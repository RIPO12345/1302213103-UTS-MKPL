package lib;

public class TaxFunction {
    public TaxFunction() {
    }

    public static int calculateTax(Income monthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        int taxableIncome = calculateTaxableIncome(monthlyIncome, numberOfMonthWorking, deductible, isMarried, numberOfChildren);
        return calculateTaxAmount(taxableIncome);
    }

    private static int calculateTaxableIncome(Income monthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        int totalIncome = monthlyIncome.getMonthlySalary() + monthlyIncome.getOtherMonthlyIncome();
        totalIncome *= numberOfMonthWorking;
        int taxFreeIncome = calculateTaxFreeIncome(isMarried, numberOfChildren);
        return totalIncome - deductible - taxFreeIncome;
    }

    private static int calculateTaxFreeIncome(boolean isMarried, int numberOfChildren) {
        int taxFreeIncome = 54000000;
        if (isMarried) {
            taxFreeIncome += 4500000;
        }
        taxFreeIncome += numberOfChildren * 4500000;
        return taxFreeIncome;
    }

    private static int calculateTaxAmount(int taxableIncome) {
        double taxPercentage = 0.05;
        int taxAmount = (int) Math.round(taxPercentage * (double) taxableIncome);
        return Math.max(taxAmount, 0);
    }

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int monthWorkingInYear,
            int annualDeductible, boolean equals, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateTax'");
    }
}

class Income {
    private int monthlySalary;
    private int otherMonthlyIncome;

    public Income(int monthlySalary, int otherMonthlyIncome) {
        this.monthlySalary = monthlySalary;
        this.otherMonthlyIncome = otherMonthlyIncome;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public int getOtherMonthlyIncome() {
        return otherMonthlyIncome;
    }
}
