import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import foodfast.Order;
import foodfast.Order.OrderStatus;
import foodfast.Dish;
import foodfast.Dish.DishSize;
import foodfast.Customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class OrderTest {

    private Customer customer;
    private Map<Dish, Integer> dishes;
    private Dish pizza;
    private Dish pasta;

    @BeforeEach
    public void setUp() {
        customer = new Customer("C001", "Jean Dupont", "123 Rue de Paris");
        
        pizza = new Dish("Pizza", new BigDecimal("12.50"), DishSize.LARGE);
        pasta = new Dish("Pasta", new BigDecimal("9.90"), DishSize.MEDIUM);
        
        dishes = new HashMap<>();
        dishes.put(pizza, 2);
        dishes.put(pasta, 1);
    }

    @Test
    public void testOrderCreationWithUniqueId() {
        Order order1 = new Order(dishes, customer, LocalDateTime.now());
        Order order2 = new Order(dishes, customer, LocalDateTime.now());
        assertNotNull(order1.getOrderId());
        assertNotNull(order2.getOrderId());
        assertNotEquals(order1.getOrderId(), order2.getOrderId());
    }

    @Test
    public void testCalculateTotalPrice() {
        Order order = new Order(dishes, customer, LocalDateTime.now());
        BigDecimal expected = new BigDecimal("34.90");
        BigDecimal actual = order.calculateTotalPrice();
        assertEquals(0, expected.compareTo(actual));
    }

    @Test
    public void testSetStatus() {
        Order order = new Order(dishes, customer, LocalDateTime.now());
        order.setStatus(OrderStatus.IN_PREPARATION);
        assertEquals(OrderStatus.IN_PREPARATION, order.getOrderStatus());
        order.setStatus(OrderStatus.COMPLETED);
        assertEquals(OrderStatus.COMPLETED, order.getOrderStatus());
        order.setStatus(OrderStatus.CANCELLED);
        assertEquals(OrderStatus.CANCELLED, order.getOrderStatus());
    }
}
