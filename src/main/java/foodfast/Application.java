package foodfast;

public class Application {
    public static void main(String[] args) {
        System.out.println("Bienvennue chez FoodFast !");
        System.out.println("Argument :");
        for (int i = 0; i<args.length; i++){
            System.out.println("Argument " + i + ": "+ args[i]);
        }
    }
}

