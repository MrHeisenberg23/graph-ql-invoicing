package com.conferences.invoicing.driven.repositories;

import com.conferences.invoicing.driven.mappers.InvoiceMapper;
import com.conferences.invoicing.driven.models.InvoiceMO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InvoiceReadDatasourceAdapter implements InvoiceReadDatasourcePort {

  @PersistenceContext
  private EntityManager em;

  private final InvoiceMapper invoiceMapper;

  @Override
  public Optional<Invoice> findById(String id) {

    List<InvoiceMO> result = em.createQuery(
                    """
                    select distinct i from InvoiceMO i
                    join fetch i.customer
                    join fetch i.lines
                    where i.id = :id
                    """,
                    InvoiceMO.class
            )
            .setParameter("id", id)
            .getResultList();

    return result.stream()
            .findFirst()
            .map(invoiceMapper::map);
  }

  @Override
  public List<Invoice> findAll() {
    return em.createQuery(
                    """
                    select distinct i from InvoiceMO i
                    join fetch i.customer
                    join fetch i.lines
                    """,
                    InvoiceMO.class
            )
            .getResultList()
            .stream()
            .map(invoiceMapper::map)
            .toList();
  }
}
