package main;


import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

@SuppressWarnings("unused")
public class GUIAdmin extends dataBase{

	public JFrame frame;
	private JTextField aField;
	private JTextField bField;
	private JTextField nField;
	private JTextField deleteUser;
	private JTextField gameText;
	private JTextField usernameField;


	/**
	 * Create the application.
	 */
	public GUIAdmin(){
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ArrayList<student> students = fileRead("students.dat");
		ArrayList<game> games = readGames("games.dat");
		
		frame = new JFrame();
		frame.setBounds(100, 100, 760, 613);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 745, 40);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUILog window = new GUILog();
							window.frame.setVisible(true);
							frame.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(645, 5, 90, 26);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldin *Admin*");
		lblNewLabel.setBounds(237, 6, 250, 30);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
        String[] columnNames = {"Öğrenci Adı", "Kullanıcı Adı", "Toplam Skor"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(242, 80, 269, 269);
        frame.getContentPane().add(scrollPane);
        for (student stu : students) {
			Object[] row = {stu.getName(), stu.getNickname(), stu.getTotalScore()};
			model.addRow(row);
		}
        
        String[] columnNames2 = {"ID", "Oyun Adı", "Çözülme Sayısı"};
        DefaultTableModel model2 = new DefaultTableModel(columnNames2, 0);
		JTable table2 = new JTable(model2);
		table2.setEnabled(false);
		
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// İstenilen column'ın genişliğini ayarlayın
		int columnIndex = 0; // İstenilen column'ın indeksi
		int columnWidth = 20; // İstenilen column'ın genişliği
		table2.getColumnModel().getColumn(columnIndex).setPreferredWidth(columnWidth);
		table2.setDefaultRenderer(Object.class, centerRenderer);
		table.setDefaultRenderer(Object.class, centerRenderer);
		
		
		JScrollPane scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(521, 80, 215, 269);
		frame.getContentPane().add(scrollPane2);
		for(game g : games) {
			Object[] row2 = {g.getID(), g.getName(), g.getCount()};
			model2.addRow(row2);
		}
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(10, 80, 222, 269);
        frame.getContentPane().add(panel_2);
        panel_2.setBorder(new LineBorder(Color.GRAY, 1, true));
        panel_2.setLayout(null);
        
        JLabel feedGame = new JLabel("");
        feedGame.setHorizontalAlignment(SwingConstants.CENTER);
        feedGame.setFont(new Font("Tahoma", Font.PLAIN, 10));
        feedGame.setBounds(10, 190, 200, 13);
        panel_2.add(feedGame);
        
        JLabel a = new JLabel("a");
        a.setFont(new Font("Tahoma", Font.PLAIN, 14));
        a.setBounds(38, 23, 62, 17);
        panel_2.add(a);
        
        aField = new JTextField();
        aField.setForeground(new Color(80, 80, 80));
        aField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        aField.setColumns(10);
        aField.setBounds(38, 44, 146, 26);
        panel_2.add(aField);
        
        JLabel b = new JLabel("b");
        b.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b.setBounds(38, 80, 128, 17);
        panel_2.add(b);
        
        bField = new JTextField();
        bField.setForeground(new Color(80, 80, 80));
        bField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        bField.setColumns(10);
        bField.setBounds(38, 99, 146, 26);
        panel_2.add(bField);
        
        JButton addGame = new JButton("Ekle");
        addGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(aField.getText().isEmpty() || bField.getText().isEmpty() || nField.getText().isEmpty()) {
        			feedGame.setText("Bütün Alanları Doldurun!");
        			feedGame.setForeground(new Color(255, 0, 0));
        		}
        		else{
        			int a = Integer.parseInt(aField.getText());
            		int b = Integer.parseInt(bField.getText());
            		int n = Integer.parseInt(nField.getText());
            		if(a<b && (b-a+1)*2 >= n) {
            			game newgame = new game(a, b, n);
            			try {
            				ArrayList<game> games = readGames("games.dat");
            				for (game g : games) {
            					if(g.getName().equals(newgame.getName())) {
            						feedGame.setText("Bu Oyun Zaten Tanımlı.");
                        			feedGame.setForeground(new Color(255, 0, 0));
                        			aField.setText(null);
                        			bField.setText(null);
                        			nField.setText(null);
            						return;
            					}
            				}
            				games.add(newgame);
            				writeGames(games, "games.dat");
                			
            			}
            			catch(Exception e1) {
            				ArrayList<game> games = new ArrayList<>();
            				games.add(newgame);
            				writeGames(games, "games.dat");
            			}
            			Object[] row3 = {newgame.getID(), newgame.getName(), newgame.getCount()};
            			model2.addRow(row3);
            			feedGame.setText("Oyun Tanımlandı.");
            			feedGame.setForeground(new Color(0, 0, 0));
            		}
            		else {
            			feedGame.setText("Geçersiz Girdi.");
            			feedGame.setForeground(new Color(255, 0, 0));
            			aField.setText(null);
            			bField.setText(null);
            			nField.setText(null);
            		}
        		}
        	}
        });
        addGame.setFont(new Font("Tahoma", Font.PLAIN, 22));
        addGame.setBackground(Color.WHITE);
        addGame.setBounds(52, 211, 114, 35);
        panel_2.add(addGame);
        
        JLabel n = new JLabel("n");
        n.setFont(new Font("Tahoma", Font.PLAIN, 14));
        n.setBounds(38, 135, 59, 17);
        panel_2.add(n);
        
        nField = new JTextField();
        nField.setForeground(new Color(80, 80, 80));
        nField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nField.setColumns(10);
        nField.setBounds(38, 154, 146, 26);
        panel_2.add(nField);
        
        JLabel lblNewLabel_2 = new JLabel("Öğrenciler");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(252, 50, 172, 27);
        frame.getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Oyunlar");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_2_1.setBounds(531, 50, 172, 27);
        frame.getContentPane().add(lblNewLabel_2_1);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(Color.GRAY, 1, true));
        panel_1.setBounds(10, 348, 222, 179);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(10, 49, 62, 17);
        panel_1.add(lblUsername);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JLabel feedDelete = new JLabel("");
        feedDelete.setBounds(10, 99, 182, 13);
        panel_1.add(feedDelete);
        
        deleteUser = new JTextField();
        deleteUser.setBounds(10, 70, 106, 26);
        panel_1.add(deleteUser);
        deleteUser.setForeground(new Color(80, 80, 80));
        deleteUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
        deleteUser.setColumns(10);
        
        JButton btnSil = new JButton("Sil");
        btnSil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		for(student s : students) {
        			if(s.getNickname().equals(deleteUser.getText())) {
        				students.remove(s);
        				dataBase.fileWrite("students.dat", students);
        				GUIAdmin window = new GUIAdmin();
                        window.frame.setVisible(true);
		                frame.dispose();
		                return;
        			}
        		}
        		feedDelete.setText("Öğrenci Bulunamadı.");
        		feedDelete.setForeground(Color.RED);
        	}
        });
        btnSil.setBounds(10, 117, 106, 27);
        panel_1.add(btnSil);
        btnSil.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSil.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_1 = new JLabel("Öğrenci Sil");
        lblNewLabel_1_1.setBounds(10, 10, 106, 29);
        panel_1.add(lblNewLabel_1_1);
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBorder(new LineBorder(Color.GRAY, 1, true));
        panel_1_1.setBounds(242, 348, 269, 179);
        frame.getContentPane().add(panel_1_1);
        
        JLabel lblUsername_1 = new JLabel("Game ID");
        lblUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsername_1.setBounds(10, 49, 62, 17);
        panel_1_1.add(lblUsername_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Oyun Ayrıntısı Görüntüle");
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1_1.setBounds(10, 10, 227, 29);
        panel_1_1.add(lblNewLabel_1_1_1);
        
        JButton btnIncele = new JButton("İncele");
        btnIncele.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(gameText.getText().isEmpty()) {
        			
        		}else {
        			int id = Integer.parseInt(gameText.getText());
        			for(game game : games) {
        				if(game.getID() == id) {
        					GUIDetailGame window = new GUIDetailGame(game);
                            window.frame.setVisible(true);
    		                frame.dispose();
        					return;
        				}
        			}
        		}
        	}
        });
        btnIncele.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnIncele.setBackground(Color.WHITE);
        btnIncele.setBounds(10, 117, 106, 27);
        panel_1_1.add(btnIncele);
        
        JLabel feedGameInfo = new JLabel("");
        feedGameInfo.setBounds(10, 99, 227, 13);
        panel_1_1.add(feedGameInfo);
        
        gameText = new JTextField();
        gameText.setForeground(new Color(80, 80, 80));
        gameText.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gameText.setColumns(10);
        gameText.setBounds(10, 70, 106, 26);
        panel_1_1.add(gameText);
        
        JPanel panel_1_2 = new JPanel();
        panel_1_2.setLayout(null);
        panel_1_2.setBorder(new LineBorder(Color.GRAY, 1, true));
        panel_1_2.setBounds(521, 348, 215, 179);
        frame.getContentPane().add(panel_1_2);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Öğrenci Ayrıntısı Görüntüle");
        lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1_2.setBounds(10, 10, 205, 29);
        panel_1_2.add(lblNewLabel_1_1_2);
        
        JLabel feedUser = new JLabel("");
        feedUser.setBounds(10, 99, 195, 13);
        panel_1_2.add(feedUser);
        
        usernameField = new JTextField();
        usernameField.setForeground(new Color(80, 80, 80));
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        usernameField.setColumns(10);
        usernameField.setBounds(10, 70, 106, 26);
        panel_1_2.add(usernameField);
        
        JLabel lblUsername_3 = new JLabel("Username");
        lblUsername_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsername_3.setBounds(10, 49, 62, 17);
        panel_1_2.add(lblUsername_3);
        
        JButton btnIncele_1 = new JButton("İncele");
        btnIncele_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		for(student stu : students) {
        			if(stu.getNickname().equals(usernameField.getText())){
        				GUIDetailStudent window = new GUIDetailStudent(stu);
                        window.frame.setVisible(true);
		                frame.dispose();
    					return;
        			}
        		}
        		feedUser.setText("Kullanıcı Bulunamadı.");
        		feedUser.setForeground(Color.RED);
        	}
        });
        btnIncele_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnIncele_1.setBackground(Color.WHITE);
        btnIncele_1.setBounds(10, 120, 106, 27);
        panel_1_2.add(btnIncele_1);
        
        JLabel feedStu = new JLabel("");
        feedStu.setBounds(10, 99, 106, 13);
        panel_1_2.add(feedStu);
        
        JLabel lblNewLabel_1 = new JLabel("Oyun Ekle");
        lblNewLabel_1.setBounds(23, 50, 106, 29);
        frame.getContentPane().add(lblNewLabel_1);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        
	}
}
