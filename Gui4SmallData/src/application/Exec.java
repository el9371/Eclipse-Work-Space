package application;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;


public class Exec {
	
	public void exec() throws Exception {
		
		Character i = new Character("Alpha"), u = new Character("Beta");
		Thread t1 = new BattlePase(i, u), t2 = new BattlePase(u, i);
		//randomAbility(i); randomAbility(u);
		//i.set4Battle(); u.set4Battle();
		t1.start();
		t2.start();
		while(i.isAlive && u.isAlive) Thread.sleep(100);
		System.out.println("전투종료");
		if (i.isAlive) writeIni(i,u);
		else writeIni(u,i);
	}
	
	public void writeIni(Character winner, Character loser) throws IOException, Exception {
		File iniData = new File("Data\\data.ini");
		Ini rData = new Ini(iniData);
		Wini wData = new Wini(iniData);
			/* for setting 
			String arrAbility[] = new String[100];
			int numberOfAbility = 0;
			boolean isHere;
			for (int i = 0; i < 10000; i++) {
				isHere = false;
				Character c = new Character("test");
				randomAbility(c);
				System.out.println(c.returnAbility());
				for (int j = 0; j < numberOfAbility; j++)
					if (arrAbility[j].equals(c.returnAbility())) {isHere = true; break;} 
				if (!isHere) {
					arrAbility[numberOfAbility] = c.returnAbility();
					wData.put(c.returnAbility(), "number", numberOfAbility++);
				}
				//wData.put(c.returnAbility(), "Games", 0);
				//wData.put(c.returnAbility(), "Wins", 0);
			}
			wData.store();
		 */
		String wString = winner.returnAbility(), lString = loser.returnAbility();
		wData.put("Base", "Games", Integer.parseInt(rData.get("Base", "Games")) + 1);
		wData.put(wString, "Games",  Integer.parseInt(rData.get(wString, "Games")) + 1);
		wData.put(wString, "Wins",  Integer.parseInt(rData.get(wString, "Wins")) + 1);
		wData.put(lString, "Games",  Integer.parseInt(rData.get(lString, "Games")) + 1);
		wData.store(); 
	}
	
	
	public static int[] randomAbility() {
		int randomStat[] = new int[4];
		for (int i = 0 ; i < 5 ; i++) 
			randomStat[(int)(Math.random() * 4) ]++;
		return randomStat;
	}
}
