package Testing;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


//(String _name, int _number, int _power, double _accuracy, int _pp, int _maxPp, boolean _isSpecial, Type _type)
enum Type{
	ELECTRICITY, FIGHTHING, FIRE, FLYING, GHOST, GRASS, GROUND, ICE, NORMAL, POISON, ROCK, WATER, NTH
}
enum State{
	BURN, PARALYSIS, DADDICTION, ADDICTION, FROZEN, SLEEPING, REACTION, NTH
}

public class Exec {
	static Pocketmon[] pocketmons;
	
	public static void main(String[] args)
	{
		/*
		Skill skills[] = new Skill[8];
		skills[0] = new Skill("열풍", 1, 95, 0.9, 10, 10, false, true, Type.FIRE);
		skills[1] = new Skill("불대문자", 2, 110, 0.85, 5, 5, false, true, Type.FIRE);
		skills[2] = new Skill("지진", 3, 100, 1, 10, 10, false, true, Type.GROUND);
		skills[3] = new Skill("오버히트", 4, 130, 0.9, 5, 5, true, true, Type.FIRE);
		*/
		int numberOfPocketmon;
		//Pocketmon[] pocketmons;
		Scanner scan = null;
		try {
			scan = new Scanner(new File("Pocketmon.data"));
			numberOfPocketmon = scan.nextInt();
			pocketmons = new Pocketmon[numberOfPocketmon+1];
			for(int i = 1; i < numberOfPocketmon + 1; i++) {
				String name; int kHp; int kAttack; int kDefense; int kSattack; int kSdefense; int kSpeed;
				name = scan.next(); kHp = scan.nextInt(); kAttack = scan.nextInt(); kDefense = scan.nextInt();
				kSattack = scan.nextInt(); kSdefense = scan.nextInt(); kSpeed = scan.nextInt();
				//System.out.print(name);
				pocketmons[i] = new Pocketmon(name, kHp, kAttack, kDefense, kSattack, kSdefense, kSpeed);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Pocketmon Data File not Found");
			System.exit(0);
		}

	}
}
