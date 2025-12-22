package com.conferences.invoicing.driven.models;

import com.conferences.graphql.models.Customer;
import com.conferences.graphql.models.InvoiceLine;
import com.conferences.graphql.models.InvoiceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoices")
public class InvoiceMO {

    @Id
    private String id;

    private String invoiceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerMO customer;

    private LocalDate issueDate;
    private LocalDate dueDate;
    private String status;

    @OneToMany(
            mappedBy = "invoice",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<InvoiceLineMO> lines;
}
