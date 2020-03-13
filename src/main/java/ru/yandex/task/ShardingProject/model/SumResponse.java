package ru.yandex.task.ShardingProject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class SumResponse {

    private final BigDecimal sum;

    @JsonCreator
    public SumResponse(@JsonProperty("sum") BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getSum() {
        return sum;
    }
}
