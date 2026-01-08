package com.conferences.invoicing.driving.resolvers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoaderRegistryFactory {

    private final CustomerByInvoiceDataLoader loader;

    @Bean
    public DataLoaderRegistry dataLoaderRegistry() {
        DataLoaderRegistry registry = new DataLoaderRegistry();
        registry.register(
                "customerByInvoiceId",
                DataLoader.newMappedDataLoader(loader)
        );
        return registry;
    }
}

