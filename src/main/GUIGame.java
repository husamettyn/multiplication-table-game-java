package main;

import java.awt.Color;
import java.util.Calendar;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

@SuppressWarnings("unused")
public class GUIGame {

	public JFrame frame;
	private Timer timer;
	private JTextField textField;
	private int id;
	private int score;
	private int secondsPassed = 0;
	private int start, end;


	public GUIGame(student stu, int index) {
		initialize(stu, index);
	}

	private void initialize(student stu, int index) {
		id = 0;
		score = 0;
		start = 0;
		
		game game = stu.getGames().get(index);
		
		int max = game.getQuests().size();
		question q = game.getQuests().get(id);
		
		Calendar zaman = Calendar.getInstance();
		int gameScore = 100/game.getQuests().size();
		game.setStart(zaman.getTime());
		q.setStartDate(zaman.getTime());
		
		frame = new JFrame();
	    frame.setBounds(100, 100, 546, 358);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
	    frame.setLocationRelativeTo(null);
	    
	    JPanel panel_1_1 = new JPanel();
	    panel_1_1.setLayout(null);
	    panel_1_1.setBorder(new LineBorder(Color.GRAY, 1, true));
	    panel_1_1.setBounds(358, 57, 144, 65);
	    frame.getContentPane().add(panel_1_1);
	    
	    JLabel lblNewLabel_3_1 = new JLabel("Skor");
	    lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblNewLabel_3_1.setBounds(10, 10, 72, 45);
	    panel_1_1.add(lblNewLabel_3_1);
	    
	    JLabel skor = new JLabel("");
	    skor.setBounds(59, 16, 85, 33);
	    panel_1_1.add(skor);
	    skor.setHorizontalAlignment(SwingConstants.CENTER);
	    skor.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    
	    skor.setText("" + score);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBorder(new LineBorder(Color.GRAY, 1, true));
	    panel_1.setBounds(23, 57, 144, 65);
	    frame.getContentPane().add(panel_1);
	    panel_1.setLayout(null);
	    
	    JLabel time = new JLabel("");
	    time.setBounds(62, 10, 72, 48);
	    panel_1.add(time);
	    time.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    time.setHorizontalAlignment(SwingConstants.TRAILING);
	    
	    JLabel lblNewLabel_3 = new JLabel("Süren");
	    lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblNewLabel_3.setBounds(10, 10, 72, 45);
	    panel_1.add(lblNewLabel_3);
	    
	    JLabel lblSoru = new JLabel("Soru ");
	    lblSoru.setHorizontalAlignment(SwingConstants.CENTER);
	    lblSoru.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblSoru.setBounds(148, 57, 230, 40);
	    frame.getContentPane().add(lblSoru);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(null);
	    panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    panel.setBounds(0, 0, 532, 40);
	    frame.getContentPane().add(panel);

	    JLabel lblNewLabel = new JLabel("Oyun: " + game.getName());
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblNewLabel.setBounds(10, 6, 512, 30);
	    panel.add(lblNewLabel);
	    
	    JLabel soru = new JLabel(q.getA() + " x " + q.getB() + " ?");
	    soru.setHorizontalAlignment(SwingConstants.CENTER);
	    soru.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    soru.setBounds(148, 96, 230, 40);
	    frame.getContentPane().add(soru);
	    
	    textField = new JTextField();
	    textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    textField.setColumns(10);
	    textField.setBounds(210, 158, 107, 40);
	    frame.getContentPane().add(textField);
	    
	    JLabel feedSoru = new JLabel();
	    feedSoru.setHorizontalAlignment(SwingConstants.CENTER);
	    feedSoru.setBounds(210, 135, 107, 13);
	    frame.getContentPane().add(feedSoru);
	    lblSoru.setText("Soru: " + (id+1) + " / " + max);
	    
	    timer = new Timer(1000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            secondsPassed++;
	            game.setTime(secondsPassed);
	            int min = secondsPassed/60;
	        	int second = secondsPassed%60;
	        	if(min == 0) {
	        		if(second < 10) {
	        			time.setText(" : 0" + second);
	        		}else {
	        			time.setText(" : " + second);
	        		}
	        		
	        	}
	        	else {
	        		time.setText(min + " : " + second);
	        	}
	        	
	        	
	        }
	    });
	    timer.start();
	    
	    JButton button = new JButton("Cevapla");
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		question q = game.getQuests().get(id);
	    		Calendar zaman = Calendar.getInstance();
	    		
	    		end = secondsPassed;
	    		q.setEnd(end);
	    		q.setEndDate(zaman.getTime());
	    		
	    		if(textField.getText().isEmpty()) {
	    			feedSoru.setText("Soruyu Boş Bıraktın");
	    			feedSoru.setForeground(Color.black);
	    			q.setStudentAns(0);
	    		}
	    		else {
	    			Integer ans = Integer.parseInt(textField.getText());
		    		q.setStudentAns(ans);
		    		
		    		if(ans == q.getCorrect()) {
		    			q.setSituation(1);
		    		}
		    		else if(textField.getText().isEmpty()){
		    			q.setSituation(0);
		    		}
		    		else {
		    			q.setSituation(-1);
		    		}
		    		
		    		if(q.getSituation().equals("Dogru")) {
		    			score += gameScore;
		    			feedSoru.setText("Doğru!");
		    			skor.setText(""+score); 
		    		}
		    		else if(q.getSituation().equals("Yanlis")){
		    			feedSoru.setText("Yanlış!");
		    			feedSoru.setForeground(Color.RED);
		    		}
	    		}
	    		id++;
	    		if(id == max) {
	    			
	    			if(score < 100 && score + gameScore > 100) {
	    				score = 100;
	    			}
	    			
	    			stu.getGames().get(index).setScore(score);
	    			stu.setTotalScore(stu.getTotalScore() + score);
	    			stu.getGames().get(index).setCount(game.getCount() + 1);
	    			stu.getGames().get(index).setEnd(zaman.getTime());
	    			timer.stop();
	    			
	    			
	    			GUIUser window = new GUIUser(stu);
                    window.frame.setVisible(true);
                    frame.dispose();
	    			
	    		}else {
	    			question q1 = game.getQuests().get(id);
	    			game.setEnd(zaman.getTime());
	    			q1.setStart(secondsPassed);
	    			q1.setStartDate(zaman.getTime());
		    		soru.setText(q1.getA() + " x " + q1.getB() + " ?");
		    		lblSoru.setText("Soru: " + (id+1) + " / " + max);
		    		textField.setText(null);
	    		}
	    		dataBase.updateStudent(stu, "students.dat");
	    	}
	    });
	    button.setBounds(220, 208, 85, 33);
	    frame.getContentPane().add(button);
	    
	    JLabel lblNewLabel_2 = new JLabel("Boş Bırakmak İçin İnput Girmeden Butona Basabilirsin.");
	    lblNewLabel_2.setBounds(148, 251, 254, 22);
	    frame.getContentPane().add(lblNewLabel_2);
	    
	}
}
