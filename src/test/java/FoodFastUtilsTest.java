import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import foodfast.FoodFastUtils;

public class FoodFastUtilsTest {

    @Test
    public void testDeliveryPlannerFizz() {
        assertEquals("Fizz", FoodFastUtils.deliveryPlanner(3));
    }

    @Test
    public void testIsLeapYearDivisibleBy400() {
        assertTrue(FoodFastUtils.isLeapYear(2000));
    }

    @Test
    public void testIsLeapYearNotDivisibleBy4() {
        assertFalse(FoodFastUtils.isLeapYear(2021));
    }

    @Test
    public void testIsLeapYearEdgeCases() {
        assertTrue(FoodFastUtils.isLeapYear(4));
        assertFalse(FoodFastUtils.isLeapYear(1));
    }

    // ============ Tests pour sumUpTo ============
    
    @Test
    public void testSumUpTo() {
        assertEquals(1, FoodFastUtils.sumUpTo(1));      // 1
        assertEquals(3, FoodFastUtils.sumUpTo(2));      // 1+2
        assertEquals(6, FoodFastUtils.sumUpTo(3));      // 1+2+3

    }

    @Test
    public void testSumUpToLargeValues() {
        assertEquals(55, FoodFastUtils.sumUpTo(10));    // 1+2+...+10
        assertEquals(5050, FoodFastUtils.sumUpTo(100)); // 1+2+...+100
    }


    // ============ Tests pour anonymize ============
    
    @Test
    public void testAnonymize() {
        assertEquals("cba", FoodFastUtils.anonymize("abc"));
        assertEquals("olleh", FoodFastUtils.anonymize("hello"));
       
    }

    @Test
    public void testAnonymizeEmptyString() {
        assertEquals("", FoodFastUtils.anonymize(""));
    }

    @Test
    public void testAnonymizeSingleCharacter() {
        assertEquals("a", FoodFastUtils.anonymize("a"));
        assertEquals("Z", FoodFastUtils.anonymize("Z"));
    }

    @Test
    public void testAnonymizeReversible() {
        String original = "FoodFast";
        String anonymized = FoodFastUtils.anonymize(original);
        String restored = FoodFastUtils.anonymize(anonymized);
        
        assertEquals(original, restored);
    }

    @Test
    public void testAnonymizePalindrome() {
        String palindrome = "kayak";
        assertEquals(palindrome, FoodFastUtils.anonymize(palindrome));
        
        String anotherPalindrome = "radar";
        assertEquals(anotherPalindrome, FoodFastUtils.anonymize(anotherPalindrome));
    }

    @Test
    public void testAnonymizeSpecialCharacters() {
        assertEquals("!dlrow ,olleH", FoodFastUtils.anonymize("Hello, world!"));
        assertEquals("321-CBA", FoodFastUtils.anonymize("ABC-123"));
    }

    @Test
    public void testAnonymizeNumbers() {
        assertEquals("54321", FoodFastUtils.anonymize("12345"));
        assertEquals("100C", FoodFastUtils.anonymize("C001"));
    }

}
