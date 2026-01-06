import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import foodfast.DeliveryPlatform;
import foodfast.Order;
import foodfast.Dish;
import foodfast.Dish.DishSize;
import foodfast.Customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Tests pour la classe DeliveryPlatform (Questions 5 et 6)
 */
public class DeliveryPlatformTest {

    private DeliveryPlatform platform;
    private Customer customer1;
    private Customer customer2;
    private Map<Dish, Integer> dishes;
    private Dish pizza;

    @BeforeEach
    public void setUp() {
        platform = new DeliveryPlatform();
        customer1 = new Customer("C001", "Jean Dupont", "123 Rue de Paris");
        customer2 = new Customer("C002", "Marie Martin", "456 Avenue Lyon");
        
        pizza = new Dish("Pizza", new BigDecimal("12.50"), DishSize.LARGE);
        
        dishes = new HashMap<>();
        dishes.put(pizza, 1);
    }

    @Test
    public void testPlaceOrder() {
        Order order = new Order(dishes, customer1, LocalDateTime.now());
        platform.placeOrder(order);
        
        Optional<Order> foundOrder = platform.findOrderById(order.getOrderId());
        assertTrue(foundOrder.isPresent());
        assertEquals(order.getOrderId(), foundOrder.get().getOrderId());
    }

    @Test
    public void testFindOrderByIdNotFound() {
        Optional<Order> result = platform.findOrderById("NON_EXISTENT_ID");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindOrderByIdFound() {
        Order order1 = new Order(dishes, customer1, LocalDateTime.now());
        Order order2 = new Order(dishes, customer2, LocalDateTime.now());
        
        platform.placeOrder(order1);
        platform.placeOrder(order2);
        
        Optional<Order> found = platform.findOrderById(order1.getOrderId());
        assertTrue(found.isPresent());
        assertEquals(order1.getOrderId(), found.get().getOrderId());
        assertEquals(customer1, found.get().getCustomer());
    }

    @Test
    public void testFindOrdersByCustomer() {
        Order order1 = new Order(dishes, customer1, LocalDateTime.now());
        Order order2 = new Order(dishes, customer1, LocalDateTime.now());
        Order order3 = new Order(dishes, customer2, LocalDateTime.now());
        
        platform.placeOrder(order1);
        platform.placeOrder(order2);
        platform.placeOrder(order3);
        
        List<Order> customer1Orders = platform.findOrdersByCustomer(customer1);
        assertEquals(2, customer1Orders.size());
        assertTrue(customer1Orders.stream().allMatch(o -> o.getCustomer().equals(customer1)));
    }

    @Test
    public void testFindOrdersByCustomerEmpty() {
        List<Order> orders = platform.findOrdersByCustomer(customer1);
        assertNotNull(orders);
        assertTrue(orders.isEmpty());
    }

    @Test
    public void testFindOrdersByStatus() {
        // Créer plusieurs commandes
        for (int i = 0; i < 20; i++) {
            Order order = new Order(dishes, customer1, LocalDateTime.now());
            platform.placeOrder(order);
        }
    }
}
