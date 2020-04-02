package smallD;

public class Character {
	private String name;
	private int maxHp, str, dex, luk, hp;
	boolean isAlive;
	
	public Character(String name) {
		this.name = name;
		this.maxHp = 100; this.str = 0; this.dex = 1000; this.luk = 0;
	}
	
	public String getName() { return this.name;} 
	public int getMaxHp() {
		return maxHp;
	}
	public void plusMaxHp() {
		this.maxHp += 40;
	}
	public int getStr() {
		return str;
	}
	public void plusStr() {
		this.str += 4;
	}
	public int getDex() {
		return dex;
	}
	public void plusDex() {
		this.dex -= 150;
	}
	public int getLuk() {
		return luk;
	}
	public void plusLuk() {
		this.luk += 15;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public boolean isAlive() {
		if (hp <= 0) return false;
		return true;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public void set4Battle() {
		this.hp = this.maxHp;
	}
	
	public void getDamage(int dmg) {
		this.hp = this.hp - dmg;
		System.out.println(this.name + "이 " + this.hp + "가 남았습니다.");
		if (dmg <= 0) {this.hp = 0; this.isAlive = false;}
	}
}
