package com.codewithmosh;

public class taxReport {
    private TaxCalculator2018 calculator ;
    public taxReport(TaxCalculator calculator){
        this.calculator= (TaxCalculator2018) calculator;

    }

    public void show(){
        var tax = calculator.calculateTax() ;
        System.out.println(tax);
    }

}
