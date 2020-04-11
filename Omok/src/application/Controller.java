package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.*;

public class Controller implements Initializable{
	//-------------------------------------------
	// PRIVATE FIELD
	//-------------------------------------------
	private GlassRobot robot = new GlassRobotImpl();
	@FXML private AnchorPane mainPage;
	@FXML private ImageView background;
	
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		background.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Point2D point = robot.getMouseLocation();
		        double x = point.getX();
		        double y = point.getY();
		        System.out.println("y = " + y);
		        System.out.println("x = " + x);
		        try {
		        FileInputStream imageFile = new FileInputStream("image\\black.png");
		        Image black = new Image(imageFile);
		        ImageView blackStone = new ImageView(black);
		        System.out.println(background.getScene());
		        } catch (FileNotFoundException e) {System.out.println("파일엄서");}
			}
		});
	}
	
	public void testButton() {
		Point2D point = robot.getMouseLocation();
        double x = point.getX();
        double y = point.getY();
        System.out.println("y = " + y);
        System.out.println("x = " + x);
        Point2D tmp = new Point2D(200.0, 300.0);
		robot.mouseMove(tmp);
	}
	
}
