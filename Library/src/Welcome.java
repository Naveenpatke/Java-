import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Welcome extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome frame = new Welcome();
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
	public Welcome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 389);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);					//disabling the welcome page
				LoginPage lp = new LoginPage();
				lp.setVisible(true);				//redirecting to the visible page
				
			}
		});
		mnFile.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);			//complete exit from the application
			}
		});
		mnFile.add(mntmExit);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO");
		lblNewLabel.setBounds(134, 11, 163, 34);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("LIBRARY MANAGEMENT SYSTEM");
		lblNewLabel_1.setBounds(39, 40, 333, 26);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		getContentPane().add(lblNewLabel_1);
		
		JButton newBookButton = new JButton("New Book");
		newBookButton.setBounds(39, 108, 146, 39);
		newBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);					//disables the welcome page
				NewBook nb = new NewBook();
				nb.setVisible(true);				//display the newBook page where you can insert new book into the database
				
			}
		});
		newBookButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(newBookButton);
		
		JButton newStudentButton = new JButton("New Student");
		newStudentButton.setBounds(216, 108, 137, 39);
		newStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);				//disabling welcome page
				Student s = new Student();
				s.setVisible(true);				//redirecting to Student page
			}
		});
		newStudentButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(newStudentButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 86, 373, 75);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "INSERT OPERATIONS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton issueBookButton = new JButton("Issue Book");
		issueBookButton.setBounds(39, 201, 146, 34);
		issueBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);			//disabling welcome page
				IssueBook ib = new IssueBook();
				ib.setVisible(true);			//redirecting to IssueBook page
			}
		});
		issueBookButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		getContentPane().add(issueBookButton);
		
		JButton returnBookButton = new JButton("Return Book");
		returnBookButton.setBounds(216, 201, 137, 34);
		returnBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);				//disabling the welcome page
				ReturnBook rb = new ReturnBook();
				rb.setVisible(true);		//redirecting to the returnBook page
			}
		});
		returnBookButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		getContentPane().add(returnBookButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 184, 373, 122);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "OPERATIONS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton deletionPortalButton = new JButton("Deletion Portal");
		deletionPortalButton.setBounds(28, 74, 144, 34);
		panel_1.add(deletionPortalButton);
		deletionPortalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);							//disabling welcome page
				DeletionPortal dp = new DeletionPortal();
				dp.setVisible(true);				//redirecting to DeletionPortal page
				
			}
		});
		deletionPortalButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JButton statisticsButton = new JButton("Statistics");
		statisticsButton.setBounds(209, 74, 133, 34);
		panel_1.add(statisticsButton);
		statisticsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);						//disabling welcome page
				Statistics s = new Statistics();
				s.setVisible(true);						//redirecting to  the statistics page
			}
		});
		statisticsButton.setFont(new Font("Tahoma", Font.BOLD, 12));
	}
}
