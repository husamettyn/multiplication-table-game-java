package main;

import java.io.Serializable;
import java.util.Date;

public class question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int situation;
	private int a;
	private int b;
	private Integer correct;
	private Integer studentAns;
	private int time;
	private int start;
	private int end;
	private Date startDate;
	private Date endDate;
	
	public question(int a, int b) {
		this.a = a;
		this.b = b;
		this.correct = a*b;
		situation = 0;
		studentAns = null;
		time = 0;
		startDate = null;
		endDate = null;
	}
	
	public String getSituation() {
		if(situation == 0) {
			return "Bos";
		}
		else if(situation == 1) {
			return "Dogru";
		}
		else {
			return "Yanlis";
		}
		
	}
	public void setSituation(int situation) {
		this.situation = situation;
	}
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	public Integer getStudentAns() {
		return studentAns;
	}
	public void setStudentAns(int studentAns) {
		this.studentAns = studentAns;
	}
	public int getA() {
		return a;
	}
	public int getB() {
		return b;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date date) {
		this.startDate = date;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
