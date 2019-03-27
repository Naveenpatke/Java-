import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ReturnBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField studentIdField;
	private JTextField bookIdField;
	private JTextField bookNameField;
	private JTextField publisherField;
	private JTextField priceField;
	private JTextField pagesField;
	private JTextField editionField;
	private JTextField studentNameField;
	private JTextField fatherNameField;
	private JTextField courseField;
	private JTextField branchField;
	private JTextField yearFielld;
	private JTextField semesterField;
	private JTextField returnDateField;
	private JTextField returningDateField;
	
	Connection conn ;
	ResultSet rs;
	PreparedStatement pst;
	int quantity;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook frame = new ReturnBook();
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
	public ReturnBook() {
		
		super("ReturnBook");
		conn = SqlConnection.databaseConnector();		//getting connection to the database
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK RETURN PORTAL");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(205, 11, 321, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student ID :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(180, 67, 92, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Book ID :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(180, 102, 92, 24);
		contentPane.add(lblNewLabel_2);
		
		studentIdField = new JTextField();
		studentIdField.setBounds(273, 70, 142, 20);
		contentPane.add(studentIdField);
		studentIdField.setColumns(10);
		
		bookIdField = new JTextField();
		bookIdField.setBounds(273, 105, 142, 20);
		contentPane.add(bookIdField);
		bookIdField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Enter Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(155, 50, 392, 85);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton DetailsSearchButton = new JButton("Search");
		DetailsSearchButton.setBounds(282, 34, 89, 23);
		panel.add(DetailsSearchButton);
		DetailsSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//String query = "select * from student s, book b, issuebook ib where s.student_id = ib.student_id && b.book_id = ib.student_id && ib.student_id = ? && s.book_id = '"+textField_1.getText()+"'";
					//String query = " select * from student as s, book as b, issuebook as ib where s.student_id = ib.student_id && b.book_id = ib.student_id && ib.student_id = '"+textField.getText()+"' && ib.book_id = '"+textField_1.getText()+"'";
					//Statement st = conn.createStatement();
					//boolean h = st.execute(query);
					//System.out.println(Boolean.toString(h));
					
					//String query = "select * from ((student inner join issuebook on student.student_id issuebook.student_id) inner join book on issuebook.book_id = book.book_id)where student.student_id = ? and book.book_id = ?";
					//String query = "select * from ((issuebook inner join student on issuebook.student_id = student.student_id)inner join book on issuebook.book_id = book.book_id)where student.student_id = ? and book.book_id = ?";
					
					String query = "select * from issuebook inner join student on issuebook.student_id = student.student_id inner join book on issuebook.book_id = book.book_id where student.student_id = '"+studentIdField.getText()+"' and book.book_id = '"+bookIdField.getText()+"'";
																					//query to join student issueook and book table to retrive all the data for the given student_id and book_id
					Statement st = conn.createStatement();				
					st.execute(query);
					
				/*	pst = conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.execute(); */
					
					rs = st.executeQuery(query);
					
					if(rs.next()) {							//If condition to to set the textField with the  data obtaied from the previous querry
						//JOptionPane.showMessageDialog(null, "hello");
						bookNameField.setText(rs.getString(14));
						publisherField.setText(rs.getString(16));
						priceField.setText(rs.getString(17));
						pagesField.setText(rs.getString(18));
						editionField.setText(rs.getString(15));
						studentNameField.setText(rs.getString(6));
						fatherNameField.setText(rs.getString(7));
						courseField.setText(rs.getString(8));
						branchField.setText(rs.getString(9));
						yearFielld.setText(rs.getString(10));
						semesterField.setText(rs.getString(11));
						returnDateField.setText(rs.getString(4));
						
						JOptionPane.showMessageDialog(null, "Data Retrieved Successfull");
					}else {				//If  no data is retrieved from the above quuery this else condition gets executed
						JOptionPane.showMessageDialog(null, "Wrong Credentials");
					}
					
					
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				
			}
		});
		DetailsSearchButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JLabel lblBookName = new JLabel("Book Name :");
		lblBookName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblBookName.setBounds(58, 178, 92, 24);
		contentPane.add(lblBookName);
		
		JLabel label_1 = new JLabel("Edition :");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_1.setBounds(58, 213, 77, 24);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Publisher :");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_2.setBounds(58, 248, 77, 24);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Price :");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_3.setBounds(58, 283, 77, 24);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Pages :");
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_4.setBounds(58, 317, 63, 24);
		contentPane.add(label_4);
		
		bookNameField = new JTextField();
		bookNameField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		bookNameField.setColumns(10);
		bookNameField.setBounds(155, 178, 134, 20);
		contentPane.add(bookNameField);
		
		publisherField = new JTextField();
		publisherField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		publisherField.setColumns(10);
		publisherField.setBounds(155, 248, 134, 20);
		contentPane.add(publisherField);
		
		priceField = new JTextField();
		priceField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		priceField.setColumns(10);
		priceField.setBounds(155, 283, 134, 20);
		contentPane.add(priceField);
		
		pagesField = new JTextField();
		pagesField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		pagesField.setColumns(10);
		pagesField.setBounds(155, 317, 134, 20);
		contentPane.add(pagesField);
		
		editionField = new JTextField();
		editionField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		editionField.setColumns(10);
		editionField.setBounds(153, 209, 134, 20);
		contentPane.add(editionField);
		
		JLabel lblStudentName = new JLabel("Student Name :");
		lblStudentName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblStudentName.setBounds(374, 181, 107, 14);
		contentPane.add(lblStudentName);
		
		JLabel label_6 = new JLabel("Father Name :");
		label_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_6.setBounds(374, 216, 97, 14);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("Course :");
		label_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_7.setBounds(374, 251, 65, 14);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("Branch :");
		label_8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_8.setBounds(374, 286, 65, 14);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("Year :");
		label_9.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_9.setBounds(374, 320, 48, 14);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("Semester :");
		label_10.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_10.setBounds(374, 347, 65, 24);
		contentPane.add(label_10);
		
		studentNameField = new JTextField();
		studentNameField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		studentNameField.setColumns(10);
		studentNameField.setBounds(491, 178, 142, 20);
		contentPane.add(studentNameField);
		
		fatherNameField = new JTextField();
		fatherNameField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		fatherNameField.setColumns(10);
		fatherNameField.setBounds(491, 213, 142, 20);
		contentPane.add(fatherNameField);
		
		courseField = new JTextField();
		courseField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		courseField.setColumns(10);
		courseField.setBounds(491, 251, 142, 20);
		contentPane.add(courseField);
		
		branchField = new JTextField();
		branchField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		branchField.setColumns(10);
		branchField.setBounds(491, 284, 142, 20);
		contentPane.add(branchField);
		
		yearFielld = new JTextField();
		yearFielld.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		yearFielld.setColumns(10);
		yearFielld.setBounds(491, 317, 142, 20);
		contentPane.add(yearFielld);
		
		semesterField = new JTextField();
		semesterField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		semesterField.setColumns(10);
		semesterField.setBounds(491, 350, 142, 20);
		contentPane.add(semesterField);
		
		JLabel lblNewLabel_3 = new JLabel("Return Date  :");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(55, 353, 95, 14);
		contentPane.add(lblNewLabel_3);
		
		returnDateField = new JTextField();
		returnDateField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		returnDateField.setBounds(155, 348, 133, 20);
		contentPane.add(returnDateField);
		returnDateField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(27, 157, 631, 232);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Returning Date :");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(189, 408, 122, 24);
		contentPane.add(lblNewLabel_4);
		
		returningDateField = new JTextField();
		returningDateField.setBounds(321, 411, 133, 20);
		contentPane.add(returningDateField);
		returningDateField.setColumns(10);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");		//statement to set the  dateFromat
		Date date = new Date();
		returningDateField.setText(dateFormat.format(date));	
		
		JButton returnButton = new JButton("Return");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "insert into returnBook (student_id,book_id,returningDate) values(?,?,?)";		//query to insert the book_id student_id and returning date into the database
					pst = conn.prepareStatement(query);				//Creates a PreparedStatement object for sendingparameterized SQL statements to the database. 
					pst.setString(1, studentIdField.getText());
					pst.setString(2, bookIdField.getText());
					pst.setString(3, returningDateField.getText());
					pst.executeUpdate();
					pst.close();
					
					
					String bookQuantityQuery = "select quantity from book where book_id = '"+bookIdField.getText()+"'";				//query to retrieve the quantity of the given book_id
					Statement st = conn.createStatement();
					st.execute(bookQuantityQuery);
					
					rs = st.executeQuery(bookQuantityQuery);
					if(rs.next()) {			//condtion to obtain the quantity and store it in the qunatity variable
						quantity = Integer.parseInt(rs.getString(1));
						quantity++;			//incrementinf the quantity value when book are returned 
						//String quantityUpdateQuery = "select quantity from book where book_id = '"+textField.getText()+"' where update book set quantity = '"+Integer.toString(quantity)+"'";
						String quantityUpdateQuery = "update book set quantity = '"+Integer.toString(quantity)+"' where book_id = '"+bookIdField.getText()+"'";
																		//query to update the incremented quantity into the book table
						pst = conn.prepareStatement(quantityUpdateQuery);		//query to execute the quantityUpdateQuey ,it sends the string in the form of a parameterized object and sen it to the database
						pst.executeUpdate();
						pst.close();
					}
					rs.close();			//it closes the result condition
					
					String query1 = "delete from issuebook where student_id = '"+studentIdField.getText()+"' && book_id = '"+bookIdField.getText()+"'";
															//query to delete the book_id and student_id from the issueTable when the book is returned by the student
					pst = conn.prepareStatement(query1);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Successful");			//itdisplays a pop up window when the returning of the  book is successful
					studentIdField.setText(" ");
					bookIdField.setText(" ");
					bookNameField.setText(" ");
					publisherField.setText(" ");
					priceField.setText(" ");
					pagesField.setText(" ");
					editionField.setText(" ");
					studentNameField.setText(" ");
					fatherNameField.setText(" ");
					courseField.setText(" ");
					branchField.setText(" ");
					yearFielld.setText(" ");
					semesterField.setText(" ");
					returnDateField.setText(" ");
					
					
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				
				
			}
		});
		returnButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		returnButton.setBounds(218, 451, 112, 23);
		contentPane.add(returnButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				Welcome w = new Welcome();
				w.setVisible(true);
			}
		});
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		backButton.setBounds(374, 451, 97, 24);
		contentPane.add(backButton);
	}
}
