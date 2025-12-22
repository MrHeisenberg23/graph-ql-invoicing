package com.conferences.invoicing.driven.mappers;

import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.driven.models.InvoiceMO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-22T23:06:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.14 (Homebrew)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceMO map(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceMO invoiceMO = new InvoiceMO();

        return invoiceMO;
    }

    @Override
    public Invoice map(InvoiceMO invoice) {
        if ( invoice == null ) {
            return null;
        }

        Invoice.InvoiceBuilder invoice1 = Invoice.builder();

        return invoice1.build();
    }
}
