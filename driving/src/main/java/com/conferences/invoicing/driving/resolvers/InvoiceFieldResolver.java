package com.conferences.invoicing.driving.resolvers;

import com.conferences.invoicing.application.ports.driven.InvoiceReadDatasourcePort;
import com.conferences.invoicing.application.ports.driven.SiteReadDatasourcePort;
import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceWithAvailableWarehouses;
import com.conferences.invoicing.views.InvoiceWithAvailableWarehousesView;
import com.conferences.invoicing.views.SiteBoundariesView;
import com.conferences.invoicing.views.WarehouseView;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InvoiceFieldResolver implements graphql.kickstart.tools.GraphQLResolver<Invoice> {

    public Customer customer(Invoice invoice) {

        return Customer.builder()
                .id("1")
                .name("test")
                .build();
    }

    /*public CompletableFuture<Customer> customer(
            Invoice invoice,
            DataFetchingEnvironment env
    ) {
        DataLoader<Long, Customer> loader =
                env.getDataLoader("customerByInvoiceId");

        return loader.load(invoice.getId());
    }*/
}
