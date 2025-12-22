package com.conferences.invoicing.driving.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceControllerAdapter {

    /*private final InvoiceCommandPort invoiceCommandHandler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody CreateInvoiceRequest request) {

      CreateInvoiceCommand command = new CreateInvoiceCommand(
              request.invoiceNumber(),
              request.customerId(),
              request.issueDate(),
              request.dueDate()
      );

      return invoiceCommandHandler.handle(command);
    }

    @PostMapping("/{invoiceId}/lines")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addInvoiceLine(
            @PathVariable String invoiceId,
            @RequestBody AddInvoiceLineRequest request
    ) {
      AddInvoiceLineCommand command = new AddInvoiceLineCommand(
              invoiceId,
              request.description(),
              request.quantity(),
              request.amount(),
              request.currency()
      );

      invoiceCommandHandler.handle(command);
    }*/
}
