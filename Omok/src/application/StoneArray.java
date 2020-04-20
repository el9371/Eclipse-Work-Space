package application;

public class StoneArray {
	private int[] arr = new int[7];
	private int size, sequence;
	private boolean isBlack;
	
	public StoneArray(boolean isBlack) {
		this.size = 0;
		this.sequence = 0;
		this.isBlack = isBlack;
		for (int i:this.arr)
			i = -100;
	}
	
	public void addArr(int i) {
		this.arr[this.size++] = i;
	}
	
	public void reset() {
		for (int i:this.arr)
			i = -100;
		this.size = 0;
		this.sequence = 0;
	}
	
	
	public int count() {
		int last = this.arr[size-1];
		/*
		for (int i = 0; i < this.size; i++)
			System.out.print(this.arr[i] + ", ");
		//System.out.println();*/
		int color = this.arr[0];
		for (int i = 0; i < size; i++) {
			if (this.arr[i] == color) this.sequence++;
			else {
				last = this.arr[i];
				break;
			}
		}
		if (this.sequence == 4) {
			//돌 4개가 붙어있음
			if (this.isBlack && color == 1)
				return this.sequence + 16;
			else if(!this.isBlack && color == -1)
				return this.sequence + 16;
			return (this.sequence + 10);
		}
		if (this.sequence == 3 && last == 0) //안막혀 있는 돌 3개
			return (this.sequence + 6);
		else if (this.sequence == 3) //한쪽이 막혀있는 돌 3개
			return (this.sequence + 2);
		if (last == 0) return (this.sequence + 1);
		return this.sequence;
	}
	public int count2() {
		if (this.arr[2] == 0 || this.arr[4] == 0) return 0;
		int sequenceRight = 0, sequenceLeft = 0, lastRight = this.arr[6]
						, lastLeft = this.arr[0], color = this.arr[2];
		if (this.arr[4] != color) return 0;
		for (int i = 2; i >= 0; i--) {
			if (this.arr[i] == color) sequenceLeft++;
			else {
				lastLeft = this.arr[i];
				break;
			}
		}
		for (int i = 4; i < 7; i++) {
			if (this.arr[i] == color) sequenceRight++;
			else {
				lastRight = this.arr[i];
				break;
			}
		}
		if (sequenceRight + sequenceLeft == 4) { //?●●□●●? 상태 (□는 검사하는 구간 ☆빈곳
			if (this.isBlack && color == 1) return 16;
			else if (!this.isBlack && color == -1) return 16;
			return 10;
		}
		if (sequenceRight * sequenceLeft != 0 && sequenceRight + sequenceLeft == 3) {
			if (lastLeft == color * -1 && lastRight == color * - 1) //○●●□●○
				return 0;
			if (lastLeft == color * -1 || lastRight == color * - 1) //○●●□●☆
				return 5;
			return 14;
		}
		return 0;
	}
	
	public void printArr() {
		for (int i = 0; i < this.size; i++) {
			if (this.arr[i] == -1) System.out.print("○");
			else if (this.arr[i] == 1) System.out.print("●");
			else if (this.arr[i] == 0) System.out.print("□");
		} System.out.print("   ");
	}
}
