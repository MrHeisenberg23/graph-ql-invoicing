package com.conferences.invoicing.driving.resolvers;

import com.conferences.graphql.models.Invoice;
import com.conferences.graphql.ports.driven.InvoiceReadDatasourcePort;
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
