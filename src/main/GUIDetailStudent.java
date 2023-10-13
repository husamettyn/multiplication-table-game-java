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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class GUIDetailStudent {

	public JFrame frame;
	private JTextField gameIDGet;

	public GUIDetailStudent(student s) {
		initialize(s);
	}
	private void initialize(student s) {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 938, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		ArrayList<game> stugames = s.getGames();
		JPanel panel = new JPanel();
		
		JLabel gameName = new JLabel(s.getName() + "'in Verileri");
		gameName.setBounds(84, 10, 280, 48);
		gameName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gameName.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblNewLabel = new JLabel("Öğrencinin Oyunları");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		String[] columnNames = {"ID", "Oyun Adı", "Skor", "Süre", "Başlangıç Zamanı", "Bitiş Zamanı"};
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
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
        			.addContainerGap())
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        			.addGap(2)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(25))
        );
        
        
        
        JButton btnGeri = new JButton("Geri");
        btnGeri.setBounds(10, 15, 64, 38);
        btnGeri.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GUIAdmin window = new GUIAdmin();
                window.frame.setVisible(true);
                frame.dispose();
        	}
        });
        btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnGeri.setBackground(Color.WHITE);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(618, 0, 296, 64);
        panel_1.setLayout(null);
        
        JLabel feedInput = new JLabel("Ayrıntısını Görüntülemek İçin Oyun ID Giriniz.");
        feedInput.setBounds(10, 10, 203, 13);
        panel_1.add(feedInput);
        
        gameIDGet = new JTextField();
        gameIDGet.setBounds(79, 29, 122, 35);
        panel_1.add(gameIDGet);
        gameIDGet.setColumns(10);
        
        JButton show = new JButton("Görüntüle");
        show.setBounds(207, 30, 77, 32);
        panel_1.add(show);
        
        JLabel lblNewLabel_1 = new JLabel("gameID");
        lblNewLabel_1.setBounds(10, 30, 65, 28);
        panel_1.add(lblNewLabel_1);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        show.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int id = Integer.parseInt(gameIDGet.getText());
        		if(gameIDGet.getText().isEmpty()) {
        			feedInput.setText("Boşluğu Doldurunuz.");
            		feedInput.setForeground(Color.RED);
        		}else {
            		for(game game : s.getGames()) {
            			if(game.getID() == id) {
            				GUIStatistic window = new GUIStatistic(s, game, true);
                            window.frame.setVisible(true);
    		                frame.dispose();
            				return;
            			}
            		}
            		feedInput.setText("Oyun Bulunamadı.");
            		feedInput.setForeground(Color.RED);
        		}
        	}
        });
        JLabel gameFeed = new JLabel("Öğrenci Verilerini Dışa Aktar");
        
        JButton show_1 = new JButton("Export Stats");
        show_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(dataBase.ExcelExporter(s, null, s.getName() + "'ın Verileri.xls", 1)) {
        			gameFeed.setText("Veri Başarıyla Kaydedildi.");
        		}
        		else {
        			gameFeed.setText("Dosya Yazma Hatası.");
        			gameFeed.setForeground(Color.RED);
        		}
        	}
        });
        show_1.setBounds(456, 32, 89, 32);
        panel.setLayout(null);
        
        
        gameFeed.setBounds(395, 5, 213, 28);
        gameFeed.setHorizontalAlignment(SwingConstants.CENTER);
        gameFeed.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(gameFeed);
        panel.add(btnGeri);
        panel.add(gameName);
        panel.add(show_1);
        panel.add(panel_1);
        frame.getContentPane().setLayout(groupLayout);
        
    	
    	for(game game : stugames) {
    		Object[] row = {game.getID(), game.getName(), game.getScore(), game.getTime(), game.getStart(), game.getEnd()};
			model.addRow(row);
    	}
    	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        int columnIndex = 0;
		int columnWidth = 20; 
		table.getColumnModel().getColumn(columnIndex).setPreferredWidth(columnWidth);
		table.setDefaultRenderer(Object.class, centerRenderer);
	}
}
