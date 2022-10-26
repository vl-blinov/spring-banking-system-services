package ru.blinov.record.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@Table(name = "record", schema = "record_schema")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long accId;

    @Column(nullable = false)
    private String operName;

    @Column(nullable = false)
    private LocalDate operDate;
}
