package ca.ulaval.glo4002.mockexercise;

import java.util.ArrayList;
import java.util.Collection;

import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.InvoiceLine;

// On crée une factory pour tester la création, mais en ce moment il n'y a pas moyen de tester la factory.
// La raison est que la story a faire dans le lab n'est pas vraiment une "loop" qui ajoute de la valeur à l'utilisateur.
public class InvoiceFactory {

    public InvoiceLine createInvoiceLine(String lineText, double price) {
        return new InvoiceLine(lineText, price);
    }

    public Invoice createInvoice(String email, Collection<InvoiceLine> lines) {
        return new Invoice(email, new ArrayList<>(lines));
    }
}
