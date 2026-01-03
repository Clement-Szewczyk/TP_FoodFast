import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import foodfast.*;
import foodfast.Order.OrderStatus;
import foodfast.Dish.DishSize;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests pour la Question 7 : Gestion des Erreurs de Préparation
 */
public class OrderPreparationTest {

    private DeliveryPlatform platform;
    private Customer customer;
    private Map<Dish, Integer> dishes;
    
    @BeforeEach
    public void setUp() {
        platform = new DeliveryPlatform();
        customer = new Customer("C001", "Jean Dupont", "123 Rue de Paris");
        
        // Création de quelques plats pour les tests
        Dish pizza = new Dish("Pizza", new BigDecimal("12.50"), DishSize.LARGE);
        Dish pasta = new Dish("Pasta", new BigDecimal("9.90"), DishSize.MEDIUM);
        
        dishes = new HashMap<>();
        dishes.put(pizza, 2);
        dishes.put(pasta, 1);
    }
    
    @Test
    public void testOrderPreparationExceptionExists() {
        OrderPreparationException exception = new OrderPreparationException("Test error");
        assertNotNull(exception);
        assertEquals("Test error", exception.getMessage());
    }
    
    @Test
    public void testOrderPreparationExceptionWithCause() {
        Throwable cause = new RuntimeException("Cause originale");
        OrderPreparationException exception = new OrderPreparationException("Test error", cause);
        assertNotNull(exception);
    }

    @Test
    public void testRestaurantThrowsExceptionSometimes() {
        Restaurant restaurant = new Restaurant();
        
        // On teste 100 commandes, au moins quelques-unes devraient échouer (statistiquement)
        int failures = 0;
        int successes = 0;
        
        for (int i = 0; i < 100; i++) {
            Order order = new Order(dishes, customer, LocalDateTime.now());
            try {
                restaurant.prepare(order);
                successes++;
            } catch (OrderPreparationException e) {
                failures++;
                assertNotNull(e.getMessage());
                assertTrue(e.getMessage().contains(order.getOrderId()));
            }
        }
        
        // Avec 100 essais et 20% de chances d'échec, on devrait avoir entre 5 et 35 échecs
        // (intervalle de confiance large pour éviter les faux négatifs)
        assertTrue(failures > 0, "Au moins une commande devrait échouer sur 100 tentatives");
        assertTrue(successes > 0, "Au moins une commande devrait réussir sur 100 tentatives");
        assertTrue(failures < 50, "Pas plus de 50% d'échecs attendus (taux configuré à 20%)");
    }
    
    @Test
    public void testDeliveryPlatformHandlesPreparationErrors() {
        // On teste plusieurs commandes pour voir la gestion d'erreur
        boolean foundCancelled = false;
        boolean foundNotCancelled = false;
        
        for (int i = 0; i < 50; i++) {
            Order order = new Order(dishes, customer, LocalDateTime.now());
            String orderId = order.getOrderId();
            
            // PlaceOrder devrait gérer l'exception en interne
            assertDoesNotThrow(() -> platform.placeOrder(order));
            
            // La commande doit être dans la plateforme
            assertTrue(platform.findOrderById(orderId).isPresent());
            
            // Vérifier le statut
            OrderStatus status = platform.findOrderById(orderId).get().getOrderStatus();
            if (status == OrderStatus.CANCELLED) {
                foundCancelled = true;
            } else {
                foundNotCancelled = true;
            }
        }
        
        // Sur 50 commandes, on devrait avoir des deux types
        assertTrue(foundCancelled, "Au moins une commande devrait être annulée");
        assertTrue(foundNotCancelled, "Au moins une commande ne devrait pas être annulée");
    }
    
    @Test
    public void testOrderHasSetStatusMethod() {
        Order order = new Order(dishes, customer, LocalDateTime.now());
        
        assertEquals(OrderStatus.PENDING, order.getOrderStatus());
        
        order.setStatus(OrderStatus.CANCELLED);
        assertEquals(OrderStatus.CANCELLED, order.getOrderStatus());
        
        order.setStatus(OrderStatus.IN_PREPARATION);
        assertEquals(OrderStatus.IN_PREPARATION, order.getOrderStatus());
    }
    
    @Test
    public void testCancelledOrdersAreStored() {
        // Forcer plusieurs commandes jusqu'à en avoir une annulée
        Order cancelledOrder = null;
        
        for (int i = 0; i < 100; i++) {
            Order order = new Order(dishes, customer, LocalDateTime.now());
            platform.placeOrder(order);
            
            if (order.getOrderStatus() == OrderStatus.CANCELLED) {
                cancelledOrder = order;
                break;
            }
        }
        
        assertNotNull(cancelledOrder, "Au moins une commande devrait être annulée sur 100");
        
        // Vérifier que la commande annulée est bien dans la plateforme
        assertTrue(platform.findOrderById(cancelledOrder.getOrderId()).isPresent());
        assertEquals(OrderStatus.CANCELLED, 
                    platform.findOrderById(cancelledOrder.getOrderId()).get().getOrderStatus());
    }
    
    
    @Test
    public void testFailureRateIsApproximately20Percent() {
        Restaurant restaurant = new Restaurant();
        int trials = 1000;
        int failures = 0;
        
        for (int i = 0; i < trials; i++) {
            Order order = new Order(dishes, customer, LocalDateTime.now());
            try {
                restaurant.prepare(order);
            } catch (OrderPreparationException e) {
                failures++;
            }
        }
        
        double failureRate = (double) failures / trials;
        
        // Vérifier que le taux d'échec est proche de 20% (entre 15% et 25% pour être tolérant)
        assertTrue(failureRate >= 0.15, 
                  "Le taux d'échec devrait être au moins 15% (obtenu: " + (failureRate * 100) + "%)");
        assertTrue(failureRate <= 0.25, 
                  "Le taux d'échec devrait être au maximum 25% (obtenu: " + (failureRate * 100) + "%)");
    }
    
    @Test
    public void testTryCatchFinallyBehavior() {
        // Ce test vérifie que placeOrder ne propage pas l'exception
        // (ce qui indique qu'un try-catch est bien utilisé)
        for (int i = 0; i < 20; i++) {
            Order order = new Order(dishes, customer, LocalDateTime.now());
            assertDoesNotThrow(() -> platform.placeOrder(order), 
                              "placeOrder ne devrait jamais propager OrderPreparationException");
        }
    }
}
