package application;

import javafx.application.Platform;
import javafx.scene.control.*;

public class BattlePage extends Thread {
	private Character mine, your;
	private ProgressBar yourHpBar;
	private Label yourLog;
	
	public BattlePage(Character mine, Character your, ProgressBar yourHp, Label log) {
		this.mine = mine; this.your = your;
		this.yourHpBar = yourHp;
		this.yourLog = log;
	}
	
	@Override
	public void run() {
		int yourEvasion = your.getLuk(), mySpeed = mine.getDex(), myStr = mine.getStr();
		double yourMaxHp = (double)your.getMaxHp();
		while(mine.getHp() > 0 && your.getHp() > 0) {
			try {Thread.sleep(mySpeed);} catch(Exception e) {}
			if (isEffect(yourEvasion)) {
				int damage = randomDamage() + myStr;
				if (mine.isAlive()) 
					if (your.getDamage(damage)) {
						setHpBar(your.getHp(),yourMaxHp);
						setLog("-" + damage);
					}
			} else setLog("MISS");
		} 
	}
	
	public boolean isEffect(int accuracy) {
		if (Math.random() * 100 + 1 <= accuracy)
			return false;
		return true;
	}
	public int randomDamage() {
		return (int)(Math.random() * 11) + 5;
	}
	
	public void setHpBar(double hp, double maxHp) {
		Platform.runLater(()->{
			yourHpBar.setProgress(hp/maxHp);
			});
		}
	public void setLog(String str) {
		Platform.runLater(()->{
			yourLog.setText(str);;
			});
		}
}
