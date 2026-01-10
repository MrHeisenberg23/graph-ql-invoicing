package com.conferences.invoicing.driving.resolvers;

import com.conferences.invoicing.application.ports.driven.InvoiceReadDatasourcePort;
import com.conferences.invoicing.application.ports.driven.SiteReadDatasourcePort;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceWithAvailableWarehouses;
import com.conferences.invoicing.views.InvoiceWithAvailableWarehousesView;
import com.conferences.invoicing.views.SiteBoundariesView;
import com.conferences.invoicing.views.WarehouseView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class InvoiceQueryResolver {

    private final InvoiceReadDatasourcePort invoiceReadPort;
    private final SiteReadDatasourcePort siteReadDatasourcePort;

    @QueryMapping
    public List<Invoice> getAllInvoices() {

        return invoiceReadPort.findAll();
    }

    @QueryMapping
    public Invoice getInvoiceById(String id) {

        return invoiceReadPort.findById(Long.valueOf(id)).orElse(null);
    }

    @QueryMapping
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

    @QueryMapping
    public Set<Invoice> getCustomInvoices(Set<String> ids) {

        return new HashSet<>(Optional.ofNullable(this.getAllInvoices())
                .orElse(List.of()));
    }

    @QueryMapping
    public Window<Invoice> invoices(
            @Argument Integer first,
            @Argument ScrollPosition scrollPosition
    ) {
        int size = first != null ? first : 20;
        Pageable pageable = PageRequest.of(0, size);

        return null;
        //return invoiceRepository.findAllBy(scrollPosition, pageable);
    }
}
