package com.conferences.invoicing.driving.resolvers;

import com.conferences.invoicing.application.ports.driven.InvoiceReadDatasourcePort;
import com.conferences.invoicing.domain.Invoice;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InvoiceQueryResolver implements GraphQLQueryResolver {

    private final InvoiceReadDatasourcePort invoiceReadPort;

    public List<Invoice> getAllInvoices() {

        return invoiceReadPort.findAll();
    }

    public Invoice getInvoiceById(String id) {

        return invoiceReadPort.findById(id).orElse(null);
    }
}
