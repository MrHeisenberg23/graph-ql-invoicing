package com.conferences.invoicing.application.ports.driven;

import com.conferences.invoicing.domain.Invoice;

public interface InvoiceWriteDatasourcePort {

  void save(Invoice invoice);
}
