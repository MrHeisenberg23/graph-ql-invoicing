package com.conferences.invoicing.application.ports.driven;

import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.views.InvoiceWithAvailableWarehousesView;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface InvoiceReadDatasourcePort {

  Optional<Invoice> findById(Long id);

  List<Invoice> findAll();

  Set<InvoiceWithAvailableWarehousesView> getInvoicesWithAvailableWarehouses();
}
