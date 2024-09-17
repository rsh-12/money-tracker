package ru.money.tracker.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ExpenseDto {
    private Long id;
    private String title;
    private BigDecimal amount;
    private LocalDate date;
    private String comment;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
