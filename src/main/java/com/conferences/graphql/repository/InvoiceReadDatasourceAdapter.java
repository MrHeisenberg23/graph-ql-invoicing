package com.conferences.graphql.repository;

import com.conferences.graphql.entities.InvoiceMO;
import com.conferences.graphql.mappers.InvoiceMapper;
import com.conferences.graphql.models.Invoice;
import com.conferences.graphql.ports.driven.InvoiceReadDatasourcePort;
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
