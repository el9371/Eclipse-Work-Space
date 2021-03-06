package smallD;

public class Character {
	static private int battleSpeed = 1;//기본100
	private String name;
	private int maxHp, str, dex, luk, hp;
	private int hpAbility, strAbility, dexAbility, lukAbility;
	boolean isAlive;
	
	public Character(String name) {
		this.hpAbility = 0; this.dexAbility = 0; this.strAbility = 0; this.lukAbility = 0;
		this.name = name;
	}
	
	public Character(int hp, int str, int dex, int luk) {
		this.hpAbility = hp; this.strAbility = str; this.dexAbility = dex; this.lukAbility = luk;
		this.name = "Custom";
	}
	
	public String getName() { return this.name;} 
	public int getMaxHp() {
		return maxHp;
	}
	public void plusMaxHp() {
		this.hpAbility += 1;
	}
	public int getStr() {
		return str;
	}
	public void plusStr() {
		this.strAbility += 1;
	}
	public int getDex() {
		return dex;
	}
	public void plusDex() {
		this.dexAbility += 1;
	}
	public int getLuk() {
		return luk;
	}
	public void plusLuk() {
		this.lukAbility += 1;
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
		this.maxHp = 100 + this.hpAbility * 30;
		this.str = 0 + this.strAbility * 3;
		this.dex = (10 - this.dexAbility * 1) * battleSpeed;
		this.luk = 0 + this.lukAbility * 10;
		this.isAlive = true;
		this.hp = this.maxHp;
	}
	
	public boolean getDamage(int dmg) {
		if (this.isAlive) {
		this.hp = this.hp - dmg;
		if (this.hp <= 0) {
			this.hp = 0; this.isAlive = false;
			//System.out.println(this.name + "님이 쓰러지셨습니다."); 
			}
		//else System.out.println(this.name + "이 " + dmg + "의 피해를 입어 " + this.hp + "가 남았습니다.");
		return true;
		} return false;
	}
	
	public void printAbility() {
		System.out.println(this.hpAbility + " | " + this.strAbility + " | "
				+ this.dexAbility + " | " + this.lukAbility);
	}
	
	public String returnAbility() {
		return this.hpAbility +"" + this.strAbility + "" + this.dexAbility + "" + this.lukAbility;
	}
}
