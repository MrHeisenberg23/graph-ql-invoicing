package com.conferences.graphql.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductQueryResolver implements GraphQLQueryResolver {

    private final List<Product> products = Arrays.asList(
            new Product("1", "Laptop", 1000.00, true),
            new Product("2", "Smartphone", 800.00, true),
            new Product("3", "Tablet", 500.00, false)
    );

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(String id) {
        Product result = null;
        for (Product product : products) {
            if (product.getId().equals(id)) {
                result =  product;
            }
        }
        return result;
    }

    public List<Product> getAllProductsFilteredByActive(Boolean isActive) {

        List<Product> result = products;

        if(isActive!=null){
            result = result.stream().filter(product -> product.getActive().equals(isActive)).toList();
        }

        return result;
    }

    //Overcharged method to test when uncommenting getAllProductsFilteredByActive from schema
    public List<Product> getAllProductsFilteredByActive() {

        return List.of(new Product("1", "Overcharging", 999.0, true));
    }

}