package com.conferences.invoicing.driving.resolvers;

import com.conferences.invoicing.domain.Customer;
import org.dataloader.MappedBatchLoader;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Component
public class CustomerByInvoiceDataLoader
        implements MappedBatchLoader<Long, Customer> {

    @Override
    public CompletionStage<Map<Long, Customer>> load(Set<Long> invoiceIds) {
        return CompletableFuture.completedFuture(
                invoiceIds.stream()
                        .collect(Collectors.toMap(
                                id -> id,
                                id -> Customer.builder()
                                        .id(id.toString())
                                        .name("Customer test " + id)
                                        .build()
                        ))
        );
    }
}

