package com.conferences.invoicing.driving.resolvers.invoice.fieldOrchestrations;

import com.conferences.invoicing.application.ports.driving.InvoiceResolverPort;
import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceLine;
import com.conferences.invoicing.domain.projections.InvoiceWithCustomerProjection;
import com.conferences.invoicing.domain.scalars.Money;
import com.conferences.invoicing.driving.cache.InvoiceCache;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.query.QuerydslDataFetcher;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequiredArgsConstructor
public class InvoiceFieldBatchResolver {

    private final InvoiceResolverPort invoiceResolverPort;
    private final InvoiceCache cache;

    @BatchMapping(typeName = "Invoice", field = "customer")
    public Map<Invoice, Customer> customer(List<Invoice> invoices) {

        Set<Long> invoiceIds = invoices.stream()
                .map(Invoice::getId)
                .collect(Collectors.toSet());

        Map<Long, Customer> customersByInvoice =
                invoiceResolverPort.findCustomersByInvoiceIds(invoiceIds);

        return invoices.stream()
                .collect(Collectors.toMap(
                        invoice -> invoice,
                        invoice -> customersByInvoice.getOrDefault(invoice.getId(), null)
                ));
    }

    @BatchMapping(typeName = "Invoice", field = "total")
    public Map<Invoice, BigDecimal> total(List<Invoice> invoices) {

        Set<Long> invoiceIds = invoices.stream()
                .map(Invoice::getId)
                .collect(Collectors.toSet());

        Map<Long, Set<InvoiceLine>> linesByInvoice =
                invoiceResolverPort.findLinesByInvoices(invoiceIds);

        return invoices.stream()
                .collect(Collectors.toMap(
                        invoice -> invoice,
                        invoice -> linesByInvoice
                                .getOrDefault(invoice.getId(), Set.of())
                                .stream()
                                .map(InvoiceLine::getAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                ));
    }

    @BatchMapping(typeName = "Invoice", field = "lines")
    public Map<Invoice, Set<InvoiceLine>> lines(List<Invoice> invoices) {
        Set<Long> invoiceIds = invoices.stream()
                .map(Invoice::getId)
                .collect(Collectors.toSet());

        Map<Long, Set<InvoiceLine>> linesByInvoiceId =
                invoiceResolverPort.findLinesByInvoices(invoiceIds);

        return invoices.stream()
                .collect(Collectors.toMap(
                        invoice -> invoice,
                        invoice -> linesByInvoiceId.getOrDefault(invoice.getId(), Set.of())
                ));
    }

    @BatchMapping(typeName = "Invoice", field = "totalMoney")
    public Map<Invoice, Money> totalMoney(List<Invoice> invoices) {
        Set<Long> ids = invoices.stream().map(Invoice::getId).collect(Collectors.toSet());

        Map<Long, Set<InvoiceLine>> linesById =
                cache.get(ids, invoiceResolverPort::findLinesByInvoices);

        return invoices.stream()
                .collect(Collectors.toMap(
                        invoice -> invoice,
                        invoice -> {
                            BigDecimal total = linesById
                                    .getOrDefault(invoice.getId(), Set.of())
                                    .stream()
                                    .map(InvoiceLine::getAmount)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);

                            return Money.builder()
                                    .amount(total)
                                    .currency(Currency.getInstance("EUR"))
                                    .build();
                        }
                ));
    }
}
