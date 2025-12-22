CREATE TABLE customers (
                           id           VARCHAR(36) PRIMARY KEY,
                           name         VARCHAR(255) NOT NULL,
                           email        VARCHAR(255) NOT NULL UNIQUE,
                           vat_number   VARCHAR(50)
);

CREATE TABLE invoices (
                          id              VARCHAR(36) PRIMARY KEY,
                          invoice_number  VARCHAR(100) NOT NULL,
                          customer_id     VARCHAR(36) NOT NULL,
                          issue_date      DATE,
                          due_date        DATE,
                          status          VARCHAR(20) NOT NULL,

                          CONSTRAINT fk_invoice_customer
                              FOREIGN KEY (customer_id)
                                  REFERENCES customers(id)
);

CREATE TABLE invoice_lines (
                               id            VARCHAR(36) PRIMARY KEY,
                               invoice_id    VARCHAR(36) NOT NULL,
                               description   VARCHAR(255),
                               quantity      INTEGER NOT NULL,
                               amount        NUMERIC(15, 2) NOT NULL,
                               currency      VARCHAR(3) NOT NULL,

                               CONSTRAINT fk_line_invoice
                                   FOREIGN KEY (invoice_id)
                                       REFERENCES invoices(id)
                                       ON DELETE CASCADE
);
