public class Paycheck {

	private double salary;
	private double incomeTax;
	private double socialSecurityTax;
	private final double SOCIALSECURITY_RATE = 6.2 / 100;
	private double medicareTax;
	private final double MEDICARE_RATE = 1.45 / 100;
	private double netPay;

	public void setSalary(double salary) {
		this.salary = salary;
		setIncomeTax(salary);
		setSocialSecurityTax(salary);
		setMedicareTax();
		setNetPay();
	}

	public Double getSalary() {
		return salary;
	}

	private void setIncomeTax(double salary) {

		if (salary > 0 && salary <= 9325) {
			this.incomeTax = salary * 0.10;
		} else if (salary > 9325 && salary <= 37950) {
			this.incomeTax = 932.50 + ((salary - 9325) * 0.15);
		} else if (salary > 37950 && salary <= 91900) {
			this.incomeTax = 5226.25 + ((salary - 37950) * 0.25);
		} else if (salary > 91900 && salary <= 191650) {
			this.incomeTax = 18713.75 + ((salary - 91900) * 0.28);
		} else if (salary > 191650 && salary <= 416700) {
			this.incomeTax = 46643.75 + ((salary - 191650) * 0.33);
		} else if (salary > 416700 && salary <= 418400) {
			this.incomeTax = 120910.25 + ((salary - 416700) * 0.35);
		} else if (salary > 418400) {
			this.incomeTax = 121505.25 + ((salary - 418400) * 0.396);
		}

	}

	public Double getIncomeTax() {
		return this.incomeTax;
	}

	private void setSocialSecurityTax(double salary) {
		this.socialSecurityTax = salary * SOCIALSECURITY_RATE;
	}

	public Double getSocialSecurityTax() {
		return socialSecurityTax;
	}

	private void setMedicareTax() {
		this.medicareTax = salary * MEDICARE_RATE;
	}

	public Double getMedicareTax() {
		return medicareTax;
	}

	public Double getNetPay() {
		return netPay;
	}

	public void setNetPay() {
		this.netPay = salary - incomeTax - socialSecurityTax - medicareTax;
	}

	public void getPaycheckSample() {
		System.out.println("Salary : " + getSalary());
		System.out.println("Income Tax : " + getIncomeTax());
		System.out.println("Social Security : " + getSocialSecurityTax());
		System.out.println("Medicare : " + getMedicareTax());
	}
	

}
