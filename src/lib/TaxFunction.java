package lib;

public class TaxFunction {

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
			boolean isMarried, int numberOfChildren) {
		int taxableIncome = calculateTaxableIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking, deductible,
				isMarried, numberOfChildren);
		return calculateTaxAmount(taxableIncome);
	}

	private static int calculateTaxableIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking,
			int deductible, boolean isMarried, int numberOfChildren) {
		int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
		int taxFreeIncome = calculateTaxFreeIncome(isMarried, numberOfChildren);
		return totalIncome - deductible - taxFreeIncome;
	}

	private static int calculateTaxFreeIncome(boolean isMarried, int numberOfChildren) {
		int taxFreeIncome = 54000000; // Penghasilan tidak kena pajak untuk pegawai yang belum menikah
		if (isMarried) {
			taxFreeIncome += 4500000; // Tambahan penghasilan tidak kena pajak untuk pegawai yang sudah menikah
		}
		taxFreeIncome += numberOfChildren * 4500000; // Tambahan penghasilan tidak kena pajak untuk setiap anak
		return taxFreeIncome;
	}

	private static int calculateTaxAmount(int taxableIncome) {
		double taxPercentage = 0.05;
		int taxAmount = (int) Math.round(taxPercentage * taxableIncome);
		return Math.max(taxAmount, 0); // Pastikan pajak tidak kurang dari nol
	}
}
