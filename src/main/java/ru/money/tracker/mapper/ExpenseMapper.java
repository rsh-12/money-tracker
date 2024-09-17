package ru.money.tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.money.tracker.generated.tables.records.ExpenseRecord;
import ru.money.tracker.model.dto.ExpenseDto;

import java.time.OffsetDateTime;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, imports = {OffsetDateTime.class})
public interface ExpenseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", source = "createdAt", defaultExpression = "java(OffsetDateTime.now())")
    @Mapping(target = "updatedAt", source = "updatedAt", defaultExpression = "java(OffsetDateTime.now())")
    void updateRecord(ExpenseDto dto, @MappingTarget ExpenseRecord rec);

}
