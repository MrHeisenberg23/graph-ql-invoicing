package com.conferences.invoicing.driving.resolvers.invoice.fieldOrchestrations;

import com.conferences.invoicing.application.ports.driving.InvoiceResolverPort;
import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.projections.InvoiceWithCustomerProjection;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.query.QuerydslDataFetcher;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequiredArgsConstructor
public class InvoiceFieldBatchResolver {

    private final InvoiceResolverPort invoiceResolverPort;

    @BatchMapping(typeName = "Invoice", field = "customer")
    public Map<Long, Customer> customer(List<Invoice> invoices) {

        Set<Long> invoiceIds = invoices.stream()
                .map(Invoice::getId)
                .collect(Collectors.toSet());

        return invoiceResolverPort.findCustomersByInvoiceIds(invoiceIds);
    }
}
