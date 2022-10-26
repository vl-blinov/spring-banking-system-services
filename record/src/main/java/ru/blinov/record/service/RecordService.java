package ru.blinov.record.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.blinov.record.entity.Record;
import ru.blinov.record.exception.NotFoundException;
import ru.blinov.record.mapper.RecordMapper;
import ru.blinov.record.repository.RecordRepository;
import ru.blinov.record.service.client.AccountFeignClient;
import ru.blinov.record.web.request.RecordRequest;
import ru.blinov.record.web.response.RecordResponse;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class RecordService {

    private RecordRepository recordRepository;
    private RecordMapper recordMapper;
    private AccountFeignClient accountFeignClient;

    public RecordResponse createRecord(RecordRequest recordRequest) {
        Record record = Record.builder()
                .accId(recordRequest.getAccId())
                .operName(recordRequest.getOperName())
                .operDate(LocalDate.now())
                .build();
        log.info("Mapped record creating request: {}", record);
        Record createdRecord = recordRepository.save(record);
        log.info("Created record: {}", createdRecord);
        RecordResponse recordResponse = recordMapper.recordToRecordResponse(createdRecord);
        recordResponse.setAccNum(recordRequest.getAccNum());
        log.info("Mapped record: {}", recordResponse);
        return recordResponse;
    }

    public List<RecordResponse> getRecords() {
        return recordRepository.findAll()
                .stream()
                .map(recordMapper::recordToRecordResponse)
                .peek(recordResponse -> recordResponse
                        .setAccNum(Objects.requireNonNull(accountFeignClient
                                .getAccountById(recordResponse.getId())
                                .getBody())
                                .getAccNum()))
                .peek(recordResponse -> log.info("Mapped record: {}", recordResponse))
                .toList();
    }

    public RecordResponse getRecordById(Long recordId) {
        Record extractedRecord = recordRepository.findById(recordId)
                .orElseThrow(() -> new NotFoundException("Record with ID '" + recordId + "' is not found"));
        log.info("Extracted record: {}", extractedRecord);
        RecordResponse recordResponse = recordMapper.recordToRecordResponse(extractedRecord);
        String accNum = Objects.requireNonNull(accountFeignClient
                .getAccountById(extractedRecord.getAccId())
                .getBody())
                .getAccNum();
        recordResponse.setAccNum(accNum);
        log.info("Mapped record: {}", recordResponse);
        return recordResponse;
    }

    public void deleteRecordById(Long recordId) {
        getRecordById(recordId);
        recordRepository.deleteById(recordId);
        log.info("Record deleted; record ID: {}", recordId);
    }

    public void deleteRecordsByAccountId(Long accountId) {
        if (recordRepository.existsByAccId(accountId)) {
            recordRepository.deleteAllByAccId(accountId);
            log.info("Records for account ID '{}' have been deleted", accountId);
        } else {
            throw new NotFoundException("Records with account ID '" + accountId + "' are not found");
        }
    }
}
