/**
 *
 * @author Aleksandra Wnuk | Michał Kidawa
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testAdd_int_int() {
        System.out.println("Test dodawania liczb calkowitych");
        int x = 5;
        int y = 3;
        Calculator instance = new Calculator();
        int expResult = 8;
        int result = instance.add(x, y);
        int overflowResult = instance.add(x, Integer.MAX_VALUE);
        int overflowExpResult = -2147483644;
        assertEquals(expResult, result);
        assertEquals(overflowExpResult, overflowResult);
    }

    @Test
    public void testDiff_int_int() {
        System.out.println("Test odejmowania liczb calkowitych");
        int x = 6;
        int y = 4;
        Calculator instance = new Calculator();
        int expResult = 2;
        int result = instance.diff(x, y);
        int underflowResult = instance.diff(Integer.MIN_VALUE, y);
        int underflowExpResult = 2147483644;
        assertEquals(expResult, result);
        assertEquals(underflowExpResult, underflowResult);
    }

    @Test
    public void testMul_int_int() {
        System.out.println("Test mnożenia liczb calkowitych");
        int x = 4;
        int y = 2;
        Calculator instance = new Calculator();
        int expResult = 8;
        int result = instance.mul(x, y);
        assertEquals(expResult, result);
    }

    @Test(expected=java.lang.ArithmeticException.class)
    public void testDiv_int_int() {
        System.out.println("Test dzielenia liczb calkowitych");
        int x = 6;
        int y = 3;
        int z = 0;
        Calculator instance = new Calculator();
        int expResult = 2;
        int result = instance.div(x, y);
        assertEquals(expResult, result);
        instance.div(x, z);
    }

    @Test(expected=java.lang.ArithmeticException.class)
    public void testDiv_int_zero_by_zero() {
        System.out.println("Test dodawania liczb calkowitych (zero przez zero)");
        int x = 0;
        int y = 0;
        Calculator instance = new Calculator();
        instance.div(x, y);
    }

    @Test
    public void testAdd_double_double() {
        System.out.println("Test dodawania liczb zmiennoprzecinkowych");
        double x = 1.1;
        double y = 2.2;
        Calculator instance = new Calculator();
        double expResult = 3.3;
        double result = instance.add(x, y);
        assertEquals(expResult, result, 0.000001);
    }

    @Test
    public void testDiff_double_double() {
        System.out.println("Test odejmowania liczb zmiennoprzecinkowych");
        double x = 5.0;
        double y = 3.0;
        Calculator instance = new Calculator();
        double expResult = 2.0;
        double result = instance.diff(x, y);
        assertEquals(expResult, result, 0.00001);
    }

    @Test
    public void testMul_double_double() {
        System.out.println("Test mnożenia liczb zmiennoprzecinkowych");
        double x = 1.5;
        double y = 2.0;
        Calculator instance = new Calculator();
        double expResult = 3.0;
        double result = instance.mul(x, y);
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testDiv_double_double() {
        System.out.println("Test dzielenia liczb zmiennoprzecinkowych");
        double x = 4.5;
        double y = 1.5;
        Calculator instance = new Calculator();
        double expResult = 3.0;
        double result = instance.div(x, y);
        assertEquals(expResult, result, 0.0001);
    }
}
