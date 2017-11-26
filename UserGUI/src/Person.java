import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person implements Comparable<Person> {

	private String firstName;
	private String lastName;

	private String email;
	private String phone;

	public Person(String firstName, String lastName, String email, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}

	public Person() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) throws InvalidTelephoneException {
		String phoneRegex = "\\d";
		Pattern pattern = Pattern.compile(phoneRegex);
		Matcher regexMatch = pattern.matcher(phone);
		String phoneNumber = "";
		String areaCode;
		String exchange;
		String localNumber;
		String country;
		int count = 0;

		while (regexMatch.find()) {
			phoneNumber += regexMatch.group();
			count++;
		}

		if (count > 9 && count < 11) {
			areaCode = phoneNumber.substring(0, 3);
			exchange = phoneNumber.substring(3, 6);
			localNumber = phoneNumber.substring(6, 10);

			this.phone = "(" + areaCode + ") " + exchange + "-" + localNumber;

		} else if (count > 10 && count < 12 && phoneNumber.substring(0) == "1") {
			country = phoneNumber.substring(0);
			areaCode = phoneNumber.substring(1, 4);
			exchange = phoneNumber.substring(4, 7);
			localNumber = phoneNumber.substring(7, 11);

			this.phone = "+" + country + " (" + areaCode + ") " + exchange + "-" + localNumber;

		} else {
			throw new InvalidTelephoneException("Invalid Number");
		}

	}

	@Override
	public int compareTo(Person user) {
		char compareName = ((Person) user).getFirstName().charAt(0);

		// Compare to sort the Object in Ascending order
		return this.firstName.charAt(0) - compareName;
	}

}
