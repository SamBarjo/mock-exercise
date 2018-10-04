package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;

public interface InvoiceFactory {

    Invoice createInvoiceFromCart(Cart cart);
}
