package Testing;

/*
데미지 = (위력 × 공격 × (레벨 × 2 ÷ 5 + 2 ) ÷ 방어 ÷ 50 × [[급소]] + 2 )
× [[자속 보정]] × 타입상성1 × 타입상성2 × 랜덤수/255

HP 계산 = (종족값 x 2 + 20 + 60) / 2 + 10 + 50
스텟 = (종족값 x 2 + 20 + 60) / 2 + 5

*/
public class Pocketmon {
	private int number, maxHp, hp, attack, defense, sattack, sdefense, speed;
	private String name;
	private Type type[] = new Type[2];
	private Skill skill[] = new Skill[4];
	private State state;
	private int kHp, kAttack, kDefense, kSattack, kSdefense, kSpeed; //종족값
	private int bAttack, bDefense, bSattack, bSdefense, bSpeed, bAccuracy;//버프스택
	
	
}
