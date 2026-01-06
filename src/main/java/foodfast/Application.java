package foodfast;

public class Application {
    public static void main(String[] args) {
        System.out.println("Bienvenue chez FoodFast !");

        if (args.length > 0) {
            System.out.println("Arguments passés :");
            for (String arg : args) {
                System.out.println("  - " + arg);
            }
        }
    }
}

