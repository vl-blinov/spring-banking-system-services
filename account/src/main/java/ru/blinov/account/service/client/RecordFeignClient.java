package ru.blinov.account.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.blinov.account.web.constant.WebConstant;
import ru.blinov.account.web.request.RecordRequest;
import ru.blinov.account.web.response.RecordResponse;

@FeignClient("record")
public interface RecordFeignClient {

    @PostMapping(WebConstant.VERSION_URL + "/records")
    ResponseEntity<RecordResponse> createRecord(@RequestBody RecordRequest recordRequest);

    @DeleteMapping(WebConstant.VERSION_URL + "/records/accounts/{accountId}")
    void deleteRecordsByAccountId(@PathVariable("accountId") Long accountId);
}
