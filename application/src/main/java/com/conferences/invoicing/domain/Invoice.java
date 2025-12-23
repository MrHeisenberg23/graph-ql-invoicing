package com.conferences.invoicing.domain;

import com.conferences.invoicing.commands.CreateInvoiceCommand;
import com.conferences.invoicing.constants.InvoiceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Invoice {

    private String id;
    private String invoiceNumber;
    private Customer customer;

    private LocalDate issueDate;
    private LocalDate dueDate;

    private InvoiceStatus status;
    private Set<InvoiceLine> lines;

    public void addLine(InvoiceLine line) {

        if (status != InvoiceStatus.DRAFT) {

            throw new IllegalStateException();
        }

        if (Objects.isNull(line)) {

            throw new IllegalStateException();
        }

        lines.add(line);
    }

    public BigDecimal getTotal() {

        return lines.stream()
                .map(InvoiceLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Invoice createInvoice(CreateInvoiceCommand invoiceCommand) {

        Set<InvoiceLine> lines = invoiceCommand.lines();

        if (Objects.isNull(lines) || lines.isEmpty()) {

            throw new IllegalStateException();
        }

        return Invoice.builder()
                .id(UUID.randomUUID().toString())
                .status(InvoiceStatus.DRAFT)
                .invoiceNumber(invoiceCommand.invoiceNumber())
                .customer(Customer.builder()
                        .id(invoiceCommand.customerId())
                        .build())
                .issueDate(invoiceCommand.issueDate())
                .dueDate(invoiceCommand.dueDate())
                .lines(invoiceCommand.lines())
                .build();
    }
}
