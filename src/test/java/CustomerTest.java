import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import foodfast.Customer;

public class CustomerTest {

    @Test
    public void testCustomerCreation() {
        Customer customer = new Customer("C001", "Jean Dupont", "123 Rue de Paris");
        assertNotNull(customer);
    }

    @Test
    public void testEqualsReturnsTrueForSameCustomers() {
        Customer customer1 = new Customer("C001", "Jean Dupont", "123 Rue de Paris");
        Customer customer2 = new Customer("C001", "Jean Dupont", "123 Rue de Paris");
        assertEquals(customer1, customer2);
    }

    @Test
    public void testEqualsReturnsFalseForDifferentCustomers() {
        Customer customer1 = new Customer("C001", "Jean Dupont", "123 Rue de Paris");
        Customer customer2 = new Customer("C002", "Marie Martin", "456 Avenue Lyon");
        assertNotEquals(customer1, customer2);
    }

    @Test
    public void testHashCodeDifferent() {
        Customer customer1 = new Customer("C001", "Jean Dupont", "123 Rue de Paris");
        Customer customer2 = new Customer("C002", "Marie Martin", "456 Avenue Lyon");

        assertNotEquals(customer1.hashCode(), customer2.hashCode());
    }

    @Test
    public void testDifferentIdNotEqual() {
        Customer customer1 = new Customer("C001", "Jean Dupont", "123 Rue de Paris");
        Customer customer2 = new Customer("C002", "Jean Dupont", "123 Rue de Paris");
        
        assertNotEquals(customer1, customer2);
    }

}
