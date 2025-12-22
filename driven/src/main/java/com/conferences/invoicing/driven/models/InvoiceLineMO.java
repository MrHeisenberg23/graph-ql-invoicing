package com.conferences.invoicing.driven.models;

import com.conferences.graphql.models.Money;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice_lines")
public class InvoiceLineMO {

    @Id
    private String id;

    private String description;

    private int quantity;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private InvoiceMO invoice;
}