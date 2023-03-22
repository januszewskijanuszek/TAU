import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateTest {

    @Test
    void twoPlusTwoEqualsFour(){
        var calculate = new Calculate();
        assertEquals(4, calculate.add(2, 2));
    }
    @Test
    void threePlusEightEqualsEleven(){
        var calculate = new Calculate();
        assertEquals(11, calculate.add(3, 8));
    }
    @Test
    void threeMulEightEqualsTwentyFour(){
        var calculate = new Calculate();
        assertEquals(24, calculate.mul(3, 8));
    }
    @Test
    void fourMulEightEqualsTwentyFour(){
        var calculate = new Calculate();
        assertEquals(32, calculate.mul(4, 8));
    }
    @Test
    void fourDivZeroEqualsTwentyZero(){
        var calculate = new Calculate();
        assertEquals(0, calculate.div(4, 0));
    }
    @Test
    void fourDivOneEqualsTwentyFour(){
        var calculate = new Calculate();
        assertEquals(4, calculate.div(4, 1));
    }
    @Test
    void twoSubTwoEqualsZero(){
        var calculate = new Calculate();
        assertEquals(0, calculate.sub(2, 2));
    }
    @Test
    void twoSubTenEqualsZero(){
        var calculate = new Calculate();
        assertEquals(-8, calculate.sub(2, 10));
    }
}