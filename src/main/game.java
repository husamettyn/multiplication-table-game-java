package main;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class game implements Serializable{

	private static final long serialVersionUID = 1L;
	private static int nextID=1;
	private int id;
	private int score;
	private int time;
	private String name;
	private ArrayList<question> quests;
	private int count;
	private Date start;
	private Date end;
	
	
	
	public game(int a, int b, int n) {
		ArrayList<game> games = dataBase.readGames("games.dat");
		
		if(games.size() > 0) {
			int max = games.get(0).getID();
			for(game g : games) {
				if(max < g.getID()) {
					max = g.getID();
				}
			}
			nextID = max+1;
		}
		
		this.id = nextID++;
		this.score = 0;
		name = Integer.toString(a) + " - " + Integer.toString(b) + " --> " +  Integer.toString(n);
		quests = createGame(a, b, n);
		count = 0;
		time = 0;
	}
	
	public game(game game) {
		this.id = game.id;
		this.time = 0;
		this.score = 0;
		this.name = game.name;
		this.quests = new ArrayList<question>(game.quests);
	}
	
	public int getScore() {
		return score;
	}
	public String getName() {
		return name;
	}
	public int getID() {
		return id;
	}
	
	private ArrayList<question> createGame(int a, int b, int n) {
		Random random = new Random();
		quests = new ArrayList<question>();
		question quest;
		int x, y;
		for(int i=0; i<n; i++) {
			do {
				x = random.nextInt(b-a)+a; // 3-15 7
				y = random.nextInt(b-x)+x+1; // 7-15
			}while(checkQuestionIsValid(x, y));
			
			quest = new question(x, y);
			quests.add(quest);
		}
		return quests;
	}
	
	private boolean checkQuestionIsValid(int x, int y) {
		for(question question : quests) {
			if(question.getA() == x && question.getB() == y) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "game [score=" + score + ", name=" + name + "]";
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date date) {
		this.start = date;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public ArrayList<question> getQuests() {
		return quests;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
