import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ForgotPassword extends JFrame {

	Connection conn ;
	ResultSet rs;
	PreparedStatement pst;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField adminNameField;
	private JTextField secuityQuestionFeidl;
	private JTextField answerField;
	private JTextField newPasswordField;
	private JTextField confirmPasswordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
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
	public ForgotPassword() {
		super("ForgotPassword");
		conn = SqlConnection.databaseConnector(); //getting connection to the database
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 311);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 67, 80, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 92, 48, 41);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Your Security Question :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 129, 162, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Answer :");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 165, 91, 28);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New Password :");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 204, 148, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Forgot password page");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_5.setBounds(92, 11, 304, 30);
		contentPane.add(lblNewLabel_5);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		usernameField.setBounds(168, 72, 172, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		adminNameField = new JTextField();
		adminNameField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		adminNameField.setEditable(false);
		adminNameField.setBounds(168, 103, 172, 20);
		contentPane.add(adminNameField);
		adminNameField.setColumns(10);
		
		secuityQuestionFeidl = new JTextField();
		secuityQuestionFeidl.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		secuityQuestionFeidl.setEditable(false);
		secuityQuestionFeidl.setBounds(168, 138, 172, 20);
		contentPane.add(secuityQuestionFeidl);
		secuityQuestionFeidl.setColumns(10);
		
		answerField = new JTextField();
		answerField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		answerField.setBounds(168, 170, 172, 20);
		contentPane.add(answerField);
		answerField.setColumns(10);
		
		newPasswordField = new JTextField();
		newPasswordField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		newPasswordField.setEditable(false);
		newPasswordField.setBounds(168, 202, 172, 20);
		contentPane.add(newPasswordField);
		newPasswordField.setColumns(10);
		
		JButton SearchButton = new JButton("Search");
		SearchButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//JOptionPane.showMessageDialog(null, textField.getText()); //Used just to check whether the code is query properly or not 
				String sql = "select * from account where username = ?";
				
				//JOptionPane.showMessageDialog(null, sql);		//Used just to check whether the code is query properly or not 
				 try{
					 pst = conn.prepareStatement(sql);
						
						String  userid = usernameField.getText();   //retrieval of username from the textField
						
			       pst.setString(1, userid);// checking UserName to database
			        rs = pst.executeQuery();
					 if(rs.next()) {
						
						 adminNameField.setText(rs.getString(2));		// to  set testField_1 with the name of the  student
						 secuityQuestionFeidl.setText(rs.getString(4));		// to set textField_2 with the security_question provided by user during singup
						 rs.close();								
						 pst.close();								//when multiple data is retrieved we need to close the connection after executing the query
					 }else {
						 JOptionPane.showMessageDialog(null, "Incorrect username");
						 
					 }
				 }catch(Exception e1) {
					 JOptionPane.showMessageDialog(null, e1);
				 }
			}
		});
		SearchButton.setBounds(350, 71, 91, 23);
		contentPane.add(SearchButton);
		
		JButton verifyButton = new JButton("Verify");
		verifyButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		verifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
						
					String query = " select * from account where username = '"+usernameField.getText()+"'&& answer = '"+answerField.getText()+"'";
					Statement s = conn.createStatement();					//query to check whether the entered answer is same as the users answer to the security question
					rs = s.executeQuery(query);
					int flag = 0;
					if(rs.next()) {				//to check whether we have got any output after execution of the previous query
						flag++;
					}
					if(flag == 1) {
						newPasswordField.setEditable(true);			//it enables the change password textfied which was previously disabled
						confirmPasswordField.setEditable(true);
					}
					
					
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null , e2);
				}
				
			}
		});
		verifyButton.setBounds(350, 169, 91, 23);
		contentPane.add(verifyButton);
		
		JButton updateButton = new JButton("Update");
		updateButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					  if(newPasswordField.getText().equals(confirmPasswordField.getText())) 	//condition to check whether the both new password and confirm password are same
					  {
						  
						String query1 = "UPDATE account SET password = '"+newPasswordField.getText()+"' WHERE answer = '"+answerField.getText()+"'&& username = '"+usernameField.getText()+"'";
						Statement st= conn.createStatement();				//query to update the password provided by the user
						st.executeUpdate(query1);
						
						 JOptionPane.showMessageDialog(null , "Password resert was successful");
						  setVisible(false);			//here we hide the ForgetPassword page when Back button is clicked
						  LoginPage lp = new LoginPage();
						  lp.setVisible(true);		//here we make the LoginPage visible (ForgetPassword page will be disabled) when back button is clicked
					
						}else {
							JOptionPane.showMessageDialog(null, "Password Mismatch, Enter again");
						}
					 
				}catch(Exception e3) {
					JOptionPane.showMessageDialog(null , e3);
				}
			}
		});
		updateButton.setBounds(350, 228, 91, 23);
		contentPane.add(updateButton);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(0, 0, 0, 0);
		contentPane.add(lblNewLabel_6);
		
		JLabel label = new JLabel("New label");
		label.setBounds(0, 0, 0, 0);
		contentPane.add(label);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password : ");
		lblConfirmPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblConfirmPassword.setBounds(10, 231, 148, 20);
		contentPane.add(lblConfirmPassword);
		
		confirmPasswordField = new JTextField();
		confirmPasswordField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		confirmPasswordField.setEditable(false);
		confirmPasswordField.setBounds(168, 233, 170, 20);
		contentPane.add(confirmPasswordField);
		confirmPasswordField.setColumns(10);
	}
	
	
	
	/*public void retrieve() {
		

		try {
			
			String userid =  textField.getText();			//to obtain the username from the textField	
			String answer1 = textField_3.getText();			//to obtain the answer provided by the user from textField_3
			String query = "select password from account where username ='"+userid+"' and answer ='"+answer1+"'";	//query to retrieve the password
			pst=conn.prepareStatement(query);				//statement to execute query
			rs = pst.executeQuery(query);					// convert the preparestatement to result set,so that  we can tranverse in the table
			while(rs.next()) {
				textField_4.setText(rs.getString(1));		//retrived password is set in the textField_4
				JOptionPane.showMessageDialog(null, "Password retrieved");
			}
			
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}*/
}
