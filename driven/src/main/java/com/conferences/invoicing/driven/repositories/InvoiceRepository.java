package com.conferences.invoicing.driven.repositories;

import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.projections.InvoiceWithCustomerProjection;
import com.conferences.invoicing.driven.models.CustomerMO;
import com.conferences.invoicing.driven.models.InvoiceMO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface InvoiceRepository extends JpaRepository<InvoiceMO, Long>, QuerydslPredicateExecutor<InvoiceMO> {

    Window<InvoiceMO> findAllBy(
            ScrollPosition scrollPosition,
            Pageable pageable
    );

    @Query("""
        select i.customer
        from InvoiceMO i
        where i.id = :invoiceId
    """)
    Optional<CustomerMO> findCustomerByInvoiceId(@Param("invoiceId") Long invoiceId);

    @Query("""
       select i.id as id, i.customer as customer
       from InvoiceMO i
           join i.customer c
       where i.id in :ids
    """)
    Set<InvoiceWithCustomerProjection> findInvoicesWithCustomer(Set<Long> ids);

}

