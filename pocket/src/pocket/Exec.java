package pocket;

import java.awt.Image;
import javax.swing.ImageIcon;

import pocket.Map;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

enum Type{
	ELECTRICITY, FIGHTHING, FIRE, FLYING, GHOST, GRASS, GROUND, ICE, NORMAL, POISON, ROCK, WATER, NTH
}
enum States{
	BURN, PARALYSIS, ADDICTION, FROZEN, SLEEPING, REACTION, NTH
}

public class Exec{
	static int resolution = 32;
	
	public static void main(String[] args)
	{
		
		//new InGame(resolution);
		//new Battle(resolution);
		mapConstructor();
	}
	
	public static ImageIcon setImageScale(ImageIcon i) {
		Image tmp = i.getImage();
		int w = tmp.getWidth(null) / 16, h = tmp.getHeight(null) / 16;
		return new ImageIcon(tmp.getScaledInstance( w * resolution, h * resolution, java.awt.Image.SCALE_SMOOTH));
		//tmp.getWidth(null) * resolution, tmp.getHeight(null) * resolution
	}
	
	public static void mapConstructor() {
		//////////////////////// need for constructing map
		int numberOfMaps = 0;
		
		String name;
		int number, width, height;
		boolean[][] obstacles;
		NPC[] npc;
		HashMap<NPC,int[]> NPCLocation = new HashMap<NPC,int[]>();
		Map nextMap;
		ImageIcon imageFile;
		//////////////////
		Pattern pat;
		Matcher mat;
		
		String content = "", firstLine = "";
		Scanner scan = null;
		try {
			scan = new Scanner(new File("Map.txt"));
			firstLine = scan.nextLine();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not Found");
			System.exit(0);
		}
		pat = Pattern.compile("([0-9*]{1,4})");
		mat = pat.matcher(firstLine);
		if (mat.find()) 
			numberOfMaps = Integer.parseInt(mat.group());
		else {System.out.println("Wrong type."); System.exit(0);}
		for (int selected = 0; selected < numberOfMaps; selected++) 
		{
			/////////////////// Name ////////////////////////
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type."); System.exit(0);}
			pat = Pattern.compile(selected + " Name : (.*)");
			mat = pat.matcher(content);
			if (mat.find())
				System.out.println("Name : " + mat.group());
			////////////////// Width //////////////////
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type."); System.exit(0);}
			pat = Pattern.compile(selected + " Width : (.*)");
			mat = pat.matcher(content);
			if (mat.find())
				System.out.println("Width : " + mat.group());
			////////////////// Height //////////////////
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type."); System.exit(0);}
			pat = Pattern.compile(selected + " Height : (.*)");
			mat = pat.matcher(content);
			if (mat.find())
				System.out.println("Height : " + mat.group());
			////////////////// Obstacles //////////////////
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type."); System.exit(0);}
			pat = Pattern.compile("([0-9*]{1,3}),");
			mat = pat.matcher(content);
			while(mat.find())
				System.out.println(mat.group(1));
		}
		//System.out.println("1");
	}
	
	
	
	
}

