package com.conferences.invoicing.driven.mappers;

import com.conferences.invoicing.driven.models.InvoiceMO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

  InvoiceMO map(Invoice invoice);

  Invoice map(InvoiceMO invoice);
}
