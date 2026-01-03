package foodfast;

import java.util.Random;


public class Restaurant {
    
    private static final double FAILURE_RATE = 0.20;
    private Random random;
    
    public Restaurant() {
        this.random = new Random();
    }
    
    public void prepare(Order order) throws OrderPreparationException {
        if (random.nextDouble() < FAILURE_RATE) {
            throw new OrderPreparationException(
                "Erreur lors de la préparation de la commande " + order.getOrderId() + 
                ": problème en cuisine"
            );
        }

        System.out.println("Commande " + order.getOrderId() + " préparée avec succès");
    }
}
