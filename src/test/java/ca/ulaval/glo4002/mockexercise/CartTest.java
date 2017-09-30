package ca.ulaval.glo4002.mockexercise;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.mockexercise.do_not_edit.InvoiceLine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CartTest {
    private static final String EMAIL = "email";

    @Mock
    private InvoiceFactory invoiceFactory;

    @Mock
    private Product product1;

    @Mock
    private Product product2;

    @Captor
    private ArgumentCaptor<List<InvoiceLine>> linesCaptor;

    private Cart cart;

    @Before
    public void setUp() {
        cart = new Cart(EMAIL);
    }

    @Test
    public void generateInvoiceCreatesAnItemForEachProduct() {
        cart.addProduct(product1);
        cart.addProduct(product2);

        cart.generateInvoice(invoiceFactory);

        verify(product1).generateInvoiceLine(invoiceFactory);
        verify(product2).generateInvoiceLine(invoiceFactory);
    }

    @Test
    public void generateInvoiceCreatesAnInvoiceWithAllLines() {
        InvoiceLine line1 = willReturnInvoiceLineForProduct(product1);
        InvoiceLine line2 = willReturnInvoiceLineForProduct(product2);
        cart.addProduct(product1);
        cart.addProduct(product2);

        cart.generateInvoice(invoiceFactory);

        verify(invoiceFactory).createInvoice(eq(EMAIL), linesCaptor.capture());
        containsBothInvoiceLines(line1, line2);
    }

    private InvoiceLine willReturnInvoiceLineForProduct(Product product) {
        InvoiceLine line = mock(InvoiceLine.class);
        willReturn(line).given(product).generateInvoiceLine(any());
        return line;
    }

    private void containsBothInvoiceLines(InvoiceLine line1, InvoiceLine line2) {
        List<InvoiceLine> lines = linesCaptor.getValue();
        assertThat(lines, hasItems(line1, line2));
    }
}