package main;

import java.io.Serializable;
import java.util.ArrayList;

public class student implements Serializable{
	private String name;
	private String nickname;
	private String pass;
	private int totalScore;
	private ArrayList<game> games;
	private static final long serialVersionUID = -2856897848600580929L;
	

	public student(String name,String nickname, String pass) {
		this.name = name;
		this.nickname = nickname;
		this.pass = pass;
		totalScore = 0;
		this.games = new ArrayList<game>();
		totalScore = 0;
	}

	public student(String name, String nickname, String pass,ArrayList<game> games, int score) {
		this.nickname = nickname;
		this.pass = pass;
		this.name = name;
		this.games = games;
		this.totalScore = score;
	}

	public ArrayList<game> getGames() {
		return games;
	}

	public void setGames(ArrayList<game> games) {
		this.games = games;
	}
	
	public void addGame(game game) {
		games.add(game);
	}
	
	public String getName() {
		return name;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPass() {
		return pass;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
}

