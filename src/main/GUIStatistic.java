package main;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("unused")
public class GUIStatistic {

	public JFrame frame;
	public GUIStatistic(student s, game g, boolean sit) {
		initialize(s, g, sit);
	}

	private void initialize(student s, game g, boolean sit) {
		// öğrenciden gelen sit true
		// game'den gelen sit false
		frame = new JFrame();
		frame.setBounds(100, 100, 819, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		
		JLabel gameName = new JLabel(s.getName() + "'in " + g.getName() + " Verileri");
		gameName.setBounds(84, 10, 353, 48);
		gameName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gameName.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblNewLabel = new JLabel("Soru Verileri");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		String[] columnNames = {"a", "b", "Öğrenci Cevabı", "Doğru Cevap", "Durum","Başlangıç Zamanı", "Bitiş Zamanı"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(10)
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
        			.addContainerGap())
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(panel, GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
        			.addGap(0))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        			.addGap(2)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(25))
        );
        
        
        
        JButton btnGeri = new JButton("Geri");
        btnGeri.setBounds(10, 15, 64, 38);
        btnGeri.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(sit == true) {
        			GUIDetailStudent window = new GUIDetailStudent(s);
                    window.frame.setVisible(true);
                    frame.dispose();
        		}
        		else {
        			GUIDetailGame window = new GUIDetailGame(g);
                    window.frame.setVisible(true);
                    frame.dispose();
        		}
        	}
        });
        btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnGeri.setBackground(Color.WHITE);
        
        
        panel.setLayout(null);
        
        JLabel gameFeed = new JLabel("Oyun İstatistiklerini Dışa Aktar");
        gameFeed.setBounds(582, 0, 213, 28);
        gameFeed.setHorizontalAlignment(SwingConstants.CENTER);
        gameFeed.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JButton show = new JButton("Export Stats");
        show.setBounds(634, 34, 89, 32);
        show.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(dataBase.ExcelExporter(s, g, s.getName() + "'ın " + g.getID() + " ID'li Oyun Verileri.xls", 2)) {
        			gameFeed.setText("Veri Başarıyla Kaydedildi.");
        		}
        		else {
        			gameFeed.setText("Dosya Yazma Hatası.");
        			gameFeed.setForeground(Color.RED);
        		}
        	}
        });
        
        panel.add(gameFeed);
        panel.add(btnGeri);
        panel.add(gameName);
        panel.add(show);
        frame.getContentPane().setLayout(groupLayout);
        
        for(game game : s.getGames()) {
        	if(game.getID() == g.getID()) {
        		for(question q : game.getQuests()) {
            		Object[] row = {q.getA(), q.getB(), q.getStudentAns(), q.getCorrect(), q.getSituation(), q.getStartDate(), q.getEndDate()};
        			model.addRow(row);
            	}
        		break;
        	}
        }
        
    	
    	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        int columnIndex = 0;
		int columnWidth = 20; 
		table.getColumnModel().getColumn(columnIndex).setPreferredWidth(columnWidth);
		table.setDefaultRenderer(Object.class, centerRenderer);
	}
}
