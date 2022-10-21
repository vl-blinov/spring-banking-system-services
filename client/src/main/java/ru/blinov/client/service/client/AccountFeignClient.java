package ru.blinov.client.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.blinov.client.web.constant.WebConstant;
import ru.blinov.client.web.response.AccountResponse;

import java.util.List;

@FeignClient("account")
public interface AccountFeignClient {
    @GetMapping(WebConstant.VERSION_URL + "/clients/{clientId}/accounts")
    List<AccountResponse> getAccountsByClientId(@PathVariable("clientId") Long clientId);

    @DeleteMapping(WebConstant.VERSION_URL + "/clients/{clientId}/accounts")
    void deleteAccountsByClientId(@PathVariable("clientId") Long clientId);
}
