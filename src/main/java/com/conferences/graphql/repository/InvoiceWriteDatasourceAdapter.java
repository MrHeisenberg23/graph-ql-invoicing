package com.conferences.graphql.repository;

import com.conferences.graphql.entities.InvoiceMO;
import com.conferences.graphql.mappers.InvoiceMapper;
import com.conferences.graphql.models.Invoice;
import com.conferences.graphql.ports.driven.InvoiceReadDatasourcePort;
import com.conferences.graphql.ports.driven.InvoiceWriteDatasourcePort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InvoiceWriteDatasourceAdapter implements InvoiceWriteDatasourcePort {

  @PersistenceContext
  private EntityManager em;

  private final InvoiceMapper invoiceMapper;

  @Override
  public void save(Invoice invoice) {

    InvoiceMO invoiceMO = invoiceMapper.map(invoice);
    em.merge(invoiceMO);
  }
}
