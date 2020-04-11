package application;

import java.util.ArrayList;

public class Tree {
	private ArrayList<Tree> children;
	private int value;
	
	public Tree(int v) {
		children = null;
		this.value = v;
	}
	
	public Tree[] getChildren() {
		if (this.children == null) return null;
		Tree t[] = new Tree[this.children.size()];
		for (int i = 0; i < this.children.size(); i++)
			t[i] = this.children.get(i);
		return t;
	}
	
	public void addChildren(Tree t) {
		this.children.add(t);
	}
	
	public void removeChildren(Tree t) {
		this.children.remove(t);
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
	
	public int max(int a, int b) {
		if (a > b) return a;
		return b;
	}
	public int min(int a, int b) {
		if (a < b) return a;
		return b;
	}
	
}
