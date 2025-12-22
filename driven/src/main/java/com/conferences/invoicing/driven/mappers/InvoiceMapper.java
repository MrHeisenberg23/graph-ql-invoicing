package com.conferences.invoicing.driven.mappers;

import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceLine;
import com.conferences.invoicing.driven.models.CustomerMO;
import com.conferences.invoicing.driven.models.InvoiceLineMO;
import com.conferences.invoicing.driven.models.InvoiceMO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

  InvoiceLineMO map(InvoiceLine line);

  CustomerMO map(Customer customer);

  InvoiceMO map(Invoice invoice);

  Invoice map(InvoiceMO invoice);
}
