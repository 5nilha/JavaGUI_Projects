import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class OpeningStage {
	
	public void setOpeningStage(Stage stage) {
		
		StackPane rootPane = new StackPane();
//		rootPane.setStyle("-fx-background-color: gray");
		BorderPane mainPane = new BorderPane();
		mainPane.setPadding(new Insets(10, 20, 10, 20));

		VBox vbox = new VBox(50);
		mainPane.setTop(vbox);
		
		ImageView imageView = new ImageView(new Image("https://lh3.googleusercontent.com/qWESQJseuVPBzzygM9lmpXhmsMVxc9eKYAG_0GxTnpurQbTyxYSp1DAzJ6W93Yql-yzJ=w300"));
		vbox.getChildren().add(imageView);
		vbox.setAlignment(Pos.CENTER);
		
		
		Label welcome = new Label("WELCOME TO PAYCHECKER");
		welcome.setFont(Font.font(null, FontWeight.BOLD, 29));
		vbox.getChildren().add(welcome);
		vbox.setAlignment(Pos.CENTER);
	
		
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setAlignment(Pos.CENTER);

		// Button to Create an Employee Paycheck
		Button enterBtn = new Button("    Enter    ");
		enterBtn.setOnAction(e -> enterBtnClicked(stage));
		enterBtn.setAlignment(Pos.CENTER_LEFT);

		pane.add(enterBtn, 2, 14);
		
		rootPane.getChildren().addAll(mainPane, pane);
		Scene scene = new Scene(rootPane, 700, 500);
		new PrintPaycheck().setDialogStage(stage);

		stage.setScene(scene);
		stage.show();
	}

	// buttons on welcome stage
	public void enterBtnClicked(Stage stage) {
		new WelcomeStage().setWelcomeStage(stage);
	}
}
