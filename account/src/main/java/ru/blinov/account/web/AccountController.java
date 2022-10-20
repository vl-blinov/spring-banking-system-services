package ru.blinov.account.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = WebConstant.VERSION_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<AccountResponse> openAccount(@RequestBody AccountOpeningRequest accountOpeningRequest) {
        log.info("Account opening request: {}", accountOpeningRequest);
        AccountResponse accountResponse = accountService.openAccount(accountOpeningRequest);
        log.info("Response with opened account: {}", accountResponse);
        return ResponseEntity.ok(accountResponse);
    }

    @PutMapping("/accounts")
    public ResponseEntity<AccountResponse> closeAccount(@RequestBody AccountClosingRequest accountClosingRequest) {
        log.info("Account closing request: {}", accountClosingRequest);
        AccountResponse accountResponse = accountService.closeAccount(accountClosingRequest);
        log.info("Response with closed account: {}", accountResponse);
        return ResponseEntity.ok(accountResponse);
    }

    @GetMapping("/accounts")
    public List<AccountResponse> getAccounts() {
        log.info("Get list of accounts request");
        List<AccountResponse> accountResponseList = accountService.getAccounts();
        log.info("Account response list; number of accounts in the list: {}", accountResponseList.size());
        return accountResponseList;
    }

    @GetMapping("/clients/{clientId}/accounts")
    public List<AccountResponse> getAccountsByClientId(@PathVariable Long clientId) {
        log.info("Get list of accounts by client ID request");
        List<AccountResponse> accountResponseList = accountService.getAccountsByClientId(clientId);
        log.info("Account response list; number of accounts in the list: {}", accountResponseList.size());
        return accountResponseList;
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long accountId) {
        log.info("Get account by ID request; account ID: {}", accountId);
        AccountResponse accountResponse = accountService.getAccountById(accountId);
        log.info("Response with extracted account: {}", accountResponse);
        return ResponseEntity.ok(accountResponse);
    }

    @DeleteMapping("/accounts/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountById(@PathVariable Long accountId) {
        log.info("Delete account by ID request; account ID: {}", accountId);
        accountService.deleteAccountById(accountId);
    }
}
