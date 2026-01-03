package foodfast;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class Order {

    public enum OrderStatus { PENDING, IN_PREPARATION, COMPLETED, CANCELLED }

    private String orderId;
    private OrderStatus status = OrderStatus.PENDING;
    private Map<Dish, Integer> dishes;
    private Customer customer;
    private LocalDateTime orderDate;


    public Order(Map<Dish, Integer> dishes, Customer customer, LocalDateTime orderDate){
        this.orderId = UUID.randomUUID().toString();
        this.dishes = dishes;
        this.customer = customer;
        this.orderDate = orderDate;
    }

    public BigDecimal calculateTotalPrice(){
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            Dish dish = entry.getKey();
            Integer quantity = entry.getValue();
            total = total.add(dish.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }
        return total;
    }
    

    public String getOrderId() {
        return orderId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public OrderStatus getOrderStatus() {
        return status;
    }
    
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return orderId.equals(order.orderId) &&
               status == order.status &&
               dishes.equals(order.dishes) &&
               customer.equals(order.customer) &&
               orderDate.equals(order.orderDate);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(orderId, status, dishes, customer, orderDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", status=" + status +
                ", dishes=" + dishes +
                ", customer=" + customer +
                ", orderDate=" + orderDate +
                '}';
    }
}
