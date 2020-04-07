package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class Main extends Application {
 
    @Override
    public void start(Stage primaryStage) {
        Scene scene = null;//try문 밖에서 더 오래 변수 유지위한 조치
        try {
            Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
            scene = new Scene(root);//parent type의 root를 scene에 부착
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        primaryStage.setScene(scene);
        primaryStage.setTitle("창제목");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}