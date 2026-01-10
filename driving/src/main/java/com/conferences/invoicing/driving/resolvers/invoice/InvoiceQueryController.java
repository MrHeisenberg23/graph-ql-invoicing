package com.conferences.invoicing.driving.resolvers.invoice;

import com.conferences.invoicing.application.ports.driving.InvoiceResolverPort;
import com.conferences.invoicing.domain.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class InvoiceQueryController {

    private final InvoiceResolverPort invoiceResolverPort;

    @QueryMapping
    public List<Invoice> getAllInvoices() {

        return invoiceResolverPort.getAllInvoices();
    }

    @QueryMapping
    public Invoice getInvoiceById(String id) {

        return invoiceResolverPort.getInvoiceById(Long.valueOf(id));
    }

    @QueryMapping
    public Set<Invoice> getCustomInvoices(Set<String> ids) {

        return invoiceResolverPort.getCustomInvoices(ids);
    }

    @QueryMapping
    public Window<Invoice> getInvoices(
            @Argument Integer first,
            @Argument ScrollPosition scrollPosition
    ) {

        return invoiceResolverPort.getInvoices(first, scrollPosition);
    }
}
