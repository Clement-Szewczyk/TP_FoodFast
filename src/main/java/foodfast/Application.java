package foodfast;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Application {
    public static void main(String[] args) {
        System.out.println("Bienvenue chez FoodFast !");

        try (Scanner scanner = new Scanner(System.in)) {
            // Création du client
            System.out.print("Nom du client : ");
            String name = scanner.nextLine().trim();
            System.out.print("Adresse du client : ");
            String address = scanner.nextLine().trim();
            String customerId = UUID.randomUUID().toString();
            Customer customer = new Customer(customerId, name, address);

            // Menu de démonstration
            Dish d1 = new Dish("Pizza Margherita", new BigDecimal("8.50"), Dish.DishSize.MEDIUM);
            Dish d2 = new Dish("Burger Classic", new BigDecimal("6.20"), Dish.DishSize.MEDIUM);
            Dish d3 = new Dish("Salade César", new BigDecimal("5.00"), Dish.DishSize.SMALL);
            Dish d4 = new Dish("Sushi Mix", new BigDecimal("12.00"), Dish.DishSize.LARGE);
            Dish[] menu = new Dish[] { d1, d2, d3, d4 };

            Map<Dish, Integer> selection = new LinkedHashMap<>();

            System.out.println("\nMenu :");
            for (int i = 0; i < menu.length; i++) {
                System.out.printf("%d) %s - %s €\n", i + 1, menu[i].toString(), menu[i].getPrice());
            }

            System.out.println("\nSaisissez le numéro du plat pour l'ajouter, ou 0 pour terminer.");
            while (true) {
                System.out.print("Numéro du plat (0 pour terminer) : ");
                String line = scanner.nextLine().trim();
                int choice;
                try {
                    choice = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide, réessayez.");
                    continue;
                }

                if (choice == 0) break;
                if (choice < 1 || choice > menu.length) {
                    System.out.println("Choix hors menu, réessayez.");
                    continue;
                }

                Dish chosen = menu[choice - 1];
                System.out.print("Quantité : ");
                String qtyLine = scanner.nextLine().trim();
                int qty;
                try {
                    qty = Integer.parseInt(qtyLine);
                    if (qty <= 0) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("Quantité invalide, réessayez.");
                    continue;
                }

                selection.merge(chosen, qty, Integer::sum);
                System.out.println(qty + " x " + chosen + " ajouté(s) au panier.");
            }

            if (selection.isEmpty()) {
                System.out.println("Aucun plat sélectionné. Fin de l'application.");
                return;
            }

            Order order = new Order(selection, customer, LocalDateTime.now());
            DeliveryPlatform platform = new DeliveryPlatform();

            System.out.println("\nPlacement de la commande...\n");
            platform.placeOrder(order);

            System.out.println("\nRésumé de la commande :");
            System.out.println(order);
            System.out.println("Prix total : " + order.calculateTotalPrice() + " €");
            System.out.println("Statut : " + order.getOrderStatus());
        }
    }
}

