

import java.lang.Math;

public class SavingsFormulas {
    public static double futureValueLumpSum(double cash, double interest, int
            years){

        double FV;
        FV = cash*Math.pow(1+interest,years);
        return FV;
    };

    public static double futureValueLS_VariableInterest(double cash, double
            values[]){

        return helperFunction_for_futureValuesLS(cash, values, 0);
    };

    private static double helperFunction_for_futureValuesLS(double cash, double values[], int index){
        if(index == values.length){
            return cash;
        }
        return helperFunction_for_futureValuesLS(cash* (1+ values[index]), values, index+1);
    };

    public static double compoundSavingsConstant(double cash, double
            interest, int years){
        double FVa;
        FVa = cash*(((Math.pow(1+interest,years))-1)/interest);
        return FVa;
    };

    public static double compoundSavingsVariable(double values[], double
            interest){

    double year[];
    double total = values[0];
    for(int i = 1; i < values.length; i++){

        total = total*(1+interest) + values[i];
    }
        return total;
    };
}
