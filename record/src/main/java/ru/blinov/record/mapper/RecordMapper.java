package ru.blinov.record.mapper;

import org.mapstruct.Mapper;
import ru.blinov.record.entity.Record;
import ru.blinov.record.web.request.RecordRequest;
import ru.blinov.record.web.response.RecordResponse;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    RecordResponse recordToRecordResponse(Record record);
}
