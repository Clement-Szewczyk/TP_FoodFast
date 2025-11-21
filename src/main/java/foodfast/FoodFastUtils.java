package foodfast;

public class FoodFastUtils {

    public static String deliveryPlanner(int n){
        if (n%15 == 0){
            return "FizzBuzz";
        }
        else if(n%3 == 0){
            return "Fizz";
        }
        else if(n%5 == 0){
            return "Buzz";
        }
        else{
            return Integer.toString(n);
        }
    }
}