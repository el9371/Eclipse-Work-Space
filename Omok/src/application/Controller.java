package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.*;
import javafx.stage.Stage;

public class Controller implements Initializable{
	//-------------------------------------------
	// FXML FIELD
	//-------------------------------------------
	@FXML private Button testButton;
	@FXML private AnchorPane mainPage;
	@FXML private ImageView background;
	@FXML private TextArea systemMessage;
	//-------------------------------------------
	// FXML FIELD
	//-------------------------------------------
	private GlassRobot robot = new GlassRobotImpl();
	private int board[][] = new int[19][19]; //x축 y축 순서
	static Stage stage = null;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < 19; i++)
			for(int j = 0; j < 19; j++)
				board[i][j] = 0;
		background.setOnMousePressed(clickOnBoard);
	}
	
	public void testButton() {
		testButton.setVisible(false);
		clearBoard();
	}
	
	public void clearBoard() {
		for (int i = 0; i < 19; i++)
			for(int j = 0; j < 19; j++)
				board[i][j] = 0;
		mainPage.getChildren().remove(2, mainPage.getChildren().size());
	}
	public void addSystemMessage(String text) {
		systemMessage.setText(systemMessage.getText() + "\n" + text);
	}
	public void addSystemMessage(int text) {
		systemMessage.setText(systemMessage.getText() + "\n" + text);
	}
	public void addSystemMessage(double text) {
		systemMessage.setText(systemMessage.getText() + "\n" + text);
	}
	
	//-------------------------------------------
	// Handler for click on omok board
	//-------------------------------------------
	EventHandler<MouseEvent> clickOnBoard = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Point2D point = robot.getMouseLocation();
			int y = (int)point.getY() - 40 - (int)stage.getY();
			int x = (int)point.getX() - 14 - (int)stage.getX();
			int yDevided = y / 21, xDevided = x / 21, yRemains = y % 21, xRemains = x % 21;
			if (yRemains <= 10) yRemains = 0;
			else yRemains = 1;
			if (xRemains <= 10) xRemains = 0;
			else xRemains = 1;
			int xIndex = xDevided + xRemains, yIndex = yDevided + yRemains;
			if (xIndex < 0) xIndex = 0; if (xIndex > 18) xIndex = 18;
			if (yIndex < 0) yIndex = 0; if (yIndex > 18) yIndex = 18;
			/* for setting 
			System.out.println("-----------------");
			System.out.println(xDevided+"    "+xRemains+"    "+xIndex);
			System.out.println(yDevided+"    "+yRemains+"    "+yIndex);
			System.out.println("-----------------");
			*/
			if (board[xIndex][yIndex] != 0)
				return;
			
	        try {
	        FileInputStream imageFile = new FileInputStream("image\\black.png");
	        Image black = new Image(imageFile, 15, 15, false, false);
	        ImageView blackStone = new ImageView(black);
	        mainPage.setTopAnchor(blackStone, (double)(yDevided + yRemains) * 21.0 + 2.0);
	        mainPage.setLeftAnchor(blackStone, (double)(xDevided + xRemains) * 21.0 + 4.0);
	        mainPage.getChildren().add(blackStone);
	        board[xIndex][yIndex] = 1;
	        } catch (FileNotFoundException e) {System.out.println("파일엄서");}
	        
	        if (isOurFive(xIndex, yIndex)) {
	        	addSystemMessage("오목완성!");
	        	testButton.setText("승리!");
	        	testButton.setVisible(true);
	        }
		}
	};
	//-------------------------------------------
	// function for omok rules
	//-------------------------------------------
	public boolean isOurFive(int x, int y) {
		int sum = 0;
		//horizontal
		for (int j = 0; j <5; j++) {
			sum = 0;
			for (int i = 0; i < 5; i++)  {
				if (x-4+j+i < 0 || x-4+j+i > 18 ) break;
				sum = sum + board[x-4+j+i][y];
			}
			if (sum == 5) return true;
		}
		//vertical
		for (int j = 0; j <5; j++) {
			sum = 0;
			for (int i = 0; i < 5; i++)  {
				if (y-4+j+i < 0 || y-4+j+i > 18 ) break;
				sum = sum + board[x][y-4+j+i];
			}
			if (sum == 5) return true;
		}
		//typically diagonal1 (top left to bottom right)
		for (int j = 0; j <5; j++) {
			sum = 0;
			for (int i = 0; i < 5; i++)  {
				if (x-4+j+i < 0 || x-4+j+i > 18 || y-4+j+i < 0 || y-4+j+i > 18) break;
				sum = sum + board[x-4+j+i][y-4+j+i];
			}
			if (sum == 5) return true;
		}
		//typically diagonal1 (top right to bottom left)
		for (int j = 0; j <5; j++) {
			sum = 0;
			for (int i = 0; i < 5; i++)  {
				if (x+4-j-i < 0 || x+4-j-i > 18 || y-4+j+i < 0 || y-4+j+i > 18) break;
				sum = sum + board[x+4-j-i][y-4+j+i];
			}
			if (sum == 5) return true;
		}
		//typically diagonal1 (bottom left to top right)
		for (int j = 0; j <5; j++) {
			sum = 0;
			for (int i = 0; i < 5; i++)  {
				if (x-4+j+i < 0 || x-4+j+i > 18 || y+4-j-i < 0 || y+4-j-i > 18) break;
				sum = sum + board[x-4+j+i][y+4-j-i];
			}
			if (sum == 5) return true;
		}
		//typically diagonal1 (bottom right to top left)
		for (int j = 0; j <5; j++) {
			sum = 0;
			for (int i = 0; i < 5; i++)  {
				if (x+4-j-i < 0 || x+4-j-i > 18 || y-4+j+i < 0 || y-4+j+i > 18) break;
				sum = sum + board[x+4-j-i][y-4+j+i];
			}
			if (sum == 5) return true;
		}

		
		return false;
	}
	
	//-------------------------------------------
	// static function
	//-------------------------------------------
	static void setStage(Stage s) {
		Controller.stage = s;
	}
}
