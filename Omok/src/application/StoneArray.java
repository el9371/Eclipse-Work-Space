package application;

public class StoneArray {
	private int[] arr = new int[5];
	private int size, sequence, last;
	
	public StoneArray() {
		this.size = 0;
		this.sequence = 0;
		this.last = 0;
	}
	
	public void addArr(int i) {
		this.arr[this.size++] = i;
	}
	
	public void reset() {
		for (int i:this.arr)
			i = 0;
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
		} System.out.println(sequence);
		if (this.sequence == 4) {
			return (this.sequence + 10);
		} if (this.sequence == 3) {
			return (this.sequence + 5);
		}
		if (last == 0) return (this.sequence + 1);
		return this.sequence;
	}
}
