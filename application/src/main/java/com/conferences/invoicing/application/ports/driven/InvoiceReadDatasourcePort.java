package com.conferences.invoicing.application.ports.driven;

import com.conferences.invoicing.domain.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceReadDatasourcePort {

  Optional<Invoice> findById(String id);

  List<Invoice> findAll();
}
