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
		arr[this.size++] = i;
	}
	
	public void reset() {
		for (int i:this.arr)
			i = 0;
		this.size = 0;
		this.sequence = 0;
		this.last = 0;
	}
	
	public int count() {
		this.last = arr[size-1];
		int color = arr[0];
		for (int i = 0; i < size; i++) 
			if (arr[0] == color) this.sequence++;
		if (this.sequence == 4) return this.sequence + 4;
		if (last == 0) return this.sequence + 3;
		return this.sequence;
	}
}
