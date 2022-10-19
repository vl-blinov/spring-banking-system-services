package ru.blinov.account.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.blinov.account.service.AccountService;
import ru.blinov.account.web.constant.WebConstant;
import ru.blinov.account.web.request.AccountClosingRequest;
import ru.blinov.account.web.request.AccountOpeningRequest;
import ru.blinov.account.web.response.AccountResponse;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = WebConstant.VERSION_URL + "/accounts",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private AccountService accountService;

    @PostMapping
    public AccountResponse openAccount(@RequestBody AccountOpeningRequest accountOpeningRequest) {
        log.info("Account opening request: {}", accountOpeningRequest);
        AccountResponse accountResponse = accountService.openAccount(accountOpeningRequest);
        log.info("Response with opened account: {}", accountResponse);
        return accountResponse;
    }

    @PutMapping
    public AccountResponse closeAccount(@RequestBody AccountClosingRequest accountClosingRequest) {
        log.info("Account closing request: {}", accountClosingRequest);
        AccountResponse accountResponse = accountService.closeAccount(accountClosingRequest);
        log.info("Response with closed account: {}", accountResponse);
        return accountResponse;
    }

    @GetMapping
    public List<AccountResponse> getAccounts() {
        log.info("Get list of accounts request");
        List<AccountResponse> accountResponseList = accountService.getAccounts();
        log.info("Account response list; number of accounts in the list: {}", accountResponseList.size());
        return accountResponseList;
    }

    @GetMapping("/{accountId}")
    public AccountResponse getAccountById(@PathVariable Long accountId) {
        log.info("Get account by ID request; account ID: {}", accountId);
        AccountResponse accountResponse = accountService.getAccountById(accountId);
        log.info("Response with extracted account: {}", accountResponse);
        return accountResponse;
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccountById(@PathVariable Long accountId) {
        log.info("Delete account by ID request; account ID: {}", accountId);
        accountService.deleteAccountById(accountId);
    }
}
