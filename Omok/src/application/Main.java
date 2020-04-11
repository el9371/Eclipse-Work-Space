package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Point2D;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Scene scene = null;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
			scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
