package com.conferences.invoicing.driven.mappers;

import com.conferences.invoicing.constants.InvoiceStatus;
import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceLine;
import com.conferences.invoicing.driven.models.CustomerMO;
import com.conferences.invoicing.driven.models.InvoiceLineMO;
import com.conferences.invoicing.driven.models.InvoiceMO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-10T16:46:21+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.14 (Homebrew)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceLineMO map(InvoiceLine line) {
        if ( line == null ) {
            return null;
        }

        InvoiceLineMO invoiceLineMO = new InvoiceLineMO();

        invoiceLineMO.setId( line.getId() );
        invoiceLineMO.setDescription( line.getDescription() );
        invoiceLineMO.setAmount( line.getAmount() );

        return invoiceLineMO;
    }

    @Override
    public CustomerMO map(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerMO customerMO = new CustomerMO();

        customerMO.setId( customer.getId() );
        customerMO.setName( customer.getName() );
        customerMO.setEmail( customer.getEmail() );
        customerMO.setVatNumber( customer.getVatNumber() );

        return customerMO;
    }

    @Override
    public Customer map(CustomerMO customer) {
        if ( customer == null ) {
            return null;
        }

        String id = null;
        String name = null;
        String email = null;
        String vatNumber = null;

        id = customer.getId();
        name = customer.getName();
        email = customer.getEmail();
        vatNumber = customer.getVatNumber();

        Customer customer1 = new Customer( id, name, email, vatNumber );

        return customer1;
    }

    @Override
    public InvoiceMO map(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceMO invoiceMO = new InvoiceMO();

        invoiceMO.setId( invoice.getId() );
        invoiceMO.setInvoiceNumber( invoice.getInvoiceNumber() );
        invoiceMO.setCustomer( map( invoice.getCustomer() ) );
        invoiceMO.setIssueDate( invoice.getIssueDate() );
        invoiceMO.setDueDate( invoice.getDueDate() );
        if ( invoice.getStatus() != null ) {
            invoiceMO.setStatus( invoice.getStatus().name() );
        }
        invoiceMO.setLines( invoiceLineSetToInvoiceLineMOSet( invoice.getLines() ) );

        afterMapping( invoiceMO );

        return invoiceMO;
    }

    @Override
    public Invoice map(InvoiceMO invoice) {
        if ( invoice == null ) {
            return null;
        }

        Long id = null;
        String invoiceNumber = null;
        LocalDate issueDate = null;
        LocalDate dueDate = null;
        InvoiceStatus status = null;

        id = invoice.getId();
        invoiceNumber = invoice.getInvoiceNumber();
        issueDate = invoice.getIssueDate();
        dueDate = invoice.getDueDate();
        if ( invoice.getStatus() != null ) {
            status = Enum.valueOf( InvoiceStatus.class, invoice.getStatus() );
        }

        Customer customer = null;
        Set<InvoiceLine> lines = null;

        Invoice invoice1 = new Invoice( id, invoiceNumber, customer, issueDate, dueDate, status, lines );

        return invoice1;
    }

    @Override
    public Invoice mapFullInvoice(InvoiceMO invoice) {
        if ( invoice == null ) {
            return null;
        }

        Long id = null;
        String invoiceNumber = null;
        Customer customer = null;
        LocalDate issueDate = null;
        LocalDate dueDate = null;
        InvoiceStatus status = null;
        Set<InvoiceLine> lines = null;

        id = invoice.getId();
        invoiceNumber = invoice.getInvoiceNumber();
        customer = map( invoice.getCustomer() );
        issueDate = invoice.getIssueDate();
        dueDate = invoice.getDueDate();
        if ( invoice.getStatus() != null ) {
            status = Enum.valueOf( InvoiceStatus.class, invoice.getStatus() );
        }
        lines = invoiceLineMOSetToInvoiceLineSet( invoice.getLines() );

        Invoice invoice1 = new Invoice( id, invoiceNumber, customer, issueDate, dueDate, status, lines );

        return invoice1;
    }

    protected Set<InvoiceLineMO> invoiceLineSetToInvoiceLineMOSet(Set<InvoiceLine> set) {
        if ( set == null ) {
            return null;
        }

        Set<InvoiceLineMO> set1 = new LinkedHashSet<InvoiceLineMO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( InvoiceLine invoiceLine : set ) {
            set1.add( map( invoiceLine ) );
        }

        return set1;
    }

    protected InvoiceLine invoiceLineMOToInvoiceLine(InvoiceLineMO invoiceLineMO) {
        if ( invoiceLineMO == null ) {
            return null;
        }

        Long id = null;
        String description = null;
        BigDecimal amount = null;

        id = invoiceLineMO.getId();
        description = invoiceLineMO.getDescription();
        amount = invoiceLineMO.getAmount();

        InvoiceLine invoiceLine = new InvoiceLine( id, description, amount );

        return invoiceLine;
    }

    protected Set<InvoiceLine> invoiceLineMOSetToInvoiceLineSet(Set<InvoiceLineMO> set) {
        if ( set == null ) {
            return null;
        }

        Set<InvoiceLine> set1 = new LinkedHashSet<InvoiceLine>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( InvoiceLineMO invoiceLineMO : set ) {
            set1.add( invoiceLineMOToInvoiceLine( invoiceLineMO ) );
        }

        return set1;
    }
}
