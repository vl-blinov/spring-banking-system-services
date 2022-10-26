package ru.blinov.record.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.blinov.record.service.RecordService;
import ru.blinov.record.web.constant.WebConstant;
import ru.blinov.record.web.request.RecordRequest;
import ru.blinov.record.web.response.RecordResponse;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = WebConstant.VERSION_URL + "/records",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RecordController {

    private RecordService recordService;

    @PostMapping
    public ResponseEntity<RecordResponse> createRecord(@RequestBody RecordRequest recordRequest) {
        log.info("Record creating request: {}", recordRequest);
        RecordResponse recordResponse = recordService.createRecord(recordRequest);
        log.info("Response with created record: {}", recordResponse);
        return ResponseEntity.ok(recordResponse);
    }

    @GetMapping
    public List<RecordResponse> getRecords() {
        log.info("Get list of records request");
        List<RecordResponse> recordResponseList = recordService.getRecords();
        log.info("Record response list; number of records in the list: {}", recordResponseList.size());
        return recordResponseList;
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<RecordResponse> getRecordById(@PathVariable Long recordId) {
        log.info("Get record by ID request; record ID: {}", recordId);
        RecordResponse recordResponse = recordService.getRecordById(recordId);
        log.info("Response with extracted record: {}", recordResponse);
        return ResponseEntity.ok(recordResponse);
    }

    @DeleteMapping("/{recordId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecordById(@PathVariable Long recordId) {
        log.info("Delete record by ID request; record ID: {}", recordId);
        recordService.deleteRecordById(recordId);
    }

    @DeleteMapping("/accounts/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecordsByAccountId(@PathVariable Long accountId) {
        log.info("Delete records by account ID request; account ID: {}", accountId);
        recordService.deleteRecordsByAccountId(accountId);
    }

















}
