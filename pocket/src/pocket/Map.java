package pocket;

import javax.swing.*;
import java.util.*;

public class Map {
	private String name;
	private int number, width, height;
	private boolean[][] obstacles;
	//private Npc npc;
	private Map nextMap;
	private ImageIcon imageFile;
	
	public Map(String name, int number, int width, int height, boolean[][] obstacles, ImageIcon imageFile) {
		super();
		this.name = name;
		this.number = number;
		this.width = width;
		this.height = height;
		this.obstacles = obstacles;
		this.imageFile = imageFile;
	}
	
	public boolean isObstacles(int x, int y) {
		if (x < 0 || x >= this.width || y < 0 || y >= this.height) return true;
		return this.obstacles[x][y];
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean[][] getObstacles() {
		return obstacles;
	}

	public void setObstacles(boolean[][] obstacles) {
		this.obstacles = obstacles;
	}

	public Map getNextMap() {
		return nextMap;
	}

	public void setNextMap(Map nextMap) {
		this.nextMap = nextMap;
	}

	public ImageIcon getImageFile() {
		return imageFile;
	}

	public void setImageFile(ImageIcon imageFile) {
		this.imageFile = imageFile;
	}
	
	
	
}
