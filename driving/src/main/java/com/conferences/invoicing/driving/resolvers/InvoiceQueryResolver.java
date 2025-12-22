package com.conferences.invoicing.driving.resolvers;

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
