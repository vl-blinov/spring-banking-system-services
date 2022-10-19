package ru.blinov.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blinov.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
