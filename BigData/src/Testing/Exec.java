package Testing;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



//(String _name, int _number, int _power, double _accuracy, int _pp, int _maxPp, boolean _isSpecial, Type _type)
enum Type{
	
	NORMAL("노말"), FIGHTING("격투"), POISON("독"), GROUND("땅"),
	FLYING("비행"), BUG("벌레"), ROCK("바위"), GHOST("고스트"),IRON("강철"),
	FIRE("불꽃"), WATER("물"), ELECTRIC("전기"), GRASS("풀"), ICE("얼음"),
	PHYSIC("에스퍼"), DRAGON("드래곤"), DARK("악"), FAIRY("페어리"), NTH("");
	Type(String _name) { this.name = _name; }
	private String name; 
	private static final int balance[][] = {
			{0,0,0,0,0,0,-1,5,-1,0,0,0,0,0,0,0,0,0,0},			//노말
			{1,0,-1,0,-1,-1,1,5,1,0,0,0,0,1,-1,0,1,0,0},		//격투
			{0,0,-1,-1,0,0,-1,-1,5,0,0,0,1,0,0,0,0,1,0},		//독
			{0,0,1,0,5,-1,1,0,1,1,0,1,-1,0,0,0,0,0,0},			//땅
			{0,1,0,0,0,1,-1,0,-1,0,0,-1,1,0,0,0,0,0,0},		//비행
			{0,-1,-1,0,-1,0,0,-1,-1,-1,0,0,1,0,1,0,1,-1,0},	//벌레
			{0,-1,0,-1,1,1,0,0,-1,1,0,0,0,1,0,0,0,0,0},		//바위
			{5,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,-1,0,0},			//고스트
			{0,0,0,0,0,0,1,0,-1,-1,-1,-1,0,1,0,0,0,1,0,},		//강철
			{0,0,0,0,0,1,-1,0,1,-1,-1,0,1,1,0,-1,0,0,0},		//불꽃
			{0,0,0,1,0,0,1,0,0,1,-1,0,-1,0,0,-1,0,0,0},		//물
			{0,0,0,5,1,0,0,0,0,0,1,-1,-1,0,0,-1,0,0,0},		//전기
			{0,0,-1,1,-1,-1,1,0,-1,-1,1,0,-1,0,0,-1,0,0,0},	//풀
			{0,0,0,1,1,0,0,0,-1,-1,-1,0,1,-1,0,1,0,0,0},		//얼음
			{0,1,1,0,0,0,0,0,-1,0,0,0,0,0,-1,0,5,0,0},			//에스퍼
			{0,0,0,0,0,0,0,1,-1,0,0,0,0,0,0,1,0,5,0},			//드래곤
			{0,-1,0,0,0,0,0,1,0,0,0,0,0,0,1,0,-1,-1,0},		//악
			{0,1,-1,0,0,0,0,0,-1,-1,0,0,0,0,0,1,1,0,0}	,		//페어리
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}				//NTH
		};		//-1 효과 별로, 0 기본, 1효과 굉장, 5 효과 없음
	public String getName() { return name;}
	public static int getBalance(int attack, int defense) { return balance[attack][defense];}
}

enum State{
	BURN("화상"), PARALYSIS("마비"), DADDICTION("중독"),
	ADDICTION("중독"), FROZEN("얼음"), SLEEPING("수면"),
	DISAPPOINT("풀죽기"), NTH("");
	State(String _name) { this.name = _name;}
	private String name;
	public String getName() {return name;}
}

public class Exec {
	
	public static Pocketmon myPock = null;
	public static Pocketmon yourPock = null;
	public static void main(String[] args)
	{
		/*
		Skill skills[] = new Skill[8];
		skills[0] = new Skill("열풍", 1, 95, 0.9, 10, 10, false, true, Type.FIRE);
		skills[1] = new Skill("불대문자", 2, 110, 0.85, 5, 5, false, true, Type.FIRE);
		skills[2] = new Skill("지진", 3, 100, 1, 10, 10, false, true, Type.GROUND);
		skills[3] = new Skill("오버히트", 4, 130, 0.9, 5, 5, true, true, Type.FIRE);
		
		1 강철날개 IRON 25 90 0 0 0 70 NTH 0 1 1 1 100 1
		2 불대문자 FIRE 5 85 0 0 0 110 BURN 10 0
		3 모래뿌리기 GROUND 15 100 0 1 5 1 0
		4 열탕 WATER 15 100 0 0 1 80 BURN 30 0
		5 속이다 NTH 10 100 3 0 0 40 DISAPPOINT 100 0
		*/
		new Skill();
		Skill skills[] = {Skill.skills[1], Skill.skills[2], Skill.skills[3], Skill.skills[4]};
		Pocketmon lizamong = new Pocketmon(3);
		Pocketmon ninetales = new Pocketmon(29);
		lizamong.setSkill(skills); ninetales.setSkill(skills);
		myPock = lizamong; yourPock = ninetales;
		System.out.println(myPock.getName());
		new Screen();
		/*
		lizamong.printAbility(); ninetales.printAbility();
		lizamong.useSkill(3, ninetales);
		lizamong.printAbility();ninetales.printAbility();
		*/

	}
	
}
