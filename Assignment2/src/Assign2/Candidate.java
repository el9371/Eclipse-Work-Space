package Assign2;

public class Candidate extends Student {
	private int numOfVote_;
	
	public Candidate(String name, int studentID, Department department) {
		super(name, studentID, department);
		this.numOfVote_ = 0;
	}
	
	public void voted() {
		this.numOfVote_ += 1;
	}
	
	public int getNumOfVote() {
		return this.numOfVote_;
	}
}
