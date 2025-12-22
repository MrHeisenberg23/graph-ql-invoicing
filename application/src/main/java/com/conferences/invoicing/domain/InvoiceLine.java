package com.conferences.invoicing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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

