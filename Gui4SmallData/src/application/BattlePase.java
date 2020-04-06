package application;

public class BattlePase extends Thread {
	Character mine, your;

	public BattlePase(Character mine, Character your) {
		this.mine = mine; this.your = your;
	}
	
	@Override
	public void run() {
		int yourEvasion = your.getLuk(), mySpeed = mine.getDex(), myStr = mine.getStr();
		while(mine.getHp() > 0 && your.getHp() > 0) {
			try {Thread.sleep(mySpeed);} catch(Exception e) {}
			if (isEffect(yourEvasion)) {
				int damage = randomDamage() + myStr;
				if (mine.isAlive()) your.getDamage(damage);
			} else System.out.println(your.getName() + "님이 공격을 피했습니다.");
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
}
