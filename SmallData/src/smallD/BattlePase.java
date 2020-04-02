package smallD;

public class BattlePase extends Thread {
	Character mine, your;

	public BattlePase(Character mine, Character your) {
		this.mine = mine; this.your = your;
	}
	
	@Override
	public void run() {
		int yourEvasion = your.getLuk(), mySpeed = mine.getDex(), myStr = mine.getStr();
		while(mine.getHp() > 0 || your.getHp() > 0) {
			try {Thread.sleep(mySpeed);} catch(Exception e) {}
			if (isEffect(yourEvasion)) {
				int damage = randomDamage() + myStr;
				your.getDamage(damage);
				System.out.println(mine.getName()+"�� "+damage+"�� ���ظ� �������ϴ�.");
			}
		} System.out.println("��������");
	}
	
	public boolean isEffect(int accuracy) {
		if (Math.random() * 99 + 1 <= accuracy)
			return false;
		return true;
	}
	public int randomDamage() {
		return (int)(Math.random() * 10) + 5;
	}
}
