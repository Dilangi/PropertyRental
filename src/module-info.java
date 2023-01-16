module PropertyRental {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens model to javafx.graphics, javafx.fxml;
	opens view to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;
}
