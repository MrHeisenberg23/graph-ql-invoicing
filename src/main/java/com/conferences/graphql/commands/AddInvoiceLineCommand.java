package com.conferences.graphql.commands;

import java.math.BigDecimal;
import java.util.Currency;

public record AddInvoiceLineCommand(
        String invoiceId,
        String description,
        int quantity,
        BigDecimal amount,
        Currency currency
) {}
