import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/*
cash to test:
4000
5500
15000
18000
24000
9000
11000
12000

interest to test:
.055
.075
.045
.09
.10
.065
.035
.025
 */


class SavingsMethodTest {
    static MyMoney s1;
    static MyMoney s2;
    static MyMoney constructor;

    @BeforeAll
    static void setUp() {
        s1 = new MyMoney("values.txt", 8,1);
        s2 = new MyMoney("values2.txt", 8,2);
        constructor = new MyMoney("values.txt", "values2.txt", 8, 8);
    }

    //Yes. Now, If any method needs cash or interest values array, you can pass in constructor.getCashValues() or constructor.getInterestValues()
    //tests for futureValueLumpSum
    @Test
    void futureValueLumpSum_Test1() {
        assertEquals(4697,Math.round(SavingsFormulas.futureValueLumpSum(4000, 0.055, 3)), "Wrong value expected.");
    }

    @Test
    void futureValueLumpSum_Test2(){
        assertEquals(18133, Math.round(SavingsFormulas.futureValueLumpSum(12000, .035, 12)), "Wrong value expected.");
    }

    //tests for recFV_VariableHelper #1
    @Test
    void futureValueLS_VariableInterest_Test1() {
        assertEquals(14057, Math.round(SavingsFormulas.futureValueLS_VariableInterest(8755.45, constructor.getInterestValues())), "Wrong value expected.");
    }

    //tests for recFV_VariableHelper #2
    @Test
    void futureValueLS_VariableInterest_Test2() {
        assertEquals(16401, Math.round(SavingsFormulas.futureValueLS_VariableInterest(10215.45, constructor.getInterestValues())), "Wrong value expected.");
    }

    @Test
    void compoundSavingsConstant_Test1(){
        assertEquals(146758, Math.round(SavingsFormulas.compoundSavingsConstant(12000,0.075, 9)), "Wrong value expected.");
    }

    @Test
    void compoundSavingsConstant_Test2(){
        assertEquals(38967, Math.round(SavingsFormulas.compoundSavingsConstant(5647,0.98, 3)), "Wrong value expected.");
    }

    @Test
    void compoundSavingsVariable_Test1(){
        assertEquals(203319, Math.round(SavingsFormulas.compoundSavingsVariable(s1.getCashValues(), 0.23)),"Wrong value expected.");
    }

    @Test
    void compoundSavingsVariable_Test2() {
        assertEquals(148398, Math.round(SavingsFormulas.compoundSavingsVariable(constructor.getCashValues(), 0.13)), "Wrong value expected.");
    }
}
