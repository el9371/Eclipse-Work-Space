package application;

import java.util.ArrayList;

public class Tree {
	private ArrayList<Tree> children;
	private int value, x, y, depth;
	private int board[][] = new int[19][19];
	private boolean isBlack, findError = false;
	private Tree parent;
	
	public Tree(int x, int y, int[][] b, boolean isBlack) {
		children = new ArrayList<Tree>();
		copyBoard(b);
		this.x = x; this.y = y;
		this.value = positionCount(x, y);
		this.isBlack = isBlack;
		this.board[x][y] = isBlack ? 1 : -1;
	}
	public Tree() {
		depth = 0;
		children = new ArrayList<Tree>();
	}
	
	public Tree getXYChild(int x, int y, boolean isBlack) {
		//첫 수를 둔 후 트리 호출
		if (this.children.size() == 0) {
			if (findError) System.out.println("error code : 5");
			addChild(new Tree(x, y, new int[19][19], !isBlack));
			if (findError) System.out.println("error code : 6");
			return nextTree(0);
		}
		
		int size = children.size();
		for (int i = 0; i < size; i++) {
			int location[] = children.get(i).getLocation();
			if (location[0] == x && location[1] == y)
				return nextTree(i);
		}
		if (findError) System.out.println("error code : 9");
		addChild(new Tree(x, y, this.board, !isBlack));
		if (findError) System.out.println("error code : 10");
		return nextTree(size);
	}
	
	public Tree nextTree(int index) {
		Tree t = this.children.get(index);
		if (findError) System.out.println("error code : 11");
		int range[] = t.rangeOfStone();
		for (int i = range[0]; i <= range[1]; i++)
			for (int j = range[2]; j <= range[3]; j++) 
				if (t.board[i][j] == 0)
					t.addChild(new Tree(i, j, t.board, !t.isBlack));
		if (findError) System.out.println(range[0] + "," +range[1] + "," +range[2] + "," +range[3]);
		if (findError) System.out.println("children size : "+ t.getChildren().size());
		return t;
	}
	
	public ArrayList<Tree> getChildren() {
		return this.children;
	}
	public void setStone(int _x, int _y, int color) {
		this.board[_x][_y] = color;
	}
	public void copyBoard(int[][] b) {
		for (int i = 0; i < 19; i++)
			for(int j = 0; j < 19; j++)
				this.board[i][j] = b[i][j];
	}
	public int[] getLocation() {
		int location[] = {x,y};
		return location;
	}
	public int[][] getBoard() {
		return this.board;
	}
	public int getDepth() {
		return this.depth;
	}
	public void setDepth(int d) {
		this.depth = d;
	}
	public int getValue() {
		return this.value;
	}
	public void setValue(int v) {
		this.value = v;
	}
	public void addChild(Tree t) {
		if (findError) System.out.println("error code : 7");
		t.setDepth(this.getDepth() + 1);
		t.setParent(this);
		if (findError) System.out.println("error code : 8");
		this.children.add(t);
	}
	public void setParent(Tree t) {
		this.parent = t;
	}
	public Tree getParent() {
		return this.parent;
	}
	public void removeChildren(Tree t) {
		this.children.remove(t);
	}
	public int max(int a, int b) {
		if (a > b) return a;
		return b;
	}
	public int min(int a, int b) {
		if (a < b) return a;
		return b;
	}
	public Tree bestChild() {
		int best = 0, bestIndex = 0;
		if (findError) System.out.println("bestChild childrensize : " + this.children.size());
		for (int i = 0; i < this.children.size(); i++) {
			Tree t = this.children.get(i);
			int v = t.getValue();
			if (v > best) {
				best = v;
				bestIndex = i;
			}
		} Tree bestChild = this.children.get(bestIndex);
		System.out.println("BestChoice = ["+bestChild.getLocation()[0]+"]["+bestChild.getLocation()[1]+"]");
		return bestChild;
	}
	
	//-------------------------------------------
	// function for omok rules
	//-------------------------------------------
	
	public int[] rangeOfStone() {
		//범위를 +1씩 해서 outOfIndex 오류안나게 return 하자
		int xMax = 0, xMin = 18, yMax = 0, yMin = 18;
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++) 
				if (this.board[i][j] != 0) {
					if (i > xMax) xMax = i;
					if (i < xMin) xMin = i;
					if (j > yMax) yMax = j;
					if (j < yMin) yMin = j;
				}
		if (xMin > 0) xMin --;
		if (yMin > 0) yMin --;
		if (xMax < 18) xMax++;
		if (yMax < 18) yMax++;
		int range[] = {xMin, xMax, yMin, yMax};
		return range;
	}

	public int positionCount(int x, int y) {
		int count = 0;
		StoneArray arr = new StoneArray(this.isBlack);
		System.out.print("["+x+"]["+y+"] || ");
		//count1 ←
		if (x-1 >= 0 && board[x-1][y] != 0) {
			for (int i = 0; x-1-i >= 0 && i < 5 ; i++) 
				arr.addArr(board[x-1-i][y]);
			count = count + arr.count();
			System.out.print("1:" + count + "  ");
		}
		//count2 →  
		if (x+1 < 19 && board[x+1][y] != 0) {
			arr.reset();
			for (int i = 0; x+1+i < 19 && i < 5; i++)
				arr.addArr(board[x+1+i][y]);
			count = count + arr.count();
			System.out.print("2:" + count + "  ");
		}
		//count3 ↑
		if (y-1 >= 0 && board[x][y-1] != 0) {
			arr.reset();
			for (int i = 0; y-1-i >= 0 && i<5; i++)
				arr.addArr(board[x][y-1-i]);
			count = count + arr.count();
			System.out.print("3:" + count + "  ");
		}
		//count4 ↓
		if (y+1 < 19 && board[x][y+1] != 0) {
			arr.reset();
			for (int i = 0; y+1+i < 19 && i < 5; i++) 
				arr.addArr(board[x][y+1+i]);
			count = count + arr.count();
			System.out.print("4:" + count + "  ");
		}
		//count   ↗5
		if (x+1 < 19 && y-1 >= 0  && board[x+1][y-1] != 0) {
			arr.reset();
			for (int i = 0; x+1+i < 19 && y-1-i >= 0 && i < 5; i++)
				arr.addArr(board[x+1+i][y-1-i]);
			count = count + arr.count();
			System.out.print("5:" + count + "  ");
		}
		//count  ↘6
		if (x+1 < 19 && y+1 < 19 && board[x+1][y+1] != 0) {
			arr.reset();
			for (int i = 0; x+1+i < 19 && y+1+i < 19 && i < 5; i++)
				arr.addArr(board[x+1+i][y+1+i]);
			count = count + arr.count();
			System.out.print("6:" + count + "  ");
		}
		//count ↙7
		if (x-1 >= 0 && y+1 < 19 && board[x-1][y+1] != 0) {
			arr.reset();
			for (int i = 0; x-1-i >= 0 && y+1+i < 19 && i < 5; i++)
				arr.addArr(board[x-1-i][y+1+i]);
			count = count + arr.count();
			System.out.print("7:" + count + "  ");
		}
		//count ↖8
		if (x-1 >= 0 && y-1 >= 0 && board[x-1][y-1] != 0) {
			arr.reset();
			for (int i = 0; x-1-i >= 0 && y-1-i >= 0 && i < 5; i++)
				arr.addArr(board[x-1-i][y-1-i]);
			count = count + arr.count();
			System.out.print("8:" + count + "  ");
		}
		
		//사이 일부러 공간을 만든 ●●□●● 이런 수 검사하기
		//count2.1 →
		arr.reset();
		for (int i = -3; i < 4; i++) {
			if (x+i < 0 || x+i > 19) arr.addArr(this.isBlack ? -1 : 1);
			else arr.addArr(board[x+i][y]);
		} count = count + arr.count2();
		arr.printArr();
		
		//count2.2 ↓
		arr.reset();
		for (int i = -3; i < 4; i++) {
			if (y+i < 0 || y+i > 18) arr.addArr(this.isBlack ? -1 : 1);
			else arr.addArr(board[x][y+i]);
		} count = count + arr.count2();
		arr.printArr();
		//count2.3 ↘
		arr.reset();
		for (int i = -3; i < 4; i++) {
			if (y+i < 0 || y+i > 18 || x+i < 0 || x+i > 18) 
				arr.addArr(this.isBlack ? -1 : 1);
			else arr.addArr(board[x+i][y+i]);
		} count = count + arr.count2();
		arr.printArr();
		//count2.4 ↙
		arr.reset();
		for (int i = -3; i < 4; i++) {
			if (y+i < 0 || y+i > 18 || x-i < 0 || x-i > 18) 
				arr.addArr(this.isBlack ? -1 : 1);
			else arr.addArr(board[x-i][y+i]);
		} count = count + arr.count2();
		arr.printArr();
		System.out.println(" then "+ count);
		return count;
	}
	
	
	public int alphabeta(Tree t, int depth, int alpha, int beta, boolean forMax){
		if (depth == 0 || t.getChildren() == null) return value;
		if (forMax) {
			for (Tree c:t.getChildren()) {
				alpha = max(alpha, alphabeta(c, depth-1, alpha, beta, false));
				if (beta <= alpha) break;
			}
			return alpha;
		}
		else {
			for (Tree c:t.getChildren()) {
				alpha = min(alpha, alphabeta(c, depth-1, alpha, beta, true));
				if (beta <= alpha) break;
			}
			return alpha;
		}
	}
	
}
