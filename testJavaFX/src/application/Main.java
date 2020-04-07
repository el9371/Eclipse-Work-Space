package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class Main extends Application {
 
    @Override
    public void start(Stage primaryStage) {
        Scene scene = null;//try�� �ۿ��� �� ���� ���� �������� ��ġ
        try {
            Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
            scene = new Scene(root);//parent type�� root�� scene�� ����
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        primaryStage.setScene(scene);
        primaryStage.setTitle("â����");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}