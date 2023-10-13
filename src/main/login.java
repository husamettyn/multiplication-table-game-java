package main;

import java.util.ArrayList;

public class login extends dataBase{
	
	public student loginUser(String nick, String pass, String fileName) {
		ArrayList<student> users = fileRead(fileName);
		for (student us : users) {
			if(us.getNickname().equals(nick) && us.getPass().equals(pass)) {
				return us;
			}
		}
		return null;
	}
	
	public admin loginAdm(String nick, String pass, String fileName) {
		ArrayList<student> users = fileRead(fileName);
		String admn = "husam";
		String pa$$ = "1234";
		if(nick.equals(admn) && pass.equals(pa$$)) {
			admin myadmin = new admin("Hüsamettin", nick, users);
			return myadmin;
		}
		else
			return null;
	}
}
