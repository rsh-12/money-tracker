package ru.money.tracker.model.params;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseFilterParams {

    @PositiveOrZero
    private int page;

    @Max(500)
    @PositiveOrZero
    private int size = 10;

    private String sortBy = "id";

    private String sortDir = "asc";
}
