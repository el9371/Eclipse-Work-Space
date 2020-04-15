package application;

public class StoneArray {
	private int[] arr = new int[7];
	private int size, sequence, last;
	
	public StoneArray() {
		this.size = 0;
		this.sequence = 0;
		this.last = 0;
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
		this.last = 0;
	}
	
	public int count() {
		this.last = this.arr[size-1];
		/*
		for (int i = 0; i < this.size; i++)
			System.out.print(this.arr[i] + ", ");
		//System.out.println();*/
		int color = this.arr[0];
		for (int i = 0; i < size; i++) {
			if (this.arr[i] == color) this.sequence++;
			else break;
		}
		if (this.sequence == 4) //돌 4개가 붙어있음
			return (this.sequence + 10);
		if (this.sequence == 3 && last == 0) //안막혀 있는 돌 3개
			return (this.sequence + 9);
		else if (this.sequence == 3) //한쪽이 막혀있는 돌 3개
			return (this.sequence + 2);
		if (last == 0) return (this.sequence + 1);
		return this.sequence;
	}
	public int count2() {
		return 0;
	}
}
