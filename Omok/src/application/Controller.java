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
	@FXML private Button gameButton;
	@FXML private AnchorPane mainPage;
	@FXML private ImageView background;
	@FXML private TextArea systemMessage;
	//-------------------------------------------
	// FXML FIELD
	//-------------------------------------------
	private boolean isEnd = true, userIsBlack; //흑돌이 스타트
	private GlassRobot robot = new GlassRobotImpl();
	private int board[][] = new int[19][19]; //x축 y축 순서
	private int stoneLog[][];
	private int turns, pageChildSize;
	private Tree mainTree;
	static Stage stage = null;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pageChildSize = mainPage.getChildren().size();
		background.setOnMousePressed(clickOnBoard);
		initGame();
	}
	
	public void initGame() {
		mainTree = new Tree();
		for (int i = 0; i < 19; i++)
			for(int j = 0; j < 19; j++)
				board[i][j] = 0;
		this.stoneLog = new int[361][2];
		this.turns = 0;
	}
	
	public void clearBoard() {
		if (pageChildSize != mainPage.getChildren().size())
			mainPage.getChildren().remove(pageChildSize, mainPage.getChildren().size());
		this.isEnd = false;
		initGame();
	}
	
	public void gameButton() {
		clearBoard();
		gameButton.setDisable(true);
		if (Math.random() > 0.5) this.userIsBlack = true;
		else this.userIsBlack = false;
		if (!userIsBlack) computersTurn(!userIsBlack);
			
	}
	
	public int[][] getBoard() {
		return this.board;
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

	public void undo() {
		if (turns < 3) return;
		addSystemMessage(turns);
		int number = mainPage.getChildren().size();
		mainPage.getChildren().remove(number - 2, number);
		for (int i = 0; i < 2; i++) {
			turns--;
			int x = stoneLog[turns][0], y = stoneLog[turns][1];
			board[x][y] = 0;
		} if (this.isEnd) {
			gameButton.setText("START");
			gameButton.setDisable(true);
			this.isEnd = false;
		} if (!this.userIsBlack && turns % 2 == 0)
			computersTurn(!userIsBlack);
		else if(this.userIsBlack && turns % 2 == 1)
			computersTurn(!userIsBlack);
		addSystemMessage("after : " +turns);
	}
	
	public boolean setStone(int xIndex, int yIndex, boolean isBlack) {
		if (isEnd) return false;
		if (board[xIndex][yIndex] != 0)
			return false;
		try {
			String fileName;
			if (isBlack) fileName = "image\\black.png";
			else fileName = "image\\white.png";
			FileInputStream imageFile = new FileInputStream(fileName);
			Image img = new Image(imageFile, 15, 15, false, false);
			ImageView stone = new ImageView(img);
			mainPage.setTopAnchor(stone, (double) yIndex * 21.0 + 2.0);
			mainPage.setLeftAnchor(stone, (double) xIndex * 21.0 + 4.0);
			mainPage.getChildren().add(stone);
			board[xIndex][yIndex] = isBlack ? 1 : -1;
			stoneLog[this.turns][0] = xIndex; stoneLog[this.turns++][1] = yIndex;
		} catch (FileNotFoundException e) {
			System.out.println("파일엄서");
		}

		if (isFive(xIndex, yIndex, isBlack)) {
			this.isEnd = true;
			gameButton.setText("REGAME");
			gameButton.setDisable(false);
			addSystemMessage(xIndex + ", "+ yIndex + " 오목완성!");;
		}
		return true;
	}

	//-------------------------------------------
	// Handler for click on omok board
	//-------------------------------------------
	EventHandler<MouseEvent> clickOnBoard = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			myTurn();
		}
	};
	//-------------------------------------------
	// function for omok rules
	//-------------------------------------------
	public boolean isFive(int x, int y, boolean isBlack) {
		int sum = 0, color = isBlack ? 5 : -5;
		//horizontal
		for (int j = 0; j <5; j++) {
			sum = 0;
			for (int i = 0; i < 5; i++)  {
				if (x-4+j+i < 0 || x-4+j+i > 18 ) break;
				sum = sum + board[x-4+j+i][y];
			}
			if (sum == color) return true;
		}
		//vertical
		for (int j = 0; j <5; j++) {
			sum = 0;
			for (int i = 0; i < 5; i++)  {
				if (y-4+j+i < 0 || y-4+j+i > 18 ) break;
				sum = sum + board[x][y-4+j+i];
			}
			if (sum == color) return true;
		}
		//typically diagonal1 (top left to bottom right)
		for (int j = 0; j <5; j++) {
			sum = 0;
			for (int i = 0; i < 5; i++)  {
				if (x-4+j+i < 0 || x-4+j+i > 18 || y-4+j+i < 0 || y-4+j+i > 18) break;
				sum = sum + board[x-4+j+i][y-4+j+i];
			}
			if (sum == color) return true;
		}
		//typically diagonal2 (top right to bottom left)
		for (int j = 0; j <5; j++) {
			sum = 0;
			for (int i = 0; i < 5; i++)  {
				if (x+4-j-i < 0 || x+4-j-i > 18 || y-4+j+i < 0 || y-4+j+i > 18) break;
				sum = sum + board[x+4-j-i][y-4+j+i];
			}
			if (sum == color) return true;
		}
		//typically diagonal3 (bottom left to top right)
		for (int j = 0; j <5; j++) {
			sum = 0;
			for (int i = 0; i < 5; i++)  {
				if (x-4+j+i < 0 || x-4+j+i > 18 || y+4-j-i < 0 || y+4-j-i > 18) break;
				sum = sum + board[x-4+j+i][y+4-j-i];
			}
			if (sum == color) return true;
		}
		//typically diagonal4 (bottom right to top left)
		for (int j = 0; j <5; j++) {
			sum = 0;
			for (int i = 0; i < 5; i++)  {
				if (x+4-j-i < 0 || x+4-j-i > 18 || y-4+j+i < 0 || y-4+j+i > 18) break;
				sum = sum + board[x+4-j-i][y-4+j+i];
			}
			if (sum == color) return true;
		}

		
		return false;
	}
	
	public void computersTurn(boolean isBlack) {
		if (this.turns == 0) {
			setStone(8, 8, isBlack);
			return;
		}
		int position[] = findBestPosition();
		setStone(position[0],position[1],isBlack);
	}
	
	public void myTurn() {
		Point2D point = robot.getMouseLocation();
		int y = (int) point.getY() - 40 - (int) stage.getY();
		int x = (int) point.getX() - 14 - (int) stage.getX();
		int yDevided = y / 21, xDevided = x / 21, yRemains = y % 21, xRemains = x % 21;
		if (yRemains <= 10) yRemains = 0;
		else yRemains = 1;
		if (xRemains <= 10) xRemains = 0;
		else xRemains = 1;
		int xIndex = xDevided + xRemains, yIndex = yDevided + yRemains;
		if (xIndex < 0) xIndex = 0;
		if (xIndex > 18) xIndex = 18;
		if (yIndex < 0) yIndex = 0;
		if (yIndex > 18) yIndex = 18;
		/*
		 * for setting System.out.println("-----------------");
		 * System.out.println(xDevided+"    "+xRemains+"    "+xIndex);
		 * System.out.println(yDevided+"    "+yRemains+"    "+yIndex);
		 * System.out.println("-----------------");
		 */
		// computer's turn
		if (setStone(xIndex, yIndex, userIsBlack))
			computersTurn(!userIsBlack);
	}
	
	//-------------------------------------------
	// static function
	//-------------------------------------------
	static void setStage(Stage s) {
		Controller.stage = s;
	}
}
