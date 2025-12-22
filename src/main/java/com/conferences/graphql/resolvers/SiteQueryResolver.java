package com.conferences.graphql.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SiteQueryResolver implements GraphQLQueryResolver {

    private final List<Site> sites = Arrays.asList(
            new Site("1", "Alisal", Arrays.asList(
                    new Product("1", "Product1", 10.0, true),
                    new Product("2", "Product2", 20.0, true)
            )),
            new Site("2", "Solares", Arrays.asList(
                    new Product("3", "Product3", 15.0, true),
                    new Product("4", "Product4", 25.0, true)
            )),
            new Site("3", "La Esprilla", Arrays.asList(
                    new Product("5", "Product5", 30.0, true),
                    new Product("6", "Product6", 35.0, true)
            ))
    );

    public List<Site> getAllSites() {
        return sites;
    }

    public Site getSiteById(String id) {
        Site result = null;
        for (Site site : sites) {
            if (site.getId().equals(id)) {
                result =  site;
            }
        }
        return result;
    }

    public List<Product> getProductsBySiteId(String siteId) {

        Site site = sites.stream()
                .filter(s -> s.getId().equals(siteId))
                .findFirst()
                .orElse(null);

        List<Product> products = new ArrayList<>();

        if (site != null) {
            products = site.getProducts();
        }

        return  products;
    }
}
