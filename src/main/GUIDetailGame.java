package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIDetailGame {

	public JFrame frame;
	private JTextField textField;

	public GUIDetailGame(game g) {
		initialize(g);
	}
	private void initialize(game g) {
		
		ArrayList<student> students = dataBase.fileRead("students.dat");
		
		frame = new JFrame();
		frame.setBounds(100, 100, 819, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Oyunu Çözen Öğrenciler");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		String[] columnNames = {"Öğrenci Adı", "Kullanıcı Adı", "Skor", "Süre", "Başlangıç Zamanı", "Bitiş Zamanı"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JPanel panel_1 = new JPanel();
        
        JButton btnGeri_1 = new JButton("Geri");
        btnGeri_1.setBounds(10, 15, 64, 38);
        btnGeri_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUIAdmin window = new GUIAdmin();
                window.frame.setVisible(true);
                frame.dispose();
        	}
        });
        btnGeri_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnGeri_1.setBackground(Color.WHITE);
        
        JLabel gameName_1 = new JLabel(g.getName());
        gameName_1.setBounds(84, 10, 280, 48);
        gameName_1.setHorizontalAlignment(SwingConstants.LEFT);
        gameName_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBounds(499, 0, 296, 64);
        panel_1_1.setLayout(null);
        
        JLabel feedInput = new JLabel("Ayrıntısını Görüntülemek İçin Öğrenci Kullanıcı Adını Giriniz.");
        feedInput.setBounds(10, 10, 286, 13);
        panel_1_1.add(feedInput);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(79, 29, 122, 35);
        panel_1_1.add(textField);
        
        JLabel lblNewLabel_1_1 = new JLabel("username");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1.setBounds(0, 30, 75, 28);
        panel_1_1.add(lblNewLabel_1_1);
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
        			.addContainerGap())
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 795, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())
        				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
        					.addGap(636))))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        			.addGap(2)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(25))
        );
        frame.getContentPane().setLayout(groupLayout);
        
        for (student stu : students) {
        	ArrayList<game> stugames = stu.getGames();
        	for(game game : stugames) {
        		if(game.getID() == g.getID()) {
        			Object[] row = {stu.getName(), stu.getNickname(), game.getScore(), game.getTime(), game.getStart(), game.getEnd()};
        			model.addRow(row);
        			break;
        		}
        	}
		}
        
        JButton show_1 = new JButton("Görüntüle");
        show_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(textField.getText().isEmpty()) {
        			feedInput.setText("Boşluğu Doldurunuz.");
            		feedInput.setForeground(Color.RED);
        		}
        		else {
        			for(student stu : students) {
            			if(stu.getNickname().equals(textField.getText())) {
            				GUIStatistic window = new GUIStatistic(stu, g, false);
                            window.frame.setVisible(true);
    		                frame.dispose();
            				return;
            			}
            		}
            		feedInput.setText("Öğrenci Bulunamadı.");
            		feedInput.setForeground(Color.RED);
        		}
        	}
        });
        show_1.setBounds(207, 30, 77, 32);
        panel_1_1.add(show_1);
        panel_1.setLayout(null);
        panel_1.add(btnGeri_1);
        panel_1.add(gameName_1);
        panel_1.add(panel_1_1);
        
        JLabel gameFeed = new JLabel("Oyun Verilerini Dışa Aktar");
        gameFeed.setHorizontalAlignment(SwingConstants.CENTER);
        gameFeed.setFont(new Font("Tahoma", Font.PLAIN, 14));
        gameFeed.setBounds(286, 5, 213, 28);
        panel_1.add(gameFeed);
        
        JButton show_1_1 = new JButton("Export Stats");
        show_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(dataBase.ExcelExporter(null, g, g.getID() + " ID'li Oyun'un Verileri.xls", 3)) {
        			gameFeed.setText("Veri Başarıyla Kaydedildi.");
        		}
        		else {
        			gameFeed.setText("Dosya Yazma Hatası.");
        			gameFeed.setForeground(Color.RED);
        		}
        	}
        });
        show_1_1.setBounds(347, 32, 89, 32);
        panel_1.add(show_1_1);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		int columnIndex = 0;
		int columnWidth = 20; 
		table.getColumnModel().getColumn(columnIndex).setPreferredWidth(columnWidth);
		table.setDefaultRenderer(Object.class, centerRenderer);
	}
}
