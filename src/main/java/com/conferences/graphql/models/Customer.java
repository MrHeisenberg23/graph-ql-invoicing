package com.conferences.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Customer {

    private final String id;
    private final String name;
    private final String email;
    private final String vatNumber;
}

