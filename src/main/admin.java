package main;

import java.util.ArrayList;

public class admin {
	private String name;
	private String nickname;
	private ArrayList<student> users;

	public admin(String name, String nickname, ArrayList<student> user) {
		this.name = name;
		this.nickname = nickname;
		this.users = user;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public ArrayList<student> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<student> users) {
		this.users = users;
	}

	
	
}
