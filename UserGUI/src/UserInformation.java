import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserInformation extends Application {

	static Stage stage;

	private static ArrayList<Person> users = new ArrayList<Person>();
	private static TextField firstNameField = new TextField();
	private static TextField lastNameField = new TextField();
	private static TextField emailField = new TextField();
	private static TextField phoneField = new TextField();
	private static Label labelWarning = new Label();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		registrationScene();
	}

	// Create the primary Scene registrationScene to register new users
	private static void registrationScene() {

		// Creating the Pane to the main scene
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(10);
		pane.setVgap(10);

		// Creating the label to the main scene
		Label labelFirstName = new Label("First Name");
		Label labelLastName = new Label("Last Name");
		Label labelEmail = new Label("Email");
		Label labelPhone = new Label("Phone");

		// Creating buttons to the main scene
		Button okBtn = new Button("OK");
		Button showUsersBtn = new Button("Show Users");
		Button cancelBtn = new Button("Cancel");

		// Adding the nodes to the pane of the main scene
		pane.add(labelFirstName, 0, 0);
		pane.add(firstNameField, 1, 0);
		pane.add(labelLastName, 0, 1);
		pane.add(lastNameField, 1, 1);
		pane.add(labelEmail, 0, 2);
		pane.add(emailField, 1, 2);
		pane.add(labelPhone, 0, 3);
		pane.add(phoneField, 1, 3);
		pane.add(labelWarning, 1, 4);

		// adds the buttons to the pane and align them
		pane.add(cancelBtn, 0, 5);
		pane.add(showUsersBtn, 1, 5);
		GridPane.setHalignment(showUsersBtn, HPos.LEFT);
		pane.add(okBtn, 1, 5);
		GridPane.setHalignment(okBtn, HPos.RIGHT);

		// Set buttons action
		cancelBtn.setOnAction(e -> stage.close());
		okBtn.setOnAction(e -> okBtnClicked());
		
		// showUsersBtn.setOnAction(e -> showButtonClicked(stage));
		showUsersBtn.setOnAction(e -> showButtonClicked());

		// Creating the main scene
		Scene scene1 = new Scene(pane, 600, 600);

		stage.setScene(scene1);
		stage.setTitle("User Registration");
		stage.show();

	}

	// Create the second scene
	private static void secondScene() {
		// Creating a pane
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setPadding(new Insets(15, 15, 15, 15));
		gridPane.setHgap(35);
		gridPane.setVgap(20);

		// Labels for the second scene
		Label nameLabel = new Label("FULL NAME ");
		Label emailLabel = new Label("EMAIL ADDRESS");
		Label phoneLabel = new Label("PHONE NUMBER");

		// Button to back to user registration scene
		Button registerUserBtn = new Button("Register new User");
		registerUserBtn.setOnAction(e -> registerUserBtnClicked());

		gridPane.add(nameLabel, 0, 0);
		gridPane.add(emailLabel, 1, 0);
		gridPane.add(phoneLabel, 2, 0);

		int row = 1, col = 0;
		for (Person user : users) {
			gridPane.add(new Label(user.getFullName()), col, row);
			gridPane.add(new Label(user.getEmail()), col + 1, row);
			gridPane.add(new Label(user.getPhone()), col + 2, row);
			row++;
		}
		gridPane.add(registerUserBtn, 0, row + 1);
		GridPane.setHalignment(registerUserBtn, HPos.LEFT);

		Scene scene2 = new Scene(gridPane, 600, 600);
		stage.close();
		stage.setScene(scene2);
		stage.setTitle("Users List");
		stage.show();

	}

	// The Button action to Register a user and add it to the list
	private static void okBtnClicked() {
		Person user = new Person();

		if ((firstNameField.getText().trim().length() != 0) && (lastNameField.getText().trim().length() != 0)
				&& (emailField.getText().trim().length() != 0) && (phoneField.getText().trim().length() != 0)) {
			user.setFirstName(firstNameField.getText().toString());
			user.setLastName(lastNameField.getText().toString());
			user.setEmail(emailField.getText().toString());

			try {
				user.setPhone(phoneField.getText().toString());
				labelWarning.setText("");
			} catch (InvalidTelephoneException ex) {
				labelWarning.setText(ex.getMessage());
			}

		} else {
			labelWarning.setText("No field can empty");
		}

		addUserToList(user);

		// Empty the fields
		firstNameField.setText("");
		lastNameField.setText("");
		emailField.setText("");
		phoneField.setText("");

	}

	public static void showButtonClicked() {

		stage.setTitle("List of Users");
		sortList();
		secondScene();

	}

	// When the registerUserBtnClicked() is clicked, we go back to registration scene
	public static void registerUserBtnClicked() {
		registrationScene();
	}

	// Adds the new user to the list of users
	private static void addUserToList(Person user) {
		users.add(user);
	}

	// Sort the list using the first name as reference
	private static void sortList() {

		Collections.sort(users);
		for (Person user : users) {
			System.out.println(user.getFirstName());
		}
	}

}
