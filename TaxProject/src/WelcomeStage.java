import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class WelcomeStage {

	private Text fileName;

	public void setWelcomeStage(Stage stage) {
		StackPane rootPane = new StackPane();
		BorderPane mainPane = new BorderPane();
		mainPane.setPadding(new Insets(10, 20, 10, 20));

		VBox vbox = new VBox(20);
		mainPane.setTop(vbox);

		Label header = new Label("Load a File From your Computer");
		header.setFont(Font.font(null, FontWeight.BOLD, 20));
		vbox.getChildren().add(header);
		vbox.setAlignment(Pos.TOP_CENTER);

		Separator lineSeparator = new Separator();
		lineSeparator.setOrientation(Orientation.HORIZONTAL);
		vbox.getChildren().add(lineSeparator);

		Label header2 = new Label("\n\nCHOOSE A FILE");
		header2.setFont(Font.font(null, FontWeight.BOLD, 29));
		vbox.getChildren().add(header2);
		vbox.setAlignment(Pos.TOP_CENTER);

		Label body = new Label(
				"Before create a Paycheck, click on the button \"Choose a file\"\n to open a file with years of experience and salary related to it.");
		body.setFont(Font.font(null, FontWeight.BOLD, 14));
		vbox.getChildren().add(body);

		GridPane centerPane = new GridPane();
		centerPane.setHgap(10);
		centerPane.setVgap(10);
		centerPane.setAlignment(Pos.CENTER);

		// Button to open a file
		Button chooseFile = new Button("      Choose a File     ");
		chooseFile.setOnAction(new PaycheckFileReader());
		chooseFile.setAlignment(Pos.CENTER);

		// Button to Create an Employee Paycheck
		Button createPaycheck = new Button("   Create Paycheck  ");
		createPaycheck.setOnAction(e -> createPaycheckClicked(stage));

		fileName = new Text("");

		centerPane.add(chooseFile, 2, 15);
		centerPane.add(fileName, 3, 15);
		centerPane.add(createPaycheck, 2, 16);

		rootPane.getChildren().addAll(mainPane, centerPane);
		Scene scene = new Scene(rootPane, 700, 500);
		new PrintPaycheck().setDialogStage(stage);

		stage.setScene(scene);
		stage.show();
	}

	// buttons on welcome stage
	public void createPaycheckClicked(Stage stage) {
		new CreatePaycheckStage().setCreatePaycheckStage(stage);
	}

	// Inner class of the button to read the file
	public class PaycheckFileReader implements EventHandler<ActionEvent> {

		private FileChooser fileChooser = new FileChooser();
		private File selectedFile;

		@Override
		public void handle(ActionEvent event) {

			try {
				selectFile();
				System.out.println("Reading file ...");
			} catch (Exception e) {
				System.out.println("Loading file canceled");
				fileName.setText("No file Selected.");
			}
		}

		public File getSelectedFile() {
			return selectedFile;
		}

		public void selectFile() {
			selectedFile = fileChooser.showOpenDialog(null);

			if (selectedFile != null) {
				fileName.setText("File selected: " + selectedFile.getName());
			} else {
				fileName.setText("No file Selected.");
			}

			new PaycheckFile().parseFile(selectedFile);

		}
	}

}
