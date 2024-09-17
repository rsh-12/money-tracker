package ru.money.tracker.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.context.annotation.ComponentScan;
import ru.money.tracker.PostgreSqlContainerTest;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@JooqTest
@ComponentScan(basePackages = {
        "ru.money.tracker.mapper",
        "ru.money.tracker.repository",
        "ru.money.tracker.generated.tables"
})
public abstract class BaseRepositoryTest extends PostgreSqlContainerTest {

    @Autowired
    protected DSLContext dslContext;

    protected void assertCurrentDateTime(OffsetDateTime dateTime) {
        assertCurrentDateTime(dateTime, 1);
    }

    protected void assertCurrentDateTime(OffsetDateTime dateTime, int seconds) {
        assertThat(OffsetDateTime.now())
                .isCloseTo(dateTime, within(seconds, ChronoUnit.SECONDS));
    }

}

