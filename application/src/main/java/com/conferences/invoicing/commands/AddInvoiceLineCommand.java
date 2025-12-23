package com.conferences.invoicing.commands;

import java.math.BigDecimal;
import java.util.Currency;

public record AddInvoiceLineCommand(
        String invoiceId,
        String lineId,
        String description,
        BigDecimal amount
) {}
