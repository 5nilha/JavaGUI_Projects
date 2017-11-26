import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreatePaycheckStage extends PaycheckFile {

	Stage paycheckStage;
	protected static Employee employee = new Employee();

	private TextField firstNameField;
	private TextField lastNameField;
	private TextField birthField;
	private TextField addressField;
	private TextField stateField;
	private TextField experienceField;

	public void setCreatePaycheckStage(Stage stage) {

		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setAlignment(Pos.CENTER);

		// Labels
		Label firstNameLabel = new Label("Firstname");
		Label lastNameLabel = new Label("Lastname");
		Label birthLabel = new Label("Date of Birth");
		Label addressLabel = new Label("Street Address");
		Label stateLabel = new Label("City, State, Zipcode");
		Label experienceLabel = new Label("Year of Experience");

		// Text Fields
		firstNameField = new TextField();
		lastNameField = new TextField();
		birthField = new TextField();
		addressField = new TextField();
		stateField = new TextField();
		experienceField = new TextField();

		firstNameField.setText("");
		lastNameField.setText("");
		birthField.setText("");
		addressField.setText("");
		stateField.setText("");
		experienceField.setText("");

		// Button to open a file
		Button chooseFile = new Button("Choose a File");
		chooseFile.setOnAction(e -> chooseAFileClicked(stage));
		chooseFile.setAlignment(Pos.CENTER);

		Button saveBtn = new Button("Visualize Paycheck");
		saveBtn.setOnAction(e -> printBtnClicked());
		saveBtn.setAlignment(Pos.CENTER_LEFT);

		Button clearBtn = new Button("Clear");
		clearBtn.setOnAction(e -> clearBtnClicked());
		clearBtn.setAlignment(Pos.CENTER_LEFT);

		pane.add(firstNameLabel, 0, 0);
		pane.add(firstNameField, 1, 0);
		pane.add(lastNameLabel, 3, 0);
		pane.add(lastNameField, 4, 0);
		pane.add(addressLabel, 0, 1);
		pane.add(addressField, 1, 1);
		pane.add(stateLabel, 3, 1);
		pane.add(stateField, 4, 1);
		pane.add(birthLabel, 0, 2);
		pane.add(birthField, 1, 2);
		pane.add(experienceLabel, 3, 2);
		pane.add(experienceField, 4, 2);
		pane.add(chooseFile, 3, 4);
		pane.add(saveBtn, 4, 4);
		pane.add(clearBtn, 0, 4);

		Scene scene = new Scene(pane, 700, 500);
		stage.setScene(scene);
		paycheckStage = stage;
		stage.show();
	}

	// Button on CreatePaycheck Stage
	public void printBtnClicked() {

		employee.setName(firstNameField.getText().toString() + " " + lastNameField.getText().toString());
		employee.setAddress(addressField.getText().toString());
		employee.setAddress2(stateField.getText().toString());
		employee.setDateOfBirth(birthField.getText().toString());

		try {
			employee.setYearsOfExperience(Integer.parseInt(checkExperienceField(experienceField.getText().toString())));
		} catch (Exception e) {
			System.out.println("A wrong value was entered");
		}

		double currentSalary;
		if (employee.getYearsOfExperience() <= getTopPlusExperience()) {
			currentSalary = salaryCheat.get(employee.getYearsOfExperience());
		} else {
			currentSalary = getTopPlusIncome();
		}

		employee.setSalary(currentSalary);

		System.out.println("Current Salary : " + employee.getSalary());
		System.out.println("Current TAx : " + employee.getIncomeTax());
		System.out.println("Current SS : " + employee.getSocialSecurityTax());
		System.out.println(salaryCheat);

		PaycheckListStage paycheckListStage = new PaycheckListStage();
		paycheckListStage.setPaycheckListStage(paycheckStage, employee);
	}

	public void chooseAFileClicked(Stage stage) {
		new WelcomeStage().setWelcomeStage(stage);
	}

	public void clearBtnClicked() {
		firstNameField.setText("");
		lastNameField.setText("");
		birthField.setText("");
		addressField.setText("");
		stateField.setText("");
		experienceField.setText("");
	}

	public String checkExperienceField(String experienceYears) {
		String regex = "\\d+";
		String years = "";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(experienceYears);

		while (matcher.find()) {
			years = matcher.group().trim();
		}
		return years;
	}

}
