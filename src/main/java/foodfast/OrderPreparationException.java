package foodfast;


public class OrderPreparationException extends Exception {
    
    public OrderPreparationException(String message) {
        super(message);
    }
    
    public OrderPreparationException(String message, Throwable cause) {
        super(message, cause);
    }
}
