import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApplicationTest {
    @Test
    public void test(){
        System.out.println("Test");
        Assertions.assertEquals(1, 1);

    }


    @Test
    public void testDeliveryPlanner(){
        Assertions.assertEquals("Fizz", foodfast.FoodFastUtils.deliveryPlanner(3));
        Assertions.assertEquals("Buzz", foodfast.FoodFastUtils.deliveryPlanner(5));
        Assertions.assertEquals("FizzBuzz", foodfast.FoodFastUtils.deliveryPlanner(15));
        Assertions.assertEquals("7", foodfast.FoodFastUtils.deliveryPlanner(7));
        Assertions.assertEquals("22", foodfast.FoodFastUtils.deliveryPlanner(22));
        Assertions.assertEquals("Fizz", foodfast.FoodFastUtils.deliveryPlanner(9));
        Assertions.assertEquals("Buzz", foodfast.FoodFastUtils.deliveryPlanner(20));
        Assertions.assertEquals("FizzBuzz", foodfast.FoodFastUtils.deliveryPlanner(30));
    }

    @Test
    public void testisLeapYear(){
        Assertions.assertEquals(true, foodfast.FoodFastUtils.isLeapYear(2020));
        Assertions.assertEquals(false, foodfast.FoodFastUtils.isLeapYear(2021));
        Assertions.assertEquals(false, foodfast.FoodFastUtils.isLeapYear(2022));
        Assertions.assertEquals(false, foodfast.FoodFastUtils.isLeapYear(2023));
        Assertions.assertEquals(true, foodfast.FoodFastUtils.isLeapYear(2024));
    }

    @Test
    public void testsumUpTo(){
        Assertions.assertEquals(10, foodfast.FoodFastUtils.sumUpTo(4));
        Assertions.assertEquals(15, foodfast.FoodFastUtils.sumUpTo(5));
        Assertions.assertEquals(21, foodfast.FoodFastUtils.sumUpTo(6));
    }

    @Test
    public void testAnonymize(){
        Assertions.assertEquals("nob", foodfast.FoodFastUtils.anonymize("bon"));
        Assertions.assertEquals("olleh", foodfast.FoodFastUtils.anonymize("hello"));
        Assertions.assertEquals("tejorp", foodfast.FoodFastUtils.anonymize("projet"));
    }
}
