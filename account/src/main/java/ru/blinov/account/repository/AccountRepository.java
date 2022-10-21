package ru.blinov.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blinov.account.entity.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByClientId(Long clientId);

    void deleteAllByClientId(Long clientId);

    Boolean existsByClientId(Long clientId);
}
