package ru.money.tracker.repository.expense;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.money.tracker.generated.tables.records.ExpenseRecord;
import ru.money.tracker.mapper.ExpenseMapper;
import ru.money.tracker.model.dto.ExpenseDto;

import static ru.money.tracker.generated.tables.Expense.EXPENSE;

@Repository
@RequiredArgsConstructor
public class ExpenseRepository {
    private final DSLContext dslContext;
    private final ExpenseMapper mapper;

    public Long insert(ExpenseDto dto) {
        ExpenseRecord rec = dslContext.newRecord(EXPENSE);
        mapper.updateRecord(dto, rec);
        rec.store();

        return rec.getId();
    }

}
