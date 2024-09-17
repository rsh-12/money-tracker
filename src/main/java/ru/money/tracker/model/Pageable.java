package ru.money.tracker.model;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectSelectStep;
import org.jooq.SortField;
import org.jooq.Table;
import ru.money.tracker.model.params.BaseFilterParams;
import ru.money.tracker.model.pojo.Page;

import java.util.List;

public abstract class Pageable<D, P extends BaseFilterParams> {

    public Page<D> getPage(P params) {
        SelectSelectStep<?> select = getSelect();

        Table<?> from = getFrom(params);

        Condition where = getWhere(params);

        List<SortField<?>> orderBy = getOrderBy(params);

        int offset = getOffset(params.getPage(), params.getSize());

        int limit = getLimit(params.getSize());

        Long total = getDSLContext().selectCount()
                .from(from)
                .where(where)
                .fetchOneInto(Long.class);

        List<D> data = select
                .from(from)
                .where(where)
                .orderBy(orderBy)
                .offset(offset)
                .limit(limit)
                .fetch()
                .stream()
                .map(this::toDto)
                .toList();

        return new Page<>(total, data);
    }

    protected abstract SelectSelectStep getSelect();

    protected abstract Table<?> getFrom(P params);

    protected abstract Condition getWhere(P params);

    protected abstract List<SortField<?>> getOrderBy(P params);

    protected abstract DSLContext getDSLContext();

    protected abstract D toDto(Record o);

    private int getOffset(Integer page, Integer size) {
        if (page == null || page < 0) {
            return 0;
        }
        return page * getLimit(size);
    }

    private int getLimit(Integer size) {
        if (size == null || size < 0) {
            return 10;
        }
        return size;
    }

}
