import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PaycheckListStage extends CreatePaycheckStage {

	public void setPaycheckListStage(Stage stage, Employee employee) {
		StackPane rootPane = new StackPane();
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10, 20, 10, 20));

		VBox vbox = new VBox(20);
		pane.setTop(vbox);

		Label sceneHeader = new Label("Employee Paycheck Preview");
		sceneHeader.setFont(Font.font(null, FontWeight.BOLD, 29));
		vbox.getChildren().add(sceneHeader);
		vbox.setAlignment(Pos.TOP_CENTER);

		Separator lineSeparator = new Separator();
		lineSeparator.setOrientation(Orientation.HORIZONTAL);
		vbox.getChildren().add(lineSeparator);

		GridPane pane2 = new GridPane();
		pane.setCenter(pane2);
		pane2.setHgap(10);
		pane2.setVgap(15);
		pane2.setAlignment(Pos.CENTER);

		Label nameLbl = new Label("Employee Name : ");
		Label salaryLbl = new Label("Salary : ");
		Label birthLbl = new Label("Birth Date : ");
		Label addressLbl = new Label("Address : ");
		Label yearExperienceLbl = new Label("Years of Experience : ");
		Label netPayLbl = new Label("Net pay : ");
		Label breakdownTax = new Label("**All Tax Breakdown");
		Label ssTax = new Label("Social Security : ");
		Label incomeTax = new Label("Federal Tax: ");
		Label medicareTax = new Label("Medicare : ");

		int row = 2, col = 0;

		pane2.add(nameLbl, col, row);
		pane2.add(new Label(employee.getName()), col + 1, row);

		pane2.add(salaryLbl, col + 3, row);
		pane2.add(new Label("$" + employee.getSalary().toString()), col + 4, row);

		row += 1; // jump a line

		pane2.add(birthLbl, col, row);
		pane2.add(new Label(employee.getDateOfBirth()), col + 1, row);

		pane2.add(yearExperienceLbl, col + 3, row);
		pane2.add(new Label(employee.getYearsOfExperience().toString()), col + 4, row);

		row += 1; // Jump another line

		pane2.add(addressLbl, col, row);
		pane2.add(new Label(employee.getAddress()), col + 1, row);

		row += 1; // Jump another line
		pane2.add(new Label(employee.getAddress2()), col + 1, row);

		pane2.add(netPayLbl, col + 3, row);
		pane2.add(new Label(employee.getNetPay().toString()), col + 4, row);

		row += 1; // Jump another line

		pane2.add(breakdownTax, col + 2, row);
		row += 1; // Jump another line

		pane2.add(incomeTax, col + 2, row);
		pane2.add(new Label(employee.getIncomeTax().toString()), col + 3, row);
		row += 1; // Jump another line

		pane2.add(ssTax, col + 2, row);
		pane2.add(new Label(employee.getSocialSecurityTax().toString()), col + 3, row);
		row += 1; // Jump another line

		pane2.add(medicareTax, col + 2, row);
		pane2.add(new Label(employee.getMedicareTax().toString()), col + 3, row);
		row += 1; // Jump another line

		Button backButton = new Button("Back");
		backButton.setOnAction(e -> backBtnClicked(stage));
		pane2.add(backButton, 2, row + 2);

		Button printBtn = new Button("Print Paycheck");
		printBtn.setOnAction(new PrintPaycheck(employee));
		pane2.add(printBtn, 3, row + 2);

		Scene scene = new Scene(rootPane, 700, 500);
		rootPane.getChildren().addAll(pane, pane2);
		stage.setScene(scene);
		stage.show();

	}

	public void backBtnClicked(Stage stage) {
		new CreatePaycheckStage().setCreatePaycheckStage(stage);
	}

}
