package foodfast;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import foodfast.Order.OrderStatus;

import java.util.List;
import java.util.stream.Collectors;

public class DeliveryPlatform {
    private final Map<String, Order> orders;
    private Restaurant restaurant;

    public DeliveryPlatform() {
        orders = new ConcurrentHashMap<>();
        restaurant = new Restaurant();
    }

    public synchronized void placeOrder(Order order) {
        orders.put(order.getOrderId(), order);
        
        try {
            restaurant.prepare(order);
            order.setStatus(OrderStatus.IN_PREPARATION);
        } catch (OrderPreparationException e) {
            order.setStatus(OrderStatus.CANCELLED);
            System.err.println("Erreur de préparation : " + e.getMessage());
            System.err.println("La commande " + order.getOrderId() + " a été annulée.");
        } finally {
            System.out.println("Traitement de la commande " + order.getOrderId() + " terminé.");
        }
    }

    public Optional<Order> findOrderById(String orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }

    public List<Order> findOrdersByCustomer(Customer customer) {
        return orders.values().stream()
                .filter(order -> order.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }

    public List<Order> findOrdersByStatus(OrderStatus status) {
        return orders.values().stream()
                .filter(order -> order.getOrderStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Order> getAllOrders() {
        return orders.values().stream().collect(Collectors.toList());
    }
}
