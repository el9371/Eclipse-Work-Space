package pocket;

import java.awt.*;
import javax.swing.*;

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
	static NPC[] npc;
	
	public static void main(String[] args)
	{
		/*
		JLabel[] tmpLabel = {new JLabel(setImageScale(new ImageIcon("images\\NPC\\0_left.png"))),new JLabel(setImageScale(new ImageIcon("images\\NPC\\0_back.png"))),new JLabel(setImageScale(new ImageIcon("images\\NPC\\0_right.png"))),new JLabel(setImageScale(new ImageIcon("images\\NPC\\0_front.png")))};
		NPC testn = new NPC(0,"Hanyang",tmpLabel);
		NPC.DialogueText tmpdialog = testn.new DialogueText("안녕 나는 12세에 탈모온 초딩이야");
		tmpdialog.setNextDialogue(testn.new DialogueText("넌 너가 탈모에서 안전할것같지?"));
		tmpdialog.setNextDialogue(testn.new DialogueText("나와 대결에서 지면 탈모빔을 시전할거야!"));
		tmpdialog.setNextDialogue(testn.new DialogueText("자 배틀이다!"));
		testn.setDt(tmpdialog);
		tmpdialog.printDialogue();
		NPC[] testnpc = {testn};
		HashMap<NPC,int[]> testlocation = new HashMap<NPC,int[]>();
		testlocation.put(testn, new int[]{32,10});
		npc[0] = testn;*/
		npc = npcConstructor();
		new InGame(resolution);
		//new Battle(resolution);
	}
	
	public static ImageIcon setImageScale(ImageIcon i) {
		Image tmp = i.getImage();
		int w = tmp.getWidth(null) / 16, h = tmp.getHeight(null) / 16;
		return new ImageIcon(tmp.getScaledInstance( w * resolution, h * resolution, java.awt.Image.SCALE_SMOOTH));
		//tmp.getWidth(null) * resolution, tmp.getHeight(null) * resolution
	}
	
	public static NPC[] npcConstructor() {
		///////////////////////// need for constructing NPC
		int numberOfNPC = 0;
		NPC[] npcs;
		DialogueText basic_dt = new DialogueText("....");
		////////////////////////
		Pattern pat;
		Matcher mat;
		
		String content = "", firstLine = "";
		Scanner scan = null;
		try {
			scan = new Scanner(new File("NPC.txt"));
			firstLine = scan.nextLine();
		}
		catch(FileNotFoundException e) {
			System.out.println("NPC File not Found");
			System.exit(0);
		}
		
		pat = Pattern.compile("([0-9*]{1,4})");
		mat = pat.matcher(firstLine);
		if (mat.find()) 
			numberOfNPC = Integer.parseInt(mat.group());
		else {System.out.println("Wrong type NPC first line."); System.exit(0);}
		npcs = new NPC[numberOfNPC];
		
		for (int selected = 0; selected < numberOfNPC; selected++)
		{
			String name = "";
			JLabel[] img = {new JLabel(), new JLabel(),new JLabel(),new JLabel()};
			DialogueText dt = basic_dt;
			
			/////////////////// Name ////////////////////////
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type NPC Name line."); System.exit(0);}
			
			pat = Pattern.compile(selected + " Name : (.*)");
			mat = pat.matcher(content);
			if (mat.find())
				name = mat.group(1);
			/////////////////// ImageFile ////////////////////////
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type NPC Image line."); System.exit(0);}
			
			pat = Pattern.compile(selected + "\"(.*)\"");
			mat = pat.matcher(content);
			for (int i = 0;mat.find(); i++)
				img[i] = new JLabel(setImageScale(new ImageIcon(mat.group(1))));
			
			/////////////////// Dialogue ////////////////////////
			int numberOfDialogue = 0;
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type NPC Dialogue line."); System.exit(0);}
			
			pat = Pattern.compile(selected + " Dialogue ([0-9*]{1,2})");
			mat = pat.matcher(content);
			if (mat.find()) numberOfDialogue = Integer.parseInt(mat.group(1));
			System.out.println(numberOfDialogue);
			
			
			for (int i = 0; i < numberOfDialogue ; i++) {
				int type = 0;
				content = scan.nextLine();
				pat = Pattern.compile("\"([0-3]{1}),");
				mat = pat.matcher(content);
				if (mat.find())
					type = Integer.parseInt(mat.group(1));
				pat = Pattern.compile(",(.*)\"");
				mat = pat.matcher(content);
				if (mat.find()) {
					if (i == 0) dt = new DialogueText(type, mat.group(1));
					else dt.setNextDialogue(new DialogueText(type, mat.group(1)));
				}
			}
			
			npcs[selected] = new NPC(selected, name, img, dt);
		} return npcs;
		
	}
	
	public static Map[] mapConstructor() {
		//////////////////////// need for constructing map
		int numberOfMaps = 0;
		Map[] maps;
		
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
			System.out.println("MAP File not Found");
			System.exit(0);
		}
		
		pat = Pattern.compile("([0-9*]{1,4})");
		mat = pat.matcher(firstLine);
		if (mat.find()) 
			numberOfMaps = Integer.parseInt(mat.group());
		else {System.out.println("Wrong type Map first line."); System.exit(0);}
		maps = new Map[numberOfMaps];
		
		for (int selected = 0; selected < numberOfMaps; selected++) 
		{
			String name = "";
			int width = 0, height = 0, obnumber = 0, npcnumber = 0;//nextmapnumber = 0;
			boolean[][] obstacles;
			NPC[] npces;
			HashMap<NPC,int[]> NPCLocation = new HashMap<NPC,int[]>();
			//Map[] nextMap;
			/////////////////// Name ////////////////////////
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type Map Name line."); System.exit(0);}
			
			pat = Pattern.compile(selected + " Name : (.*)");
			mat = pat.matcher(content);
			if (mat.find())
				name = mat.group(1);
			////////////////// Width //////////////////
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type Map Width line."); System.exit(0);}
			
			pat = Pattern.compile(selected + " Width : (.*)");
			mat = pat.matcher(content);
			if (mat.find())
				width = Integer.parseInt(mat.group(1));
			////////////////// Height //////////////////
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type Map Height line."); System.exit(0);}
			
			pat = Pattern.compile(selected + " Height : (.*)");
			mat = pat.matcher(content);
			if (mat.find())
				height = Integer.parseInt(mat.group(1));
			////////////////// Obstacles //////////////////
			
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type Map Obstacles line."); System.exit(0);}
			
			pat = Pattern.compile(selected + " Obstacles ([0-9*]{1,4}) :");
			mat = pat.matcher(content);
			if (mat.find()) obnumber = Integer.parseInt(mat.group(1));
			int[] obx = new int[obnumber], oby = new int[obnumber];
			obstacles = new boolean[width][height];
			for (int x = 0; x < width; x++)
				for (int y = 0; y < height; y++)
					obstacles[x][y] = false;
			
			pat = Pattern.compile("([0-9*]{1,3}),");
			mat = pat.matcher(content);
			for (int i = 0; mat.find() && obnumber != 0; i++) obx[i] = Integer.parseInt(mat.group(1));
			pat = Pattern.compile(",([0-9*]{1,3})");
			mat = pat.matcher(content);
			for (int i = 0; mat.find() && obnumber != 0; i++) oby[i] = Integer.parseInt(mat.group(1));
			for (int i = 0; i < obnumber; i++)
				obstacles[obx[i]][oby[i]] = true;
			////////////////// NPC //////////////////
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type Map Npc line."); System.exit(0);}

			pat = Pattern.compile(selected + " NPC ([0-9*]{1,2}) :");
			mat = pat.matcher(content);
			if (mat.find()) npcnumber = Integer.parseInt(mat.group(1));
			npces = new NPC[npcnumber];
			int[] npcx = new int[npcnumber], npcy = new int[npcnumber];
			
			pat = Pattern.compile("\"([0-9*]{1,3})\"");
			mat = pat.matcher(content);
			for (int i = 0; mat.find() && npcnumber != 0 ; i++) npces[i] = npc[Integer.parseInt(mat.group(1))];
			pat = Pattern.compile("([0-9*]{1,3}),");
			mat = pat.matcher(content);
			for (int i = 0; mat.find() && obnumber != 0; i++) npcx[i] = Integer.parseInt(mat.group(1));
			pat = Pattern.compile(",([0-9*]{1,3})");
			mat = pat.matcher(content);
			for (int i = 0; mat.find() && obnumber != 0; i++) npcy[i] = Integer.parseInt(mat.group(1));
			for (int i = 0; i < npcnumber; i++)
				NPCLocation.put(npces[i], new int[]{npcx[i],npcy[i]});
			////////////////// NextMap add later  //////////////////
			/*
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type."); System.exit(0);}
			
			pat = Pattern.compile(selected + " NextMap ([0-9*]{1,2}) :");
			mat = pat.matcher(content);
			if (mat.find()) nextmapnumber = Integer.parseInt(mat.group(1));
			nextMap = new Map[nextmapnumber];
			*/
			////////////////// Map Image  //////////////////
			if (scan.hasNextLine()) content = scan.nextLine();
			else {System.out.println("Wrong type map image line."); System.exit(0);}

			pat = Pattern.compile(selected + " MapImage : \"(.*)\"");
			mat = pat.matcher(content);
			if (!mat.find()) {System.out.println("Wrong type map image line."); System.exit(0);}
			ImageIcon imageFile = setImageScale(new ImageIcon(mat.group(1)));
			
			//////////////////// construct map///////////////////
			maps[selected] = new Map(name,selected,width,height,obstacles,npces,NPCLocation,imageFile);
		}
		//System.out.println("1");
		return maps;
	}
	
	
	
	
}

