import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class LoginPage extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn ;
	ResultSet rs;
	PreparedStatement pst;
	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public  LoginPage() {
		super("LoginPage");
		conn = SqlConnection.databaseConnector(); //getting connection to the database
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 421, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("LIBRARY  MANAGEMENT");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("UserName:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel_2);
		
		usernameField = new JTextField();
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		frame.getContentPane().add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		loginButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				try {
					String userid = usernameField.getText();		// to obtain username from the usernameField
					String key = passwordField.getText();			// to  obtain password from the passwordField
					
					String query = " select username,password from account where username ='"+userid+"' and password = '"+key+"'";		//query to check whether the account related to the username and password is present in the database or not
					pst=conn.prepareStatement(query);		// creating a preparedStatement to sent the parameterized statement to the database
					rs = pst.executeQuery(query);			//statement to execute the above query
					int flag = 0;
					if(rs.next()) {
						flag+=1;						// it traverse the output table whether this is perfect match for the given username and password
					}
					if(flag == 1) {
						rs.close();
						pst.close();
						setVisible(false);				//here we disable the  LoginPage when Login button is clicked
						Loading ld = new Loading();
						ld.uploading();					//it calls the uploading() method in the Loading class (which is helpful in updating the progress bar)
						ld.setVisible(true);				//here we enable the next page that is Loading page when authentication is done successfully
					}
					else {
						JOptionPane.showMessageDialog(null, "Incorrect username or Password");//when the flag value is greater than 1 it means either the given username or password is incorrect
						rs.close();						//closing resultSet connection
						pst.close();					//closing preparedStatement connection
					}	
					//setVisible(false);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);		// a dialog box is popped when an exception is rised
				}
				
				
			}
		});
		frame.getContentPane().add(loginButton);
		
		JButton signupButton = new JButton("SignUp");
		signupButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);					//disabling LoginPage when signUp button is clicked 
				NewAccount na = new NewAccount();
				na.setVisible(true);				//redirecting to the signUp page
			}
		});
		frame.getContentPane().add(signupButton);
		
		JButton forgetpasswordButton = new JButton("Forget Password");
		forgetpasswordButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		forgetpasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);						//disabling the LoginPage when Forgetpassword button is clicked
				ForgotPassword fp = new ForgotPassword();
				fp.setVisible(true);				//redirecting to the forgotPassword page
			}
		});
		frame.getContentPane().add(forgetpasswordButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setToolTipText("Login");
		frame.getContentPane().add(panel);
		panel.setLayout(null);
	}

	

}
