import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrintPaycheck extends PaycheckListStage implements EventHandler<ActionEvent> {

	private Stage dialogStage;
	private static final String defaultFileName = "MyFile.txt";
	Employee employeePaycheck;

	public PrintPaycheck(Employee employeePaycheck) {
		this.employeePaycheck = employeePaycheck;
	}

	public PrintPaycheck() {

	}

	@Override
	public void handle(ActionEvent event) {
		saveFile();

	}

	private void saveFile() {

		System.out.println(employeePaycheck.getName());

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save File");
		fileChooser.setInitialFileName(defaultFileName);
		File savedFile = fileChooser.showSaveDialog(dialogStage);

		if (savedFile != null) {
			try {
				writingToFile(savedFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void writingToFile(File file) throws IOException {

		file.createNewFile();
		FileWriter writer = new FileWriter(file);
		
		writer.write("*********************** Employee Paycheck ***************************\n\n\n");
		writer.write(employee.getName() + "                      " + "Salary:" + "$" + employee.getSalary() + "\n\n");
		writer.write(employee.getAddress() + "                     " + "**All Tax breakdown" + "\n\n");
		writer.write(employee.getAddress2() + "                   " + "Net pay:" + "$" + employee.getNetPay() + "\n");
		writer.close();

	}

	public void setDialogStage(Stage stage) {
		this.dialogStage = stage;
	}

}
