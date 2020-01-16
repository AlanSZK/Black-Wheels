package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
	
	
	
	
	@Override
	public void start(Stage PrimaryStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/login.fxml"));
			Scene login = new Scene(root,400,400);
			login.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			PrimaryStage.setTitle("Black Wheels Login");
			PrimaryStage.setScene(login);
			PrimaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
