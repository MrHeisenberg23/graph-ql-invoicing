package com.conferences.invoicing.commands.handlers;

import com.conferences.invoicing.application.ports.driven.InvoiceReadDatasourcePort;
import com.conferences.invoicing.application.ports.driven.InvoiceWriteDatasourcePort;
import com.conferences.invoicing.application.ports.driving.InvoiceCommandPort;
import com.conferences.invoicing.commands.AddInvoiceLineCommand;
import com.conferences.invoicing.commands.CreateInvoiceCommand;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class InvoiceCommandHandler implements InvoiceCommandPort {

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
                .build();

        invoice.addLine(line);
        invoiceWritePort.save(invoice);
    }
}
