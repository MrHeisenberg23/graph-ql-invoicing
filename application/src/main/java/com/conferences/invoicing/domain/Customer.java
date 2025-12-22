package com.conferences.invoicing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Customer {

    private String id;
    private String name;
    private String email;
    private String vatNumber;
}

