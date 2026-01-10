package com.conferences.invoicing.domain.projections;

import com.conferences.invoicing.domain.Customer;

public interface InvoiceWithCustomerProjection {

    Long getInvoiceId();

    Customer getCustomer();
}
