package ru.blinov.account.mapper;

import org.mapstruct.Mapper;
import ru.blinov.account.entity.Account;
import ru.blinov.account.web.response.AccountResponse;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountResponse AccountToAccountResponse(Account account);
}
