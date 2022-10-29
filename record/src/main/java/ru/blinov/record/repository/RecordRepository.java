package ru.blinov.record.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blinov.record.entity.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {

    Boolean existsByAccId(Long accountId);

    void deleteAllByAccId(Long accountId);
}
