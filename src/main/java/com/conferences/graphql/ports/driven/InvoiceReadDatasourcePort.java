package com.conferences.graphql.ports.driven;

import com.conferences.graphql.models.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceReadDatasourcePort {

  Optional<Invoice> findById(String id);

  List<Invoice> findAll();
}
