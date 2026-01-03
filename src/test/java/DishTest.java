import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import foodfast.Dish;
import foodfast.Dish.DishSize;

import java.math.BigDecimal;

/**
 * Tests pour la classe Dish (Question 4)
 */
public class DishTest {

    @Test
    public void testDishCreation() {
        Dish dish = new Dish("Pizza", new BigDecimal("12.50"), DishSize.LARGE);
        assertNotNull(dish);
    }

    @Test
    public void testGetPrice() {
        BigDecimal price = new BigDecimal("12.50");
        Dish dish = new Dish("Pizza", price, DishSize.LARGE);
        assertEquals(price, dish.getPrice());
    }

    @Test
    public void testEqualsReturnsTrueForSameDishes() {
        Dish dish1 = new Dish("Pizza", new BigDecimal("12.50"), DishSize.LARGE);
        Dish dish2 = new Dish("Pizza", new BigDecimal("12.50"), DishSize.LARGE);
        assertEquals(dish1, dish2);
    }

    @Test
    public void testDifferentNameNotEqual() {
        Dish dish1 = new Dish("Pizza", new BigDecimal("12.50"), DishSize.LARGE);
        Dish dish2 = new Dish("Pasta", new BigDecimal("12.50"), DishSize.LARGE);
        
        assertNotEquals(dish1, dish2);
    }

    @Test
    public void testDifferentPriceNotEqual() {
        Dish dish1 = new Dish("Pizza", new BigDecimal("12.50"), DishSize.LARGE);
        Dish dish2 = new Dish("Pizza", new BigDecimal("15.00"), DishSize.LARGE);
        
        assertNotEquals(dish1, dish2);
    }

    @Test
    public void testDifferentSizeNotEqual() {
        Dish dish1 = new Dish("Pizza", new BigDecimal("12.50"), DishSize.LARGE);
        Dish dish2 = new Dish("Pizza", new BigDecimal("12.50"), DishSize.SMALL);
        
        assertNotEquals(dish1, dish2);
    }

}
