package com.conferences.invoicing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Customer {

    private final String id;
    private final String name;
    private final String email;
    private final String vatNumber;
}

