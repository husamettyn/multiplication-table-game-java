package main;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;

@SuppressWarnings("unused")
public class GUIUser extends dataBase{

	public JFrame frame;
	private JPanel pan;
	private JTextField textField;

	/**
	 * Create the application.
	 */
	public GUIUser(student s) {
		initialize(s);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(student s) {
	    ArrayList<game> games = readGames("games.dat");

	    frame = new JFrame();
	    frame.setBounds(100, 100, 760, 613);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
	    frame.setLocationRelativeTo(null);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(null);
	    panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    panel.setBounds(0, 0, 745, 40);
	    frame.getContentPane().add(panel);

	    JButton btnNewButton = new JButton("Çıkış Yap");
	    btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            EventQueue.invokeLater(new Runnable() {
	                public void run() {
	                    try {
	                    	updateStudent(s, "students.dat");
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

	    JLabel lblNewLabel = new JLabel("Hoşgeldin " + s.getName());
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblNewLabel.setBounds(237, 6, 250, 30);
	    panel.add(lblNewLabel);

	    String[] columnNames2 = {"ID", "Oyun Adı", "Skor", "Süre"};
        DefaultTableModel model2 = new DefaultTableModel(columnNames2, 0);
		JTable table2 = new JTable(model2);
		table2.setEnabled(false);
		JScrollPane scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(463, 84, 273, 269);
		frame.getContentPane().add(scrollPane2);
		
		ArrayList<game> stuGames = s.getGames();
		for(game g : stuGames) {
			Object[] row2 = {g.getID(), g.getName(), g.getScore() + "/100", g.getTime()};
			model2.addRow(row2);
		}
	    JLabel lblNewLabel_1_1 = new JLabel("Tanımlı Oyunlar");
	    lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblNewLabel_1_1.setBounds(10, 50, 198, 27);
	    frame.getContentPane().add(lblNewLabel_1_1);

	    String[] columnNames = {"ID", "Oyun Adı", "Çözülme Sayısı"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(model);
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 84, 215, 269);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Çözdüğün Oyunlar");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(463, 50, 198, 27);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Oynamak İstediğin Oyunun");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(249, 84, 198, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(293, 160, 34, 34);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNewLabel_1_2 = new JLabel("ID'sini Gir");
		lblNewLabel_1_2.setToolTipText("");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(249, 108, 198, 27);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel feedGame = new JLabel("");
		feedGame.setHorizontalAlignment(SwingConstants.CENTER);
		feedGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		feedGame.setBounds(235, 133, 218, 26);
		frame.getContentPane().add(feedGame);
		
		for(game g : games) {
			Object[] row = {g.getID(), g.getName(), g.getCount()};
			model.addRow(row);
		}
	    
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		int columnIndex = 0;
		int columnWidth = 20; 
		table2.getColumnModel().getColumn(columnIndex).setPreferredWidth(columnWidth);
		table.getColumnModel().getColumn(columnIndex).setPreferredWidth(columnWidth);
		table2.setDefaultRenderer(Object.class, centerRenderer);
		table.setDefaultRenderer(Object.class, centerRenderer);
		
		JButton btnNewButton_1 = new JButton("Oyna");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				if(!textField.getText().isEmpty()) {
					int id = Integer.parseInt(textField.getText());
					for(game stugame : s.getGames()) {
						if(stugame.getID() == id) {
							flag = 1;
							break;
						}
					}
					game newgame = null;
					if(flag == 0) {
						for(game g : games) {
							if(g.getID() == id) {
								flag = 1;
								g.setCount(g.getCount() + 1);
								newgame = new game(g);
								dataBase.writeGames(games, "games.dat");
								break;
							}
						}
						if(flag == 1) {
							s.getGames().add(newgame);
							int size = s.getGames().size();
							dataBase.updateStudent(s, "students.dat");
							Object[] row2 = {newgame.getID(), newgame.getName(), newgame.getScore(), newgame.getTime()};
							model2.addRow(row2);
							try {
		                        GUIGame window = new GUIGame(s, size-1);
		                        window.frame.setVisible(true);
		                        frame.dispose();
		                    } catch (Exception e1) {
		                        e1.printStackTrace();
		                    }
						}
						else {
							feedGame.setText("Belirtilen ID'de Oyun Bulunamadı.");
							feedGame.setForeground(Color.RED);
						}
					}
					else {
						feedGame.setText("Oyun Zaten Oynanmış.");
						feedGame.setForeground(Color.RED);
					}
					
				}
				else {
					feedGame.setText("Lütfen Oyun Seçin.");
					feedGame.setForeground(Color.RED);
				}
			}
			
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(337, 158, 85, 40);
		frame.getContentPane().add(btnNewButton_1);
		
	    frame.setVisible(true);
	    frame.setSize(760, 613);
	}
	
}
