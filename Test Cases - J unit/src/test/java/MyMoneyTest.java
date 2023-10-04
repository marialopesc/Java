import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

class MyMoneyTest {
    static MyMoney s1;
    static MyMoney s2;
    static MyMoney constructorBoth;

    @BeforeAll
    static void setup() {
        s1 = new MyMoney("values.txt", 8, 1);
        s2 = new MyMoney("values2.txt", 8, 1);
        constructorBoth = new MyMoney("values.txt", "values2.txt", 8, 8);
    }

    @Test
    void MyMoney_Test1() {
        double[] cashAmt = {4000, 5500, 15000, 18000, 24000, 9000, 11000, 12000};
        assertArrayEquals(cashAmt, s1.getCashValues(), "Not the same cash values for the first constructor.");
        assertEquals(cashAmt.length, s1.getCashValues().length, "Not the same cash values for the first constructor.");
        assertNotNull(s1.getCashValues(), "Cash values should not be null.");
    }

    //@Test
//    void MyMoney_Test2() {
//        double[] interestRateArray = {.055, .075, .045, .09, .1, .065, .035, .025};
//        assertArrayEquals(interestRateArray, s2.getInterestValues(), "Mismatch in interest rates for the first constructor.");
//        assertEquals(interestRateArray.length, s2.getInterestValues().length, "Mismatch in interest rates' length for the first constructor.");
//        assertNotNull(s2.getInterestValues(), "Interest values should not be null.");
//    }

    @Test
    void MyMoney_SecondContructor_Test1() {
        double[] cashAmt = {4000, 5500, 15000, 18000, 24000, 9000, 11000, 12000};
        assertArrayEquals(cashAmt, constructorBoth.getCashValues(), "Mismatch in cash values for the second constructor.");
        assertEquals(cashAmt.length, constructorBoth.getCashValues().length, "Mismatch in cash values' length for the second constructor.");
        assertNotNull(constructorBoth.getCashValues(), "Cash values array seems to be empty.");
    }

    @Test
    void MyMoney_SecondContructor_Test2() {
        double[] interestRateArray = {.055, .075, .045, .09, .10, .065, .035, .025};
        assertNotNull(constructorBoth.getInterestValues(), "Interest values array seems to be empty.");
        assertArrayEquals(interestRateArray, constructorBoth.getInterestValues(), "Mismatch in interest rates for the second constructor.");
        assertEquals(interestRateArray.length, constructorBoth.getInterestValues().length, "Mismatch in interest rates' length for the second constructor.");
    }

    @Test //passed
    void lumpSum_ConstantRate_Test1() {
        assertEquals(18133, Math.round(constructorBoth.lumpSum_ConstantRate(12000, .035, 12)), "Incorrect result for lumpSum_ConstantRate in second test.");
    }

    @Test //passed
    void lumpSum_ConstantRate_Test2() {
        assertEquals(5228, Math.round(constructorBoth.lumpSum_ConstantRate(4000, .055, 5)), "Incorrect result for lumpSum_ConstantRate in second test.");
    }

    @Test //passed
    void lumpSum_VariableRate_Test1() {
        assertEquals(14057, Math.round(constructorBoth.lumpSum_VariableRate(8755.45)), "Incorrect result for lumpSum_VariableRate in first test.");
    }

    @Test //passed
    void lumpSum_VariableRate_Test2() {
        assertEquals(16401, Math.round(constructorBoth.lumpSum_VariableRate(10215.45)), "Incorrect result for lumpSum_VariableRate in second test.");
    }

    @Test //passed
    void compoundSavings_sameContribution_Test1() {
        assertEquals(511069796, Math.round(constructorBoth.compoundSavings_sameContribution(9000, 0.65, 21)), "Incorrect result for compoundSavings_sameContribution in first test.");
    }

    @Test //passed
    void compoundSavings_sameContribution_Test2() {
        assertEquals(24000, Math.round(constructorBoth.compoundSavings_sameContribution(24000, 0.1, 1)), "Incorrect result for compoundSavings_sameContribution in second test.");
    }

    @Test //passed
    void compoundSavings_variableContribution_Test1() {
        assertEquals(203319, Math.round(constructorBoth.compoundSavings_variableContribution(0.23)), "Incorrect result for compoundSavings_variableContribution in first test.");
    }

    @Test //passed
    void compoundSavings_variableContribution_Test2() {
        assertEquals(148398, Math.round(constructorBoth.compoundSavings_variableContribution(0.13)), "Incorrect result for compoundSavings_variableContribution in second test.");
    }

    static int cashIndex = 0;
    @ParameterizedTest //to execute the same test over and over again using different values
    @ValueSource(doubles = {4000, 5500, 15000, 18000, 24000, 9000, 11000, 12000})
    void getCashValues_Test1(double expectedValue) {
        double[] cashValuesArray = s1.getCashValues();
        assertEquals(expectedValue, cashValuesArray[cashIndex], "Mismatch in cash values array.");

        cashIndex++;
    }

    static int interestIndex = 0;
    @ParameterizedTest //to execute the same test over and over again using different values
    @ValueSource(doubles = {.055, .075, .045, .09, .10, .065, .035, .025})
    void getCashValues_Test2(double expectedInterest) {
        double[] interestValuesArray = constructorBoth.getInterestValues();
        assertEquals(expectedInterest, interestValuesArray[interestIndex], "Mismatch in interest rates array.");

        interestIndex++;
    }

    @Test
    void getInterestValues_Test1() {
        double[] interestRateArray = {.055, .075, .045, .09, .10, .065, .035, .025};
        assertArrayEquals(interestRateArray, constructorBoth.getInterestValues(), "Mismatch in values in the interest rates array.");
    }

    @Test
    void getInterestValues_Test2() {
        double[] interestRateArray = {.055, .075, .045, .09, .10, .065, .035, .025};
        assertEquals(interestRateArray.length, constructorBoth.getInterestValues().length, "Mismatch in length of the interest rates array.");
    }
}
