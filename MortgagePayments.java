import java.text.NumberFormat;
import java.util.Scanner;

public class MortgagePayments {
  final static byte MONTHS_IN_YEAR = 12;
  final static byte PERCENT = 100;

  public static void main(String[] args) {
    int principal = (int) readNumber("Principal (min: 1000, max: 1_000_000) ? ", 1000, 1_000_000);
    float annualInterest = (float) readNumber("Annual Interest Rate (min: 1, max: 30)", 1, 30);
    byte years = (byte) readNumber("Period (Years)? (min: 1, max: 30) : ", 1, 30);

    double mortgage = calculateMortgage(principal, annualInterest, years);
    String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
    System.out.println("\n MORTGAGE \n ----------------------");
    System.out.println("Monthly Payments : " + mortgageFormatted + "\n");

    printPaymentSchedule(principal, annualInterest, years);

  }

  private static void printPaymentSchedule(int principal, float annualInterest, byte years) {
    System.out.println("PAYMENT SCHEDULE \n ----------------------");
    for (short month = 1; month <= years * MONTHS_IN_YEAR; month++) {
      double balance = calculateBalance(principal, annualInterest, years, month);
      System.out.println(NumberFormat.getCurrencyInstance().format(balance));
    }
  }

  public static double calculateBalance(
      int principal,
      float annualInterest,
      byte years,
      short numberOfPaymentsMade) {

    float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
    short numberOfPayments = (short) (years * MONTHS_IN_YEAR);
    double balance = principal
        * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
        / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
    return balance;
  }

  /**
   * calculateMortgage method
   */
  public static double calculateMortgage(
      int principal,
      float annualInterest,
      byte years) {

    float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
    short numberOfPayments = (short) (years * MONTHS_IN_YEAR);

    double mortgage = principal
        * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments)
            / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1));

    return mortgage;
  }

  public static double readNumber(String prompt, double min, double max) {
    Scanner scanner = new Scanner(System.in);
    double value;
    while (true) {
      System.out.println(prompt);
      value = scanner.nextFloat();
      if (value >= min && value <= max)
        break;

      System.out.println("Enter a  value between" + min + " and " + max);

    }
    return value;

  }

}