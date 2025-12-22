package com.conferences.invoicing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Money {

    private final BigDecimal amount;
    private final Currency currency;

    public Money add(Money other) {

        if (!currency.equals(other.currency)) {

            throw new IllegalArgumentException();
        }

        return new Money(amount.add(other.amount), currency);
    }

    public Money multiply(int factor) {

        return new Money(amount.multiply(BigDecimal.valueOf(factor)), currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return amount.compareTo(money.amount) == 0 &&
                currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}

