package com.conferences.graphql.commands.handlers;

import com.conferences.graphql.commands.AddInvoiceLineCommand;
import com.conferences.graphql.commands.CreateInvoiceCommand;
import com.conferences.graphql.entities.*;
import com.conferences.graphql.models.Invoice;
import com.conferences.graphql.models.InvoiceLine;
import com.conferences.graphql.models.Money;
import com.conferences.graphql.ports.driven.InvoiceReadDatasourcePort;
import com.conferences.graphql.ports.driven.InvoiceWriteDatasourcePort;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class InvoiceCommandHandler {

    private final InvoiceReadDatasourcePort invoiceReadPort;
    private final InvoiceWriteDatasourcePort invoiceWritePort;

    public Invoice handle(CreateInvoiceCommand command) {

        Invoice toBeCreatedInvoice = Invoice.createInvoice(command);

        invoiceWritePort.save(toBeCreatedInvoice);
        return toBeCreatedInvoice;
    }

    public void handle(AddInvoiceLineCommand command) {

        Invoice invoice = invoiceReadPort.findById(command.invoiceId())
                .orElseThrow(IllegalArgumentException::new);

        InvoiceLine line = InvoiceLine.builder()
                .id(UUID.randomUUID().toString())
                .description(command.description())
                .quantity(command.quantity())
                .unitPrice(new Money(command.amount(), command.currency()))
                .build();

        invoice.addLine(line);
        invoiceWritePort.save(invoice);
    }
}
