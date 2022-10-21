package ru.blinov.account.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.blinov.account.entity.Account;
import ru.blinov.account.exception.NotFoundException;
import ru.blinov.account.mapper.AccountMapper;
import ru.blinov.account.repository.AccountRepository;
import ru.blinov.account.web.request.AccountClosingRequest;
import ru.blinov.account.web.request.AccountOpeningRequest;
import ru.blinov.account.web.response.AccountResponse;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;
    private AccountMapper accountMapper;

    public AccountResponse openAccount(AccountOpeningRequest accountOpeningRequest) {
        Account account = Account.builder()
                .clientId(accountOpeningRequest.getClientId())
                .accNum(UUID.randomUUID().toString())
                .saldo(BigDecimal.valueOf(0L, 2))
                .openDate(LocalDate.now())
                .build();
        log.info("Mapped account opening request: {}", account);
        Account openedAccount = accountRepository.save(account);
        log.info("Opened account: {}", openedAccount);
        return accountMapper.AccountToAccountResponse(openedAccount);
    }

    public AccountResponse closeAccount(AccountClosingRequest accountClosingRequest) {
        Long accountId = accountClosingRequest.getAccountId();
        Account extractedAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account with ID '" + accountId + "' is not found"));
        log.info("Extracted account: {}", extractedAccount);
        extractedAccount.setCloseDate(LocalDate.now());
        Account closedAccount = accountRepository.save(extractedAccount);
        log.info("Closed account: {}", closedAccount);
        return accountMapper.AccountToAccountResponse(closedAccount);
    }

    public List<AccountResponse> getAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::AccountToAccountResponse)
                .peek(accountResponse -> log.info("Mapped account: {}", accountResponse))
                .toList();
    }

    public List<AccountResponse> getAccountsByClientId(Long clientId) {
        return accountRepository.findAllByClientId(clientId)
                .stream()
                .map(accountMapper::AccountToAccountResponse)
                .peek(accountResponse -> log.info("Mapped account: {}", accountResponse))
                .toList();
    }

    public AccountResponse getAccountById(Long accountId) {
        Account extractedAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account with ID '" + accountId + "' is not found"));
        log.info("Extracted account: {}", extractedAccount);
        return accountMapper.AccountToAccountResponse(extractedAccount);
    }

    public void deleteAccountById(Long accountId) {
        getAccountById(accountId);
        accountRepository.deleteById(accountId);
        log.info("Account deleted; account ID: {}", accountId);
    }

    public void deleteAccountsByClientId(Long clientId) {
        if(accountRepository.existsByClientId(clientId)) {
            accountRepository.deleteAllByClientId(clientId);
            log.info("Accounts for client ID: {} have been deleted", clientId);
        } else {
            throw new NotFoundException("Accounts with client ID '" + clientId + "' are not found");
        }
    }
}
