package ca.ulaval.glo4002.mockexercise;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.InvoiceLine;

import static java.util.stream.Collectors.toList;

public class Cart {
    private final String email;
    private final List<Product> products = new ArrayList<>();

    public Cart(String email) {
        this.email = email;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Invoice generateInvoice(InvoiceFactory invoiceFactory) {
        List<InvoiceLine> lines = products.stream().map(x -> x.generateInvoiceLine(invoiceFactory)).collect(toList());
        return invoiceFactory.createInvoice(email, lines);
    }
}
