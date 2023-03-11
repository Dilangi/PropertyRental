package list;

import javafx.scene.control.Alert;

public class ErrorHandler {
	public static void errorMsg(String title,String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(msg);
		alert.showAndWait();
	}
	
	public static void successMsg(String title,String msg) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setContentText(msg);
		alert.showAndWait();
	}
}
