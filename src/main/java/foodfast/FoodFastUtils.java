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
    
    public static boolean isLeapYear(int year){
        if(year%400 == 0){
            return true;
        }
        else if(year%100 == 0){
            return false;
        }
        else if(year%4 == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public static int sumUpTo(int n){
        int result = 0;
        for(int i =1; i<=n; i++){
            result += i;
        }
        return result;
    }

}