package ru.money.tracker.repository;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.money.tracker.generated.tables.records.ExpenseRecord;
import ru.money.tracker.model.dto.ExpenseDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.money.tracker.generated.tables.Expense.EXPENSE;

// Testing schema, constraints and queries
class ExpenseRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ExpenseRepository repository;

    private ExpenseDto expense;

    @BeforeEach
    void setUp() {
        expense = new ExpenseDto()
                .setTitle("Bread")
                .setAmount(BigDecimal.valueOf(44.90))
                .setDate(LocalDate.now())
                .setComment("Some comment");
    }

    @Nested
    @DisplayName("Given there is an insert operation")
    class InsertTest {

        @Nested
        @DisplayName("When we have a valid ExpenseDto")
        class ValidExpenseDto {

            @Test
            @DisplayName("Then you can insert it into the database")
            void testInsertExpenseDto() {
                expense.setTitle("Inserted expense");

                Long id = repository.insert(expense);

                assertAll("Verify the ExpenseDto is saved correctly in the database", () -> {
                    ExpenseRecord rec = fetchOneById(id);
                    assertEquals("Inserted expense", rec.getTitle());
                    assertEquals("Some comment", rec.getComment());

                    assertThat(rec.getAmount())
                            .usingComparator(BigDecimal::compareTo)
                            .isEqualTo(BigDecimal.valueOf(44.90));

                    assertCurrentDateTime(rec.getCreatedAt());
                    assertCurrentDateTime(rec.getUpdatedAt());
                });
            }
        }

        @Nested
        @DisplayName("When we have an ExpenseDto with missing required fields")
        class IncompleteExpenseDto {

            @Test
            @DisplayName("Then you cannot insert it without title")
            void testInsertExpenseDtoWithoutTitle() {
                expense.setTitle(null);

                assertThrows(DataIntegrityViolationException.class, () -> repository.insert(expense));
            }

            @Test
            @DisplayName("Then you cannot insert it without amount")
            void testInsertExpenseDtoWithoutAmount() {
                expense.setAmount(null);

                assertThrows(DataIntegrityViolationException.class, () -> repository.insert(expense));
            }
        }
    }

    @NotNull
    private ExpenseRecord fetchOneById(Long id) {
        return dslContext.selectFrom(EXPENSE)
                .where(EXPENSE.ID.eq(id))
                .fetchOptional()
                .orElseThrow();
    }
}