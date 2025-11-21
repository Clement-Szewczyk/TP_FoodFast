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
}
