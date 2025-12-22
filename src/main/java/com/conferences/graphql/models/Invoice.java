package com.conferences.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.sound.sampled.Line;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class Invoice {

    private final String id;
    private final String invoiceNumber;
    private final Customer customer;

    private final LocalDate issueDate;
    private final LocalDate dueDate;

    private InvoiceStatus status;
    private final Set<InvoiceLine> lines;

    public void addLine(InvoiceLine line) {

        if (status != InvoiceStatus.DRAFT) {

            throw new IllegalStateException();
        }

        if (Objects.isNull(line)) {

            throw new IllegalStateException();
        }

        lines.add(line);
    }

    public static Invoice createInvoice(
            String invoiceNumber,
            Customer customer,
            LocalDate issueDate,
            LocalDate dueDate,
            Set<InvoiceLine> lines
    ) {

        if (Objects.isNull(lines) || lines.isEmpty()) {

            throw new IllegalStateException();
        }

        return Invoice.builder()
                .status(InvoiceStatus.DRAFT)
                .invoiceNumber(invoiceNumber)
                .customer(customer)
                .issueDate(issueDate)
                .dueDate(dueDate)
                .lines(lines)
                .build();
    }

    public Money getTotalAmount() {
        return lines.stream()
                .map(InvoiceLine::getTotal)
                .reduce(Money::add)
                .orElse(null);
    }
}
