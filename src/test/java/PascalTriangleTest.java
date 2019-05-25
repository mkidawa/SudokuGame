import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class PascalTriangleTest {

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testTriangleNegative() {
        System.out.println("Test Trójkąta Pascala dla liczby ujemnej");
        int n = -3;
        PascalTriangle instance = new PascalTriangle();
        instance.compute(n);
    }
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testTriangleZero() {
        System.out.println("Test Trójkąta Pascala dla n=0");
        int n = 0;
        PascalTriangle instance = new PascalTriangle();
        instance.compute(n);
    }
    @Test()
    public void testTriangleOne() {
        System.out.println("Test Trójkąta Pascala");
        int n = 1;
        PascalTriangle instance = new PascalTriangle();
        instance.compute(n);
        int[][] triangle = instance.getTriangleState();
        int[][] expTriangle = {{1}};
        Assert.assertArrayEquals(expTriangle, triangle);
    }
    @Test()
    public void testTriangleNormal() {
        System.out.println("Test Trójkąta Pascala");
        int n = 4;
        PascalTriangle instance = new PascalTriangle();
        instance.compute(n);
        int[][] triangle = instance.getTriangleState();
        int[][] expTriangle = {{1,0,0,0},{1,1,0,0},{1,2,1,0},{1,3,3,1}};
        Assert.assertArrayEquals(expTriangle, triangle);
    }
}