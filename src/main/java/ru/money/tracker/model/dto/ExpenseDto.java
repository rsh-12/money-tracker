package ru.money.tracker.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ExpenseDto {
    private Long id;
    private String title;
    private BigDecimal amount;
    private String comment;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public ExpenseDto(String title, BigDecimal amount, String comment) {
        this.title = title;
        this.amount = amount;
        this.comment = comment;
    }
}
