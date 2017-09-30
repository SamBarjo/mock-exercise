package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.CartFactory;
import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.ProductRepository;

public class StartByTestingThis {

    private final CartFactory cartFactory;
    private final InvoiceFactory invoiceFactory;
    private final ProductRepository productRepository;

    public StartByTestingThis(
            CartFactory cartFactory,
            InvoiceFactory invoiceFactory,
            ProductRepository productRepository) {
        this.cartFactory = cartFactory;
        this.invoiceFactory = invoiceFactory;
        this.productRepository = productRepository;
    }

    public Invoice oneClickBuy(String clientEmail, String productSku) {
        Cart cart = cartFactory.create(clientEmail);
        Product product = productRepository.findBySku(productSku);

        cart.addProduct(product);

        return cart.generateInvoice(invoiceFactory);
    }
}
