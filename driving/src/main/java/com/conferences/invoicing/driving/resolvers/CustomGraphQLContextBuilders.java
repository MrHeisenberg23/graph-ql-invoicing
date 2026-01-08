package com.conferences.invoicing.driving.resolvers;

import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

@Component
@RequiredArgsConstructor
public class CustomGraphQLContextBuilders implements GraphQLServletContextBuilder {

    private final CustomerByInvoiceDataLoader customerByInvoiceDataLoader;

    @Override
    public GraphQLContext build(HttpServletRequest request, HttpServletResponse response) {
        return createContext();
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return createContext();
    }

    @Override
    public GraphQLContext build() {
        return createContext();
    }

    private GraphQLContext createContext() {
        DataLoaderRegistry registry = new DataLoaderRegistry();
        registry.register(
                "customerByInvoiceId",
                DataLoader.newMappedDataLoader(customerByInvoiceDataLoader)
        );

        return new DefaultGraphQLContext(registry, null);
    }
}
