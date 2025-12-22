package com.conferences.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceLine {

    private final String id;
    private final String description;
    private final int quantity;
    private final Money unitPrice;

    public Money getTotal() {

        return unitPrice.multiply(quantity);
    }
}

