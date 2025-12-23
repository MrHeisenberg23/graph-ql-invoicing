package com.conferences.invoicing.driven.adapters;

import com.conferences.invoicing.application.ports.driven.InvoiceWriteDatasourcePort;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.driven.mappers.InvoiceMapper;
import com.conferences.invoicing.driven.models.CustomerMO;
import com.conferences.invoicing.driven.models.InvoiceLineMO;
import com.conferences.invoicing.driven.models.InvoiceMO;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class InvoiceWriteDatasourceAdapter implements InvoiceWriteDatasourcePort {

  @PersistenceContext
  private EntityManager em;

  private final InvoiceMapper invoiceMapper;

  @Override
  public Long save(Invoice invoice) {

    InvoiceMO invoiceMO = invoiceMapper.map(invoice);

    if (invoice.getId() == null) {
      em.persist(invoiceMO);
    } else {
      invoiceMO = em.merge(invoiceMO);
    }

    em.flush();

    return invoiceMO.getId();
  }
}
