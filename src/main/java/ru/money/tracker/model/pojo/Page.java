package ru.money.tracker.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    private Long total;
    private List<T> data;

    public Page(List<T> data) {
        if (data != null) {
            this.total = (long) data.size();
        }
        this.data = data;
    }

    public static <T> Page<T> empty() {
        return new Page<>(Collections.emptyList());
    }
}
