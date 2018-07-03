package Assign2;

import java.util.*;

public class Department {
	private String name_;
	private ArrayList<Candidate> candidate_;
	private ArrayList<Student> student_;
	
	public Department(String name) {
		this.name_ = name;
		this.candidate_ = new ArrayList<>();
		this.student_ = new ArrayList<>();
	}
	
	
	public void addCandidate(Candidate c) {
		this.student_.add(c);
		this.candidate_.add(c);
	}
	public void addStudent(Student s) {
		this.student_.add(s);
	}
	
	public String getName() {
		return this.name_;
	}
	
	public ArrayList<Candidate> getCandidate() {
		return this.candidate_;
	}
	
	public ArrayList<Student> getStudent() {
		return this.student_;
	}
	/*
	public Candidate maxVote() {
		return
	}*/
}
