package ru.blinov.record.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.blinov.record.web.constant.WebConstant;
import ru.blinov.record.web.response.AccountResponse;

@FeignClient("account")
public interface AccountFeignClient {
    @GetMapping(WebConstant.VERSION_URL + "/accounts/{accountId}")
    ResponseEntity<AccountResponse> getAccountById(@PathVariable("accountId") Long accountId);
}
