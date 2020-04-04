package smallD;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.io.File;
import java.io.IOException;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

public class Exec {
	
	public void exec() throws Exception {
		
		Character i = new Character("Alpha"), u = new Character("Beta");
		Thread t1 = new BattlePase(i, u), t2 = new BattlePase(u, i);
		randomAbility(i); randomAbility(u);
		i.set4Battle(); u.set4Battle();
		i.printAbility(); u.printAbility();
		t1.start();
		t2.start();
		while(i.isAlive && u.isAlive) Thread.sleep(100);
		System.out.println("전투종료");
		if (i.isAlive) writeIni(i,u);
		else writeIni(u,i);
	}
	
	public void writeIni(Character winner, Character loser) throws IOException, Exception {
		File smallData = new File("Data\\data.ini");
		Ini rData = new Ini(smallData);
		Wini wData = new Wini(smallData);
			/* for setting
			for (int i = 0; i < 10000; i++) {
				Character c = new Character("test");
				randomAbility(c);
				System.out.println(c.returnAbility());
				wData.put(c.returnAbility(), "Games", 0);
				wData.put(c.returnAbility(), "Wins", 0);
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
	
	public static void randomAbility(Character c) {
		for (int i = 0 ; i < 5 ; i++) {
			int rnd = (int)(Math.random() * 4) + 1;
			switch(rnd) {
			case 1:
				c.plusMaxHp();
				break;
			case 2:
				c.plusStr();
				break;
			case 3:
				c.plusDex();
				break;
			case 4:
				c.plusLuk();
			}
		}
	}
}
