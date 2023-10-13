package main;

import java.util.ArrayList;

public class signUp extends dataBase{
	
	public boolean signStudent(String name, String nickname, String pass, String filename) {
		student newStudent = new student(name, nickname, pass);
		
		ArrayList<student> users = fileRead(filename);
		
		if(users == null) {
			users = new ArrayList<student>();
			users.add(newStudent);
			fileWrite(filename, users);
			return true;
		}
		else if(users.contains(newStudent)) {
			return false;
		}
			
		else {
			users.add(newStudent);
			fileWrite(filename, users);
			return true;
		}
	}
}
