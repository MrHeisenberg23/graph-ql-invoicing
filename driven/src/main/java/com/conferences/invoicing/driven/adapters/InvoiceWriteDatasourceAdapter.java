package com.conferences.invoicing.driven.repositories;

import com.conferences.invoicing.driven.models.InvoiceMO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvoiceWriteDatasourceAdapter implements InvoiceWriteDatasourcePort {

  @PersistenceContext
  private EntityManager em;

  private final com.conferences.invoicing.driven.mappers.InvoiceMapper invoiceMapper;

  @Override
  public void save(Invoice invoice) {

    InvoiceMO invoiceMO = invoiceMapper.map(invoice);
    em.merge(invoiceMO);
  }
}
