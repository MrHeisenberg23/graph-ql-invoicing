package com.conferences.invoicing.driving.resolvers;

import com.conferences.invoicing.application.ports.driven.InvoiceReadDatasourcePort;
import com.conferences.invoicing.application.ports.driven.SiteReadDatasourcePort;
import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceWithAvailableWarehouses;
import com.conferences.invoicing.domain.Warehouse;
import com.conferences.invoicing.views.InvoiceWithAvailableWarehousesView;
import com.conferences.invoicing.views.SiteBoundariesView;
import com.conferences.invoicing.views.WarehouseView;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InvoiceQueryResolver implements GraphQLQueryResolver {

    private final InvoiceReadDatasourcePort invoiceReadPort;
    private final SiteReadDatasourcePort siteReadDatasourcePort;

    public List<Invoice> getAllInvoices() {

        return invoiceReadPort.findAll();
    }

    public Invoice getInvoiceById(String id) {

        return invoiceReadPort.findById(Long.valueOf(id)).orElse(null);
    }

    public Set<InvoiceWithAvailableWarehouses> getInvoicesWithAvailableWarehouses() {

        Set<InvoiceWithAvailableWarehousesView> invoicesView = invoiceReadPort.getInvoicesWithAvailableWarehouses();
        Set<String> warehousesIds = Optional.ofNullable(invoicesView)
                .orElse(Set.of())
                .stream()
                .flatMap(invView -> invView.warehouses().stream())
                .map(WarehouseView::id)
                .collect(Collectors.toSet());
        Set<SiteBoundariesView> siteBoundariesView = siteReadDatasourcePort.getSitesBoundaries(warehousesIds);

        return InvoiceWithAvailableWarehouses.extractDetail(invoicesView, siteBoundariesView);
    }


    public Set<Invoice> getCustomInvoices(Set<String> ids) {

        return new HashSet<>(Optional.ofNullable(this.getAllInvoices())
                .orElse(List.of()));
    }
}
