import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class DeletionPortal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField studentIdField;
	private JTextField studentNameField;
	private JTextField fatherNameField;
	private JTextField courseField;
	private JTextField branchField;
	private JTextField yearField;
	private JTextField semesterField;
	
	PreparedStatement pst;
	Connection conn;
	ResultSet rs;
	private JTextField librarianNameField1;
	private JTextField bookIdField;
	private JTextField bookNameField;
	private JTextField publisherField;
	private JTextField priceField;
	private JTextField pagesField;
	private JTextField editionField;
	private JTextField librarianNameField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletionPortal frame = new DeletionPortal();
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
	public DeletionPortal() {
		super("DeletionPortal");
		conn = SqlConnection.databaseConnector();  //getting database connection to ye database
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 875, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Book Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(24, 55, 377, 349);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton bookDeletionButton = new JButton("Delete Book Details");
		bookDeletionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String bookDletionFromIssueBook = "delete from issueBook where book_id = '"+bookIdField.getText()+"'";						//query to delete the book_id which is the foriegn key in returnbook table(u cannot delete the primary key withpurr deletiong the foreign key first)
					String bookDeletionFromReturnBook = "delete from returnbook where book_id = '"+bookIdField.getText()+"'";		//query to delete the book_id which is the foriegn key in issuebook table(u cannot delete the primary key withpurr deletiong the foreign key first)
					String bookDeletionFromBookTable = "delete from book where book_id = '"+bookIdField.getText()+"'";			//query to delete the given book_id details from the database
				
					Statement st = conn.createStatement();
					Statement st1 = conn.createStatement();
					Statement st2 = conn.createStatement();
					st.executeUpdate(bookDletionFromIssueBook);			//you need to delete the foreign key first before deleting the primary key
					st1.executeUpdate(bookDeletionFromReturnBook);
					st2.executeUpdate(bookDeletionFromBookTable);
					
				//	JOptionPane.showMessageDialog(null, "hello");
								
						bookIdField.setText(" ");
						bookNameField.setText(" ");
						publisherField.setText(" ");
						priceField.setText(" ");
						pagesField.setText(" ");
						editionField.setText(" ");
						librarianNameField.setText(" ");
						JOptionPane.showMessageDialog(null, "Deletion was successful");
									
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				
				
			}
		});
		bookDeletionButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bookDeletionButton.setBounds(89, 293, 194, 31);
		panel.add(bookDeletionButton);
		
		JLabel label = new JLabel("Book ID :");
		label.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label.setBounds(16, 32, 77, 24);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Name :");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_1.setBounds(16, 67, 77, 24);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Edition :");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_2.setBounds(16, 102, 77, 24);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Publisher :");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_3.setBounds(16, 137, 77, 24);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Price :");
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_4.setBounds(16, 172, 77, 24);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Pages :");
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_5.setBounds(16, 206, 63, 24);
		panel.add(label_5);
		
		JLabel label_14 = new JLabel("Librarian Name :");
		label_14.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_14.setBounds(16, 243, 107, 23);
		panel.add(label_14);
		
		JButton bookSearch = new JButton("Search");
		bookSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String query = "select * from book where book_id = ?";		//query to search given book_id details
					pst = conn.prepareStatement(query);						//Creates a PreparedStatement object for sendingparameterized SQL statements to the database. 
					pst.setString(1, bookIdField.getText());
					rs = pst.executeQuery();
					if(rs.next()) {
						
						bookNameField.setText(rs.getString(2));
						editionField.setText(rs.getString(3));
						publisherField.setText(rs.getString(4));
						priceField.setText(rs.getString(5));
						pagesField.setText(rs.getString(6));
						librarianNameField.setText(rs.getString(7));
						rs.close();
						pst.close();
						
					}else {
						JOptionPane.showMessageDialog(null, "Invalid  Book ID");
						bookIdField.setText("  ");
					}

					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		bookSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bookSearch.setBounds(278, 30, 89, 23);
		panel.add(bookSearch);
		
		bookIdField = new JTextField();
		bookIdField.setColumns(10);
		bookIdField.setBounds(134, 32, 134, 20);
		panel.add(bookIdField);
		
		bookNameField = new JTextField();
		bookNameField.setColumns(10);
		bookNameField.setBounds(134, 67, 134, 20);
		panel.add(bookNameField);
		
		publisherField = new JTextField();
		publisherField.setColumns(10);
		publisherField.setBounds(134, 137, 134, 20);
		panel.add(publisherField);
		
		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(134, 172, 134, 20);
		panel.add(priceField);
		
		pagesField = new JTextField();
		pagesField.setColumns(10);
		pagesField.setBounds(134, 206, 134, 20);
		panel.add(pagesField);
		
		editionField = new JTextField();
		editionField.setColumns(10);
		editionField.setBounds(134, 102, 134, 20);
		panel.add(editionField);
		
		librarianNameField = new JTextField();
		librarianNameField.setColumns(10);
		librarianNameField.setBounds(134, 245, 134, 20);
		panel.add(librarianNameField);
		
		JLabel lblBookDeletionPortal = new JLabel("BOOK  DELETION  PORTAL");
		lblBookDeletionPortal.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblBookDeletionPortal.setBounds(254, 11, 365, 33);
		contentPane.add(lblBookDeletionPortal);
		
		JLabel label_7 = new JLabel("Student ID :");
		label_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_7.setBounds(464, 82, 77, 14);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("Name :");
		label_8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_8.setBounds(464, 117, 48, 14);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("Father Name :");
		label_9.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_9.setBounds(464, 152, 97, 14);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("Course :");
		label_10.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_10.setBounds(464, 187, 65, 14);
		contentPane.add(label_10);
		
		JLabel label_11 = new JLabel("Branch :");
		label_11.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_11.setBounds(464, 222, 65, 14);
		contentPane.add(label_11);
		
		JLabel label_12 = new JLabel("Year :");
		label_12.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_12.setBounds(464, 256, 48, 14);
		contentPane.add(label_12);
		
		JLabel label_13 = new JLabel("Semester :");
		label_13.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_13.setBounds(464, 283, 65, 24);
		contentPane.add(label_13);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(422, 55, -1, 2);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(422, 55, -1, 2);
		contentPane.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Student Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(428, 55, 408, 349);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton studentDeletionButton = new JButton("Delete Student Details");
		studentDeletionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "delete from issueBook where student_id = '"+studentIdField.getText()+"'";		//query to delete the student_id from the issuebook table which is a the foreign key 
					String studentDeletionfromreturnBookTable = " delete from returnbook where student_id ='"+studentIdField.getText()+"'";		//query to delete the dtudent_id from the returnBook table where student_id is the foreign key
					String query1 = "delete from student where student_id = '"+studentIdField.getText()+"'";			//query to delte the given student_id details fromgthe database
					Statement st = conn.createStatement();
					st.executeUpdate(query);
					st.executeUpdate(studentDeletionfromreturnBookTable);
					st.executeUpdate(query1);
					//JOptionPane.showMessageDialog(null, "hello");
								
						studentIdField.setText(" ");
						studentNameField.setText(" ");
						fatherNameField.setText(" ");
						courseField.setText(" ");
						branchField.setText(" ");
						yearField.setText(" ");
						semesterField.setText(" ");
						JOptionPane.showMessageDialog(null, "Deletion was successful");
									
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		studentDeletionButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		studentDeletionButton.setBounds(120, 294, 206, 32);
		panel_3.add(studentDeletionButton);
		
		JLabel label_6 = new JLabel("Librarian Name :");
		label_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_6.setBounds(34, 260, 107, 23);
		panel_3.add(label_6);
		
		librarianNameField1 = new JTextField();
		librarianNameField1.setColumns(10);
		librarianNameField1.setBounds(151, 262, 143, 20);
		panel_3.add(librarianNameField1);
		
		studentIdField = new JTextField();
		studentIdField.setBounds(152, 25, 142, 20);
		panel_3.add(studentIdField);
		studentIdField.setColumns(10);
		
		studentNameField = new JTextField();
		studentNameField.setBounds(152, 59, 142, 20);
		panel_3.add(studentNameField);
		studentNameField.setColumns(10);
		
		fatherNameField = new JTextField();
		fatherNameField.setBounds(152, 94, 142, 20);
		panel_3.add(fatherNameField);
		fatherNameField.setColumns(10);
		
		courseField = new JTextField();
		courseField.setBounds(152, 132, 142, 20);
		panel_3.add(courseField);
		courseField.setColumns(10);
		
		branchField = new JTextField();
		branchField.setBounds(152, 165, 142, 20);
		panel_3.add(branchField);
		branchField.setColumns(10);
		
		yearField = new JTextField();
		yearField.setBounds(152, 198, 142, 20);
		panel_3.add(yearField);
		yearField.setColumns(10);
		
		semesterField = new JTextField();
		semesterField.setBounds(152, 231, 142, 20);
		panel_3.add(semesterField);
		semesterField.setColumns(10);
		
		JButton studentSearchButton = new JButton("Search");
		studentSearchButton.setBounds(309, 23, 89, 23);
		panel_3.add(studentSearchButton);
		studentSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query1 = "select * from student where student_id = ?";		//query to retrieve all the details pf the given student_id
					pst = conn.prepareStatement(query1);							//Creates a PreparedStatement object for sendingparameterized SQL statements to the database. 
					pst.setString(1, studentIdField.getText());
					rs = pst.executeQuery();
					if(rs.next()) {
						
						studentNameField.setText(rs.getString(2));
						fatherNameField.setText(rs.getString(3));
						courseField.setText(rs.getString(4));
						branchField.setText(rs.getString(5));
						yearField.setText(rs.getString(6));
						semesterField.setText(rs.getString(7));
						librarianNameField1.setText(rs.getString(8));
						rs.close();
						pst.close();
					}else {
						JOptionPane.showMessageDialog(null, "Invalid Student ID");
						studentIdField.setText(" ");
					}
					
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		studentSearchButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JButton bacjButton = new JButton("Back");
		bacjButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);			//disabling the deletion portal
				Welcome w = new Welcome();
				w.setVisible(true);			// redirecting to the welcome page
				
			}
		});
		bacjButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bacjButton.setBounds(369, 427, 89, 23);
		contentPane.add(bacjButton);
	}

}
