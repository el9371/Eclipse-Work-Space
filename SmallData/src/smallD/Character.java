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
		this.isAlive = true;
		this.hp = this.maxHp;
	}
	
	public boolean getDamage(int dmg) {
		if (this.isAlive) {
		this.hp = this.hp - dmg;
		if (this.hp <= 0) {
			this.hp = 0; this.isAlive = false;
			System.out.println(this.name + "님이 쓰러지셨습니다."); 
			}
		else System.out.println(this.name + "이 " + dmg + "의 피해를 입어 " + this.hp + "가 남았습니다.");
		return true;
		} return false;
	}
	
	public void printAbility() {
		System.out.println("maxHp : " + this.maxHp + " | str : " + this.str + " | dex : " + this.dex
				+ " | luk : " + this.luk);
	}
}
