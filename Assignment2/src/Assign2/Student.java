package Assign2;

import java.util.*;

interface Voter {
	public void vote();
}

public class Student implements Voter {

	protected String name_;
	protected int studentID_;
	//protected int grade_;
	protected Department department_;
	
	public Student(String name, int studentID, Department department) {
		this.name_ = name;
		this.studentID_ = studentID;
		//this.grade_ = grade;
		this.department_ = department;
	}
	
	public Department getDepartment() {
		return this.department_;
	}
	
	public String getName() {
		return this.name_;
	}
	
	public int getID() {
		return this.studentID_;
	}
	
	public void vote() {
		ArrayList<Candidate> candidate = this.department_.getCandidate();
		int rand = (int)(Math.random()*candidate.size());
		candidate.get(rand).voted();
	}
}
