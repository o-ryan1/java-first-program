package com.h2;

import java.time.LocalDate;
import java.time.YearMonth;

public class SavingsCalculator {
    private float[] debits, credits;

    public SavingsCalculator(float[] credits, float[] debits) {
        this.debits = debits;
        this.credits = credits;
    }

    public static void main(String[] args){
        String[] creditsAsString = args[0].split(",");
        String[] debitsAsString = args[1].split(",");

        float[] credits = new float[creditsAsString.length];
        float[] debits = new float[debitsAsString.length];

        for(int i =0; i< credits.length; i++){ credits[i] = Float.parseFloat(creditsAsString[i]); }
        for(int i =0; i< debits.length; i++){ debits[i] = Float.parseFloat(debitsAsString[i]); }

        SavingsCalculator calculator = new SavingsCalculator(credits, debits);

        float netSavings = calculator.calculate();

        System.out.println("Net Savings = " + netSavings + ", remaining days in month = " +
                remainingDaysInMonth(LocalDate.now()));
    }

    public float calculate(){
        float netSavings = sumOfCredits(credits) - sumOfDebits(debits);
        return netSavings;
    }

    private float sumOfCredits(float[] credits){
        float sum = 0.0f;
        for(float credit: credits){
            sum+=credit;
        }
        return sum;
    }

    private float sumOfDebits(float[] debits){
        float sum = 0.0f;
        for(float debit: debits){
            sum+=debit;
        }
        return sum;
    }

    private static int remainingDaysInMonth(LocalDate date){
        YearMonth yearMonth = yearMonth = YearMonth.of(date.getYear(), date.getMonthValue());

        int totalDaysOfMonth = yearMonth.lengthOfMonth();
        int dayOfMonth = date.getDayOfMonth();

        int remainingDays = totalDaysOfMonth - dayOfMonth;
        return remainingDays;

    }
}
