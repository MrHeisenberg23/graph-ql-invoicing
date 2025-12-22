package com.conferences.invoicing.driven.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class CustomerMO {

    @Id
    private String id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String vatNumber;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<InvoiceMO> invoices;
}