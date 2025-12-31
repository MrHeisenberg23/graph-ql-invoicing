package com.conferences.invoicing.driven.mappers;

import com.conferences.invoicing.constants.InvoiceStatus;
import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceLine;
import com.conferences.invoicing.driven.models.CustomerMO;
import com.conferences.invoicing.driven.models.InvoiceLineMO;
import com.conferences.invoicing.driven.models.InvoiceMO;
import java.time.LocalDate;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-31T12:55:59+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceLineMO map(InvoiceLine line) {
        if ( line == null ) {
            return null;
        }

        InvoiceLineMO invoiceLineMO = new InvoiceLineMO();

        return invoiceLineMO;
    }

    @Override
    public CustomerMO map(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerMO customerMO = new CustomerMO();

        return customerMO;
    }

    @Override
    public InvoiceMO map(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceMO invoiceMO = new InvoiceMO();

        afterMapping( invoiceMO );

        return invoiceMO;
    }

    @Override
    public Invoice map(InvoiceMO invoice) {
        if ( invoice == null ) {
            return null;
        }

        Long id = null;
        String invoiceNumber = null;
        Customer customer = null;
        LocalDate issueDate = null;
        LocalDate dueDate = null;
        InvoiceStatus status = null;
        Set<InvoiceLine> lines = null;

        Invoice invoice1 = new Invoice( id, invoiceNumber, customer, issueDate, dueDate, status, lines );

        return invoice1;
    }
}
