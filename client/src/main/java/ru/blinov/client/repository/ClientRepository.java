package ru.blinov.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blinov.client.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
