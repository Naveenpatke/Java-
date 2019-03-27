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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class IssueBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField book_id;
	private JTextField bookName;
	private JTextField publisher;
	private JTextField price;
	private JTextField pages;
	private JTextField edition;
	private JTextField student_id;
	private JTextField studentName;
	private JTextField fatherName;
	private JTextField course;
	private JTextField branch;
	private JTextField year;
	private JTextField semester;
	
	Connection conn ;
	ResultSet rs;
	PreparedStatement pst;
	PreparedStatement pst1;
	private JTextField issuedDate;
	private JTextField returnDate;
	int quantity;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBook frame = new IssueBook();
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
	public IssueBook() {
		
		super("IssueBook");
		conn = SqlConnection.databaseConnector();		//getting connection
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book ID :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(51, 76, 77, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(51, 111, 77, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Edition :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(51, 146, 77, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Publisher :");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(51, 181, 77, 24);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Price :");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(51, 216, 77, 24);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Pages :");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_5.setBounds(51, 250, 63, 24);
		contentPane.add(lblNewLabel_5);
		
		book_id = new JTextField();
		book_id.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		book_id.setBounds(136, 79, 134, 20);
		contentPane.add(book_id);
		book_id.setColumns(10);
		
		bookName = new JTextField();
		bookName.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		bookName.setBounds(136, 114, 134, 20);
		contentPane.add(bookName);
		bookName.setColumns(10);
		
		publisher = new JTextField();
		publisher.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		publisher.setBounds(136, 184, 134, 20);
		contentPane.add(publisher);
		publisher.setColumns(10);
		
		price = new JTextField();
		price.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		price.setBounds(136, 219, 134, 20);
		contentPane.add(price);
		price.setColumns(10);
		
		pages = new JTextField();
		pages.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		pages.setBounds(136, 253, 134, 20);
		contentPane.add(pages);
		pages.setColumns(10);
		
		edition = new JTextField();
		edition.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		edition.setBounds(136, 149, 134, 20);
		contentPane.add(edition);
		edition.setColumns(10);
		
		JButton bookSearch = new JButton("Search");
		bookSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "select * from book where book_id = ?";		//query to search books details
					pst = conn.prepareStatement(query);							//Creates a PreparedStatement object for sendingparameterized SQL statements to the database. 
					pst.setString(1, book_id.getText());
					rs = pst.executeQuery();				//Executes the SQL query in this PreparedStatement object and returns the ResultSet object generated by the query.
					if(rs.next()) {
						
						bookName.setText(rs.getString(2));		//setting the textFeild with the data retrieved from the above query
						edition.setText(rs.getString(3));
						publisher.setText(rs.getString(4));
						price.setText(rs.getString(5));
						pages.setText(rs.getString(6));
						rs.close();
						pst.close();
						
					}else {
						JOptionPane.showMessageDialog(null, "Invalid Book ID");		//this part is executed when invalid book_id is executed
						
						book_id.setText("  ");				//statement to reset the  textfield
						bookName.setText(" ");
						edition.setText(" ");
						publisher.setText(" ");
						price.setText(" ");
						pages.setText(" ");
					}

					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		bookSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bookSearch.setBounds(288, 78, 89, 23);
		contentPane.add(bookSearch);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Book Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(24, 55, 377, 271);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("BOOK ISSUE PORTAL");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_6.setBounds(254, 11, 275, 33);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Student ID :");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7.setBounds(464, 82, 77, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Name :");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_8.setBounds(464, 117, 48, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Father Name :");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_9.setBounds(464, 152, 97, 14);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Course :");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_10.setBounds(464, 187, 65, 14);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Branch :");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_11.setBounds(464, 222, 65, 14);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Year :");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_12.setBounds(464, 256, 48, 14);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Semester :");
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_13.setBounds(464, 283, 65, 24);
		contentPane.add(lblNewLabel_13);
		
		student_id = new JTextField();
		student_id.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		student_id.setBounds(564, 80, 142, 20);
		contentPane.add(student_id);
		student_id.setColumns(10);
		
		studentName = new JTextField();
		studentName.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		studentName.setBounds(564, 114, 142, 20);
		contentPane.add(studentName);
		studentName.setColumns(10);
		
		fatherName = new JTextField();
		fatherName.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		fatherName.setBounds(564, 149, 142, 20);
		contentPane.add(fatherName);
		fatherName.setColumns(10);
		
		course = new JTextField();
		course.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		course.setBounds(564, 187, 142, 20);
		contentPane.add(course);
		course.setColumns(10);
		
		branch = new JTextField();
		branch.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		branch.setBounds(564, 220, 142, 20);
		contentPane.add(branch);
		branch.setColumns(10);
		
		year = new JTextField();
		year.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		year.setBounds(564, 253, 142, 20);
		contentPane.add(year);
		year.setColumns(10);
		
		semester = new JTextField();
		semester.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		semester.setBounds(564, 286, 142, 20);
		contentPane.add(semester);
		semester.setColumns(10);
		
		JButton studentSearch = new JButton("Search");
		studentSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query1 = "select * from student where student_id = ?";		//query to retrieve student data from the given student_id
					pst = conn.prepareStatement(query1);						//Creates a PreparedStatement object for sendingparameterized SQL statement to the database
					pst.setString(1, student_id.getText());
					rs = pst.executeQuery();
					if(rs.next()) {
						
						studentName.setText(rs.getString(2));				//setting the textFeild with the data retrieved from the above query
						fatherName.setText(rs.getString(3));
						course.setText(rs.getString(4));
						branch.setText(rs.getString(5));
						year.setText(rs.getString(6));
						semester.setText(rs.getString(7));
						rs.close();
						pst.close();
					}else {
						JOptionPane.showMessageDialog(null, "Invalid student ID ");		//this part is executed wen invalid student_id is enterred
						student_id.setText(" ");
						
					}
					
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				
			}
		});
		studentSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		studentSearch.setBounds(716, 78, 102, 23);
		contentPane.add(studentSearch);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(422, 55, -1, 2);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(422, 55, -1, 2);
		contentPane.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Student Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(428, 55, 408, 271);
		contentPane.add(panel_3);
		
		JLabel lblNewLabel_14 = new JLabel("Issued  Date :");
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_14.setBounds(172, 352, 97, 24);
		contentPane.add(lblNewLabel_14);
		
		issuedDate = new JTextField();
		issuedDate.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		issuedDate.setBounds(264, 355, 89, 20);
		contentPane.add(issuedDate);
		issuedDate.setColumns(10);
		
		JButton issueBook = new JButton("Issue Book");
		issueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String queryToInsertData = "insert into issueBook (student_id,book_id,issueDate,returnDate) values(?,?,?,?)";
																			//the  above query is used to insert data obtained from textField into the returnBook table
					pst = conn.prepareStatement(queryToInsertData);
					pst.setString(1, student_id.getText());
					pst.setString(2, book_id.getText());
					pst.setString(3, issuedDate.getText());
					pst.setString(4, returnDate.getText());
					pst.executeUpdate();		//Executes the SQL statement in this PreparedStatement object,which must be an SQL Data Manipulation Language statement
					
					pst.close();
					
					String bookQuantityQuery = "select quantity from book where book_id = '"+book_id.getText()+"'";
																//query to obtain the quantity of a particular book
					Statement st = conn.createStatement();		//Creates a Statement object for sendingSQL statements to the database.SQL statements without parameters are normallyexecuted using Statement objects
					st.execute(bookQuantityQuery);				//Executes the given SQL statement, which may return multiple results
					
					rs = st.executeQuery(bookQuantityQuery);	//Executes the given SQL statement, which returns a single ResultSet object. 
					if(rs.next()) {
						quantity = Integer.parseInt(rs.getString(1));		//to store the quantity data obtain from above query into the quantity variable
						quantity--;				//wen ever a bookis issued the quantity of the  book is decreased
						//String quantityUpdateQuery = "select quantity from book where book_id = '"+textField.getText()+"' where update book set quantity = '"+Integer.toString(quantity)+"'";
						String quantityUpdateQuery = "update book set quantity = '"+Integer.toString(quantity)+"' where book_id = '"+book_id.getText()+"'";
						pst = conn.prepareStatement(quantityUpdateQuery);
						pst.executeUpdate();
						pst.close();
					}
					rs.close();
					//String query1 = "update book set quantity = "
					JOptionPane.showMessageDialog(null, "book issued successfully");
					book_id.setText("  ");
					bookName.setText("  ");
					publisher.setText("  ");
					price.setText("  ");
					pages.setText("  ");
					edition.setText("  ");
					student_id.setText("  ");
					studentName.setText("  ");
					fatherName.setText("  ");
					course.setText("  ");
					branch.setText("  ");
					year.setText("  ");
					semester.setText("  ");
					
					
					
				}catch(Exception e3) {
					JOptionPane.showMessageDialog(null, e3);
				}
				
			}
		});
		issueBook.setFont(new Font("Times New Roman", Font.BOLD, 14));
		issueBook.setBounds(248, 406, 153, 23);
		contentPane.add(issueBook);
		

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");		//statement to set the  dateFromat
		Date date = new Date();
		issuedDate.setText(dateFormat.format(date));		// current date from retrieved from the pc is been show in the issueDate textField
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);				//disabling issueBook page when back button is clicked
				Welcome w = new Welcome();
				w.setVisible(true);			//redirecting to the welcome page
			}
		});
		back.setFont(new Font("Times New Roman", Font.BOLD, 14));
		back.setBounds(452, 406, 147, 23);
		contentPane.add(back);
		
		JLabel lblNewLabel_15 = new JLabel("Return Date :");
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_15.setBounds(422, 355, 90, 18);
		contentPane.add(lblNewLabel_15);
		
		returnDate = new JTextField();
		returnDate.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		returnDate.setBounds(522, 355, 102, 20);
		contentPane.add(returnDate);
		returnDate.setColumns(10);
	}
}
