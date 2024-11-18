import org.example.Calculator;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(2, calculator.add(1, 1)); // Kiểm tra 1 + 1 = 2
    }

    @Test
    public void testSubtract() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(0, calculator.subtract(1, 1));
    }
}
