import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaycheckFile /*extends CreatePaycheckStage*/ {

	public static TreeMap <Integer, Double> salaryCheat = new TreeMap<Integer, Double>();
	private static int topPlusExperience;
	private static double topPlusIncome;
	
	// Parsing the file
	public void parseFile(File file) {

		try {
			// Reading the file selected
			Scanner scanner = new Scanner(file);

			// Creating a regex's pattern to get the salary from file
			String regexSalaryPattern = "[$]\\d+[,]\\d+";
			Pattern checkSalaryRegex = Pattern.compile(regexSalaryPattern);

			// Creating a regex's pattern to get year's of experience from file
			String regexExperiencePattern = "(\\d[-]\\d+|\\d+[+]?)";
			Pattern checkExperienceRegex = Pattern.compile(regexExperiencePattern);
			String[] year;

			while (scanner.hasNextLine()) {

				String input = scanner.nextLine();
				Matcher salaryMatcher = checkSalaryRegex.matcher(input);
				Matcher experienceMatcher = checkExperienceRegex.matcher(input);

				while (experienceMatcher.find() && salaryMatcher.find()) {
					String e = experienceMatcher.group().trim();
					String s = salaryMatcher.group().trim();
					System.out.println(s);

					String years = "";
					years = e.replace("-", " ").replace("+", "");

					System.out.println(years);
					year = years.split("\\s");

					double income;
					income = Double.parseDouble(s.replace("$", "").replace(",", ""));

					if (year.length > 1) {
						int minExperience = Integer.parseInt(year[0]);
						int maxExperience = Integer.parseInt(year[1]);

						if (minExperience < maxExperience) {
							while (minExperience <= maxExperience) {
								addToSalaryCheat(minExperience, income);
								minExperience++;
							}
						}
					} else if (year.length <= 1) {
						int topPlusExperience = Integer.parseInt(year[0]);
						setTopPlusExperience(topPlusExperience);
						setTopPlusIncome(income);
						System.out.println("Top experience : " + topPlusExperience);
							
					}

				}

			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file found!");
		}

	}
	
	public static void addToSalaryCheat(Integer key, Double value) {
		int experience = key;
		double salary = value;
		
		System.out.println("Exp: " + experience);
		System.out.println("Sal: " + salary);
		salaryCheat.put(experience, salary);
	}
	
	public void setTopPlusExperience(int experience) {
		topPlusExperience = experience;
	}
	
	public static int getTopPlusExperience() {
		return topPlusExperience;
	}
	
	public void setTopPlusIncome(double income) {
		topPlusIncome = income;
	}
	
	public static double getTopPlusIncome() {
		return topPlusIncome;
	}
	
	

}