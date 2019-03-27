import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.sql.*;

public class NewAccount extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn ;
	ResultSet rs;
	PreparedStatement pst;
	private JPanel contentPane;
	private JTextField adminNameField;
	private JTextField passwordField;
	private JTextField answerField;
	private JTextField usernameFeild;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewAccount frame = new NewAccount();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NewAccount() {
		
		super("LoginPage");
		conn = SqlConnection.databaseConnector(); //getting connection to the database
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" SIGN UP   PAGE");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(112, 11, 232, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(29, 87, 48, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(29, 53, 77, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password :");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(29, 121, 77, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Security Question:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(29, 156, 122, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Answer :");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_5.setBounds(29, 189, 102, 14);
		contentPane.add(lblNewLabel_5);
		
		adminNameField = new JTextField();
		adminNameField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		adminNameField.setBounds(169, 85, 175, 20);
		contentPane.add(adminNameField);
		adminNameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		passwordField.setBounds(169, 119, 175, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		answerField = new JTextField();
		answerField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		answerField.setBounds(169, 187, 175, 20);
		contentPane.add(answerField);
		answerField.setColumns(10);
		
		JComboBox securityQuestion = new JComboBox();
		securityQuestion.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		securityQuestion.setModel(new DefaultComboBoxModel(new String[] {"what is your nickname?", "what is your favrouite color?", "what is your lucky number?", "Favrouite sports?"}));
		securityQuestion.setBounds(169, 153, 175, 22);
		contentPane.add(securityQuestion);
		
		JButton signUp = new JButton("SignUp");
		signUp.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				try {
					String createNewAccount = "Insert into account (username,name,password,security_question,answer) values (?,?,?,?,?)";
																							//query to insert new account details into the account table in library database 
					pst = conn.prepareStatement(createNewAccount);						//Creates a PreparedStatement object for sending parameterized SQL statements to the database
					//JOptionPane.showMessageDialog(null, "connected");
					pst.setString(1,usernameFeild.getText());					//retrieving dara from textField and sending the values to the insertion query
					
					pst.setString(2,adminNameField.getText());

					pst.setString(3,passwordField.getText());
				
					pst.setString(4,(String)securityQuestion.getSelectedItem());
			
					pst.setString(5,answerField.getText());
		
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "New Account Created");
					//rs.close();
					//pst.close();
					setVisible(false);	//this helps to hide the NewAccount page
					LoginPage lp = new LoginPage();
					lp.setVisible(true); 
					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		signUp.setFont(new Font("Times New Roman", Font.BOLD, 14));
		signUp.setBounds(137, 218, 175, 32);
		contentPane.add(signUp);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 23, 0, 2);
		contentPane.add(panel);
		
		usernameFeild = new JTextField();
		usernameFeild.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		usernameFeild.setBounds(169, 53, 175, 20);
		contentPane.add(usernameFeild);
		usernameFeild.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 32, 0, 10);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 44, 0, -2);
		contentPane.add(panel_2);
		
		
	}
}
