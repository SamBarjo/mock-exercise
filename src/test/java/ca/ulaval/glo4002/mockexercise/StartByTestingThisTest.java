package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.CartFactory;
import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.ProductRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;

public class StartByTestingThisTest {

    private static final String CLIENT_EMAIL = "test@test.com";
    private static final String PRODUCT_SKU = "abc1234";

    private CartFactory cartFactory;
    private InvoiceFactory invoiceFactory;
    private ProductRepository productRepository;
    private StartByTestingThis service;

    @Before
    public void setUp() {
        cartFactory = mock(CartFactory.class);
        invoiceFactory = mock(InvoiceFactory.class);
        productRepository = mock(ProductRepository.class);
        service = new StartByTestingThis(cartFactory, invoiceFactory, productRepository);
    }

    @Test
    public void oneClickBuy_CartShouldBeCreatedUsingCartFactory() {
        given(cartFactory.create(anyString())).willReturn(new Cart(""));
        service.oneClickBuy(CLIENT_EMAIL, PRODUCT_SKU);
        then(cartFactory).should().create(CLIENT_EMAIL);
    }

    @Test
    public void oneClickBuy_ProductShouldBeFoundUsingProductRepository() {
        given(cartFactory.create(anyString())).willReturn(new Cart(""));
        service.oneClickBuy(CLIENT_EMAIL, PRODUCT_SKU);
        then(productRepository).should().findBySku(PRODUCT_SKU);
    }

    @Test
    public void oneClickBuy_ProductShouldBeAddedToCart() {
        Cart cart = mock(Cart.class);
        Product theProduct = new Product("", "", 0.00);

        given(cartFactory.create(anyString())).willReturn(cart);
        given(productRepository.findBySku(anyString())).willReturn(theProduct);

        service.oneClickBuy(CLIENT_EMAIL, PRODUCT_SKU);

        then(cart).should(times(1)).addProduct(theProduct);
    }

    @Test
    public void oneClickBuy_invoiceShouldBeCreatedUsingCart() {
        Cart theCart = new Cart(CLIENT_EMAIL);
        given(cartFactory.create(anyString())).willReturn(theCart);
        service.oneClickBuy(CLIENT_EMAIL, PRODUCT_SKU);
        then(invoiceFactory).should(times(1)).createInvoiceFromCart(theCart);
    }

    @Test
    public void oneClickBuy_shouldReturnCorrectInvoice() {
        Invoice theInvoice = new Invoice(CLIENT_EMAIL, new ArrayList<>());
        given(cartFactory.create(anyString())).willReturn(new Cart(""));
        given(invoiceFactory.createInvoiceFromCart(any(Cart.class))).willReturn(theInvoice);

        Invoice returnedInvoice = service.oneClickBuy(CLIENT_EMAIL, PRODUCT_SKU);

        assertEquals(theInvoice, returnedInvoice);
    }
}