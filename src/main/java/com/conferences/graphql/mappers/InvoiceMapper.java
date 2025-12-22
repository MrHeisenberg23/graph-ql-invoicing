package com.conferences.graphql.mappers;

import com.conferences.graphql.entities.InvoiceMO;
import com.conferences.graphql.models.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

  InvoiceMO map(Invoice invoice);

  Invoice map(InvoiceMO invoice);
}
