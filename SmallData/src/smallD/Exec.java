package smallD;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;
import org.apache.poi.hssf.usermodel.*;


public class Exec {
	
	public void exec() throws Exception {
		
		Character i = new Character("alpha"), u = new Character("Beta");
		Thread t1 = new BattlePase(i, u), t2 = new BattlePase(u, i);
		randomAbility(i); 
		randomAbility(u);
		i.set4Battle(); u.set4Battle();
		t1.start();
		t2.start();
		while(i.isAlive && u.isAlive) Thread.sleep(100);
		System.out.println("전투종료");
		if (i.isAlive) writeIni(i,u);
		else writeIni(u,i);
	}
	
	public static void resetData() throws IOException {
		File iniData = new File("Data\\data.ini");
		Wini wData = new Wini(iniData);
		wData.put("Base", "Games", 0);
		wData.put("Base", "Wins", 0);
		wData.put("Base", "BestWinner", "0005");
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
				wData.put(c.returnAbility(), "Games", 0);
				wData.put(c.returnAbility(), "Wins", 0);
				wData.put(c.returnAbility(), "WinRate", 0);
			}
		}
		wData.store(); 
	}
	
	public void writeIni(Character winner, Character loser) throws IOException, Exception {
		File iniData = new File("Data\\data.ini");
		Ini rData = new Ini(iniData);
		Wini wData = new Wini(iniData);

		
		String wString = winner.returnAbility(), lString = loser.returnAbility();
		wData.put("Base", "Games", Integer.parseInt(rData.get("Base", "Games")) + 1);
		int winnerGames = Integer.parseInt(rData.get(wString, "Games")) + 1;
		int winnerWins = Integer.parseInt(rData.get(wString, "Wins")) + 1;
		double winRate = (double)winnerWins/(double)winnerGames * 100.0;
		//wins / games 가 저장되는 데이터에 안맞는 경우 발견
		wData.put(wString, "Games", winnerGames);
		wData.put(wString, "Wins",  winnerWins);
		wData.put(wString, "WinRate",  winRate);
		if (winRate > Double.parseDouble(rData.get(rData.get("Base","BestWinner"),"WinRate")))
			wData.put("Base", "BestWinner",wString);
		wData.put(lString, "Games",  Integer.parseInt(rData.get(lString, "Games")) + 1);
		wData.store();  
	}
	
	public void writeExel() throws IOException {
		//FileInputStream cellData = new FileInputStream("Data\\statistic.xls");
		 HSSFWorkbook workbook = new HSSFWorkbook(); // 새 엑셀 생성
	        HSSFSheet sheet = workbook.createSheet("시트명"); // 새 시트(Sheet) 생성
	        HSSFRow row = sheet.createRow(0); // 엑셀의 행은 0번부터 시작
	        HSSFCell cell = row.createCell(0); // 행의 셀은 0번부터 시작
	        cell.setCellValue("테스트"); //생성한 셀에 데이터 삽입
	        try {
	            FileOutputStream fileoutputstream = new FileOutputStream("data\\set.xlsx");
	            workbook.write(fileoutputstream);
	            fileoutputstream.close();
	            System.out.println("엑셀파일생성성공");
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("엑셀파일생성실패");
	        }


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
