package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;

@SuppressWarnings("unused")
public class GUILog{

	public JFrame frame;
	private JFrame logged;
	private JTextField userText;
	private JTextField passText;
	private JTextField isimSign;
	private JTextField usernameSign;
	private JTextField passSign;

	/**
	 * Create the application.
	 */
	public GUILog() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 760, 613);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setBounds(226, 98, 283, 327);
		frame.getContentPane().add(tabbedPane);
		
		JPanel Login_1 = new JPanel();
		tabbedPane.addTab("Login", null, Login_1, null);
		Login_1.setLayout(null);
	
		JLabel feedLogin = new JLabel("");
		feedLogin.setHorizontalAlignment(SwingConstants.CENTER);
		feedLogin.setBounds(38, 138, 203, 26);
		Login_1.add(feedLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(65, 21, 62, 17);
		Login_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		userText = new JTextField();
		userText.setBounds(65, 42, 146, 26);
		Login_1.add(userText);
		userText.setForeground(new Color(80, 80, 80));
		userText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userText.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setBounds(65, 88, 59, 17);
		Login_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		passText = new JTextField();
		passText.setBounds(65, 107, 146, 26);
		Login_1.add(passText);
		passText.setForeground(new Color(80, 80, 80));
		passText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passText.setColumns(10);
		
		JButton Login = new JButton("Login");
		Login.setBackground(new Color(255, 255, 255));
		Login.setBounds(78, 167, 114, 35);
		Login_1.add(Login);
		Login.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JTextArea txtrAdminGiriiIin = new JTextArea();
		txtrAdminGiriiIin.setText("admin girişi için \"husam:1234\" kullanabilirsiniz.");
		txtrAdminGiriiIin.setBounds(10, 212, 255, 17);
		Login_1.add(txtrAdminGiriiIin);
		
		Login.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        login log = new login();
		        if(userText.getText().isEmpty() || passText.getText().isEmpty()) {
		            feedLogin.setText("Bütün Alanları Doldurun.");
		            feedLogin.setForeground(Color.RED);
		        }
		        else if(userText.getText().equals("husam")) {
		            admin adm = log.loginAdm(userText.getText(), passText.getText(), "students.dat");
		            if(adm != null) {
		            	GUIAdmin window = new GUIAdmin();
                        window.frame.setVisible(true);
		                frame.dispose();
		            }
		            else {
		            	userText.setText(null);
		            	passText.setText(null);
		            	feedLogin.setText("Giriş Başarısız.");
			            feedLogin.setForeground(Color.RED);
		            }
		        }
		        else {
		            student stu = log.loginUser(userText.getText(), passText.getText(), "students.dat");
		            if(stu != null) {
		            	GUIUser window = new GUIUser(stu);
                        window.frame.setVisible(true);
                        frame.dispose();
		            }
		            else {
		            	userText.setText(null);
		            	passText.setText(null);
		            	feedLogin.setText("Giriş Başarısız.");
			            feedLogin.setForeground(Color.RED);
		            }
		        }
		        
		    }
		});

		
		JPanel Sign_up = new JPanel();
		tabbedPane.addTab("Sign Up", null, Sign_up, null);
		Sign_up.setLayout(null);
		
		JLabel feedSign = new JLabel("");
		feedSign.setHorizontalAlignment(SwingConstants.CENTER);
		feedSign.setFont(new Font("Tahoma", Font.PLAIN, 10));
		feedSign.setBounds(40, 200, 200, 13);
		Sign_up.add(feedSign);
		
		JLabel lblNewLabel_1_2 = new JLabel("İsim");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(68, 10, 62, 17);
		Sign_up.add(lblNewLabel_1_2);
		
		isimSign = new JTextField();
		isimSign.setForeground(new Color(80, 80, 80));
		isimSign.setFont(new Font("Tahoma", Font.PLAIN, 16));
		isimSign.setColumns(10);
		isimSign.setBounds(68, 31, 146, 26);
		Sign_up.add(isimSign);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Kullanıcı Adı");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(68, 77, 128, 17);
		Sign_up.add(lblNewLabel_1_1_1);
		
		usernameSign = new JTextField();
		usernameSign.setForeground(new Color(80, 80, 80));
		usernameSign.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usernameSign.setColumns(10);
		usernameSign.setBounds(68, 96, 146, 26);
		Sign_up.add(usernameSign);
		tabbedPane.setEnabledAt(1, true);
		tabbedPane.setDisabledIconAt(1, null);
		
		JButton SignUp = new JButton("Sign Up");
		SignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUp log = new signUp();
				if(isimSign.getText().isEmpty() || usernameSign.getText().isEmpty() || passSign.getText().isEmpty()) {
					feedSign.setText("Lütfen Bütün Boşlukları Doldurun.");
					feedSign.setForeground(Color.RED);
				}
				else if(usernameSign.getText().equals("husam")) {
					isimSign.setText(null);
					usernameSign.setText(null);
					passSign.setText(null);
					feedSign.setText("Kullanıcı Zaten Ekli");
					feedSign.setForeground(Color.RED);
				}
				else if(log.signStudent(isimSign.getText(), usernameSign.getText(), passSign.getText(), "students.dat")) {
					isimSign.setText(null);
					usernameSign.setText(null);
					passSign.setText(null);
					feedSign.setText("Kullanıcı Başarıyla Oluşturuldu.");
					feedSign.setForeground(Color.GREEN);
				}
				else {
					isimSign.setText(null);
					usernameSign.setText(null);
					passSign.setText(null);
					feedSign.setText("Kullanıcı Zaten Ekli");
					feedSign.setForeground(Color.RED);
				}
				
			}
		});
		SignUp.setFont(new Font("Tahoma", Font.PLAIN, 22));
		SignUp.setBackground(Color.WHITE);
		SignUp.setBounds(82, 221, 114, 35);
		Sign_up.add(SignUp);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(68, 149, 59, 17);
		Sign_up.add(lblNewLabel_1_1_1_1);
		
		passSign = new JTextField();
		passSign.setForeground(new Color(80, 80, 80));
		passSign.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passSign.setColumns(10);
		passSign.setBounds(68, 168, 146, 26);
		Sign_up.add(passSign);
		
		
		
		JLabel lblNewLabel = new JLabel("Çarpma Tablosu Ezberleme Oyunu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(180, 10, 376, 58);
		frame.getContentPane().add(lblNewLabel);
	}
}
