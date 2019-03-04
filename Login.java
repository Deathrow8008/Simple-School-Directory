package NET4010;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	Connection cn;
	Statement st;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		myconnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setBounds(167, 49, 87, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(167, 112, 87, 14);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(280, 46, 120, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(280, 109, 120, 20);
		contentPane.add(passwordField);
		
		JButton btnSignup = new JButton("SIGNUP");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignIn().setVisible(true);
				dispose();
			}
		});
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSignup.setBounds(165, 180, 89, 23);
		contentPane.add(btnSignup);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/yellowpages", "root", "");
					String sql="Select * from signup where Username='"+txtUsername.getText()+"' AND Password='"+passwordField.getText()+"'";
					ResultSet rs=st.executeQuery(sql);
					new Form().setVisible(true);
					dispose();
							
							if(rs.next()) {
								JOptionPane.showMessageDialog(null, "Login Successfully...!!!");
						new Form().setVisible(true);
						dispose();
							}
							else
								JOptionPane.showMessageDialog(null, "Incorrect Username and Password!!!");
								cn.close();
				}
				catch(Exception e1) {
					System.out.print(e1);
					
				}
			}
			
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogin.setBounds(311, 180, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Mike\\Desktop\\220px-Yellow_Pages_logo.svg.png"));
		label.setBounds(0, 0, 167, 250);
		contentPane.add(label);
		
		
		JLabel lblWelcomeYellow = new JLabel("WELCOME TO YELLOW PAGES");
		lblWelcomeYellow.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblWelcomeYellow.setBounds(43, 0, 357, 38);
		contentPane.add(lblWelcomeYellow);
	}
	private void myconnection() {
		// TODO Auto-generated method stub
		try {
			cn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/yellowpages", "root", "");
			st=(Statement) cn.createStatement();
			//st.executeUpdate(query);
			rs= st.executeQuery("select * from signup");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
