package Assign2;

import java.util.*;
import java.io.*;

public class ElectionSim {
	private String departmentInput_;
	private String studentInput_;
	private String output_;
	private ArrayList<Department> department_;
	private ArrayList<Candidate> candidate_;
	
	public ElectionSim(String departmentInput, String studentInput, String output) {
		this.departmentInput_ = departmentInput;
		this.studentInput_ = studentInput;
		this.output_ = output;
		this.department_ = new ArrayList<>();
		this.department_.add(null);
		this.candidate_ = new ArrayList<>();
		Scanner dinputStream, sinputStream;
		////////////////////////////saving in department_
		try {
			dinputStream = new Scanner(new FileInputStream(this.departmentInput_));
			dinputStream.nextLine();
			while (dinputStream.hasNextLine()) {
				String depName = dinputStream.nextLine().split(",")[1];
				//System.out.println(dinputStream.nextLine());
				//department_.add(new Department(depName));
				this.department_.add(new Department(depName));
				//System.out.println(depName);
			} 
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not Found.");
			this.sendError("File Not Found.");
		}
		//////////////////////////saving student
		try {
			sinputStream = new Scanner(new FileInputStream(this.studentInput_));
			sinputStream.nextLine();
			while (sinputStream.hasNextLine()) {
				//System.out.println(sinputStream.nextLine());
				String[] stud = sinputStream.nextLine().split(",");
				//System.out.println(stud[3]);
				if (str2boolean(stud[3])) {
					//System.out.println(str2int(stud[1]));
					Department d = this.department_.get(str2int(stud[1]));
					Candidate c = new Candidate(stud[2], str2int(stud[0]), d);
					d.addCandidate(c);
				} 
				else {
					//System.out.println("bye");
					Department d = this.department_.get(str2int(stud[1]));
					Student s = new Student(stud[2], str2int(stud[0]), d);
					d.addStudent(s);
				}
				//department_.add(new Department(tmpstr[1]));
			} 
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not Found.");
			this.sendError("File Not Found.");
		}
	}
	
	public boolean str2boolean(String str) {
		if (!str.equalsIgnoreCase("TRUE") && !str.equalsIgnoreCase("FALSE"))
			this.sendError("Invalid Input. (not boolean data)");
		return Boolean.valueOf(str);
	}
	
	public int str2int(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid Input.");
			this.sendError("Invalid Input. (not integer data)");
		}
		return 0;
	}
	
	public void runSimulation() {
		
		
		for(Iterator<Department> it = this.department_.iterator(); it.hasNext();) {
			Department d = it.next();
			if (d == null) continue;
			Candidate selected = null;
			
			for(Iterator<Student> sit = d.getStudent().iterator(); sit.hasNext();)
				//System.out.println(sit.next().getName());
				sit.next().vote();
			
			for(Iterator<Candidate> cit = d.getCandidate().iterator(); cit.hasNext();) {
				Candidate c = cit.next();
				if (selected == null)
					selected = c;
				else 
					if (selected.getNumOfVote() < c.getNumOfVote())
						selected = c;
			}
			this.candidate_.add(selected);
		}
		this.saveData();
	}
	
	public void sendError(String emsg) {
		PrintWriter oStream;
		try {
			oStream = new PrintWriter(new FileOutputStream(this.output_));
			oStream.println(emsg);
			oStream.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File Opening Error");
		}
		System.exit(0);
	}
	
	public void saveData() {
		PrintWriter oStream;
		try {
			oStream = new PrintWriter(new FileOutputStream(this.output_));
			for (Iterator<Candidate> it = this.candidate_.iterator(); it.hasNext();) {
				Candidate selected = it.next();
				oStream.println("======== Elected Candidate ========");
				oStream.println("Department Name: " + selected.getDepartment().getName());
				oStream.println("name: " + selected.getName());
				oStream.println("Student_ID: " + selected.getID());
				oStream.println("Votes: " + selected.getNumOfVote());
				oStream.println("===================================");
			}
			oStream.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File Opening Error");
			System.exit(0);
		}
	}
}
