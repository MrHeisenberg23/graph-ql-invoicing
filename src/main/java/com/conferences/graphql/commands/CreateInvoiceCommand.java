package com.conferences.graphql.commands;

import com.conferences.graphql.models.InvoiceLine;

import java.time.LocalDate;
import java.util.Set;

public record CreateInvoiceCommand(
        String invoiceNumber,
        String customerId,
        LocalDate issueDate,
        LocalDate dueDate,
        Set<InvoiceLine> lines
) {}

