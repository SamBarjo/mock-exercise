package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.InvoiceLine;

public class Product {
    private final String sku;
    private final String name;
    private final double price;

    public Product(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public InvoiceLine generateInvoiceLine(InvoiceFactory invoiceFactory) {
        return invoiceFactory.createInvoiceLine(name, price);
    }
}
