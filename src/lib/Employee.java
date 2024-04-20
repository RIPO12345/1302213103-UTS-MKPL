package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private int yearJoined;
    private int monthJoined;
    private int dayJoined;
    private int monthWorkingInYear;

    private boolean isForeigner;
    private boolean gender; // true = Laki-laki, false = Perempuan

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.yearJoined = yearJoined;
        this.monthJoined = monthJoined;
        this.dayJoined = dayJoined;
        this.isForeigner = isForeigner;
        this.gender = gender;

        childNames = new LinkedList<String>();
        childIdNumbers = new LinkedList<String>();
    }

    // Metode untuk mengatur gaji bulanan berdasarkan grade
    public void setMonthlySalary(int grade) {
        if (grade == 1) {
            monthlySalary = calculateGradeSalary(3000000);
        } else if (grade == 2) {
            monthlySalary = calculateGradeSalary(5000000);
        } else if (grade == 3) {
            monthlySalary = calculateGradeSalary(7000000);
        }
    }

    // Metode untuk menghitung gaji berdasarkan grade dan status warga negara
    private int calculateGradeSalary(int baseSalary) {
        int calculatedSalary = baseSalary;
        if (isForeigner) {
            calculatedSalary = (int) (baseSalary * 1.5);
        }
        return calculatedSalary;
    }

    // Metode untuk mengatur potongan tahunan
    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    // Metode untuk mengatur pemasukan bulanan lainnya
    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    // Metode untuk mengatur informasi pasangan
    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = idNumber;
    }

    // Metode untuk menambahkan informasi anak
    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    // Metode untuk mendapatkan pajak penghasilan tahunan
    public int getAnnualIncomeTax() {
        calculateMonthWorkingInYear();
        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
    }

    // Metode untuk menghitung berapa lama pegawai bekerja dalam setahun ini
    private void calculateMonthWorkingInYear() {
        LocalDate date = LocalDate.now();
        if (date.getYear() == yearJoined) {
            monthWorkingInYear = date.getMonthValue() - monthJoined;
        } else {
            monthWorkingInYear = 12;
        }
    }
}
