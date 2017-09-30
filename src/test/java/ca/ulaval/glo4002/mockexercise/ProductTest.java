package ca.ulaval.glo4002.mockexercise;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductTest {
    private static final double PRICE = 12.12;
    private static final String NAME = "product";
    private static final String SKU = "sku";

    @Mock
    private InvoiceFactory invoiceFactory;
    private Product product;

    @Before
    public void setUp() {
        product = new Product(SKU, NAME, PRICE);
    }

    @Test
    public void generateInvoiceLineCreatesALineWithNameAndPrice() {
        product.generateInvoiceLine(invoiceFactory);

        verify(invoiceFactory).createInvoiceLine(NAME, PRICE);
    }
}