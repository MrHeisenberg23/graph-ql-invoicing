package com.conferences.graphql.ports.driven;

import com.conferences.graphql.models.Invoice;

public interface InvoiceWriteDatasourcePort {

  void save(Invoice invoice);
}
