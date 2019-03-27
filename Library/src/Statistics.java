import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Statistics extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField student_id;
	private JTable table;
	PreparedStatement pst;
	Connection conn;
	ResultSet rs;
	private JTextField book_id;
	private JTextField avalability;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistics frame = new Statistics();
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
	public Statistics() {
		super("Statistics");
		conn = SqlConnection.databaseConnector();  //getting connection to the database
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLibrarianName = new JLabel("Student ID :");
		lblLibrarianName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblLibrarianName.setBounds(23, 32, 107, 24);
		contentPane.add(lblLibrarianName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 67, 767, 211);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{},
				{},
				{},
				{},
			},
			new String[] {
			}
		));
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		JPanel panel = new JPanel();
		panel.setBounds(771, 169, -611, -113);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Books Collected By Particular Student", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 816, 291);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		student_id = new JTextField();
		student_id.setBounds(100, 24, 136, 20);
		panel_1.add(student_id);
		student_id.setFont(new Font("Times New Roman", Font.BOLD, 14));
		student_id.setColumns(10);
		
		JButton studentIdSearch = new JButton("Search");
		studentIdSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String studentExistanceChecking = "select * from student where student_id = '"+student_id.getText()+"'";
																				//query to retrieve all the details of the particular student_id provided by the  user
					Statement st = conn.createStatement();
					st.execute(studentExistanceChecking);
					ResultSet rs1 = st.executeQuery(studentExistanceChecking);
					if(rs1.next()) {		//if we obtain any data for the given student_id thenn if condition will be executed
							
							try {
									//String query = "select * from issuebook inner join student on issuebook.student_id = student.student_id inner join book on issuebook.book_id = book.book_id where student.student_id = '"+textField.getText()+"' and book.book_id = '"+textField_1.getText()+"'";

									String booksAssignedToAParticularStudent = " select s.name StudentName, b.book_id BookID,b.book_name BookName, s.course Course, s.branch Branch, s.year Year,"+
																				" s.semester Semester, s.librarianName LibrarianName, ib.issueDate IssueDate from  issuebook ib inner join student s"+
																				" on ib.student_id = s.student_id inner join book b on ib.book_id = b.book_id where s.student_id = '"+student_id.getText()+"'";	
															//query to retrieve details of the given stuudent_id,the details include the book issued to this student (along with the  bookName)and the book issued details and the librariab who issued the  book to the given student_id
									Statement st1 = conn.createStatement();					//Creates a Statement object for sendingSQL statements to the database
									st1.execute(booksAssignedToAParticularStudent);
									rs = st1.executeQuery(booksAssignedToAParticularStudent);
									//table.setModel(DbUtils.resultSetToTableModel(rs));
					
									table.setModel(DbUtils.resultSetToTableModel(rs));			//code to set the table,here the data which is stored in the resultSet is been set into the table
																								//You require rs2xml jar file to implement the  table ,which can display the data in resultSet
									table.setEnabled(false);
					
								}catch(Exception e1) {
										JOptionPane.showMessageDialog(null, e1);
								}
					}else {							//else condition is  executed when the given student_id is incorrect or the student details is not present in the database
						DefaultTableModel model = (DefaultTableModel) table.getModel();     //this code is used to reset the table
						model.setRowCount(0);												 //this code is used to reset the table
						JOptionPane.showMessageDialog(null, "Student doesn't exist");
						
					}
				}catch(Exception e3) {
					JOptionPane.showMessageDialog(null, e3);
				}
			}
		});
		studentIdSearch.setBounds(246, 23, 89, 23);
		panel_1.add(studentIdSearch);
		studentIdSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JLabel label = new JLabel("Book ID :");
		label.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label.setBounds(229, 334, 83, 24);
		contentPane.add(label);
		
		book_id = new JTextField();
		book_id.setFont(new Font("Times New Roman", Font.BOLD, 14));
		book_id.setColumns(10);
		book_id.setBounds(325, 336, 136, 20);
		contentPane.add(book_id);
		
		JButton bookIdSearch = new JButton("Search");
		bookIdSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String booksAvalability = "select quantity from book where book_id = '"+book_id.getText()+"'";
																		//query to retrieve the number of books fro the given book_id available in the database 
					Statement st = conn.createStatement();			//Creates a Statement object for sendingSQL statements to the database.
					st.execute(booksAvalability);
					rs = st.executeQuery(booksAvalability);
					if(rs.next()) {			//if any data is retrieved from the the  above query then this "if" conditon getst executed
						avalability.setText(rs.getString(1));
					}else {					//when no data is retiered after execution of the above query or given book_id is not present in the database then this else condition gets executed
						JOptionPane.showMessageDialog(null, "Wrong Credentials");
					}
					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		bookIdSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bookIdSearch.setBounds(484, 335, 89, 23);
		contentPane.add(bookIdSearch);
		
		JLabel label_1 = new JLabel("Avalability :");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_1.setBounds(227, 369, 107, 24);
		contentPane.add(label_1);
		
		avalability = new JTextField();
		avalability.setFont(new Font("Times New Roman", Font.BOLD, 14));
		avalability.setColumns(10);
		avalability.setBounds(325, 371, 47, 20);
		contentPane.add(avalability);
		
		JLabel label_2 = new JLabel("books are available in library.");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_2.setBounds(382, 369, 196, 24);
		contentPane.add(label_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(615, 414, -394, -91);
		contentPane.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Books Avalability Checking Portal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(216, 313, 376, 124);
		contentPane.add(panel_3);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_id.setText(" ");	 		//it clears the book_id Field
				avalability.setText(" ");		//it clears the avalabilityField
			}
		});
		clear.setFont(new Font("Times New Roman", Font.BOLD, 14));
		clear.setBounds(151, 90, 89, 23);
		panel_3.add(clear);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);			//it disables the Statistics page
				Welcome w = new Welcome();
				w.setVisible(true);			//it redirects to the welcome page
			}
		});
		back.setFont(new Font("Times New Roman", Font.BOLD, 14));
		back.setBounds(372, 450, 89, 23);
		contentPane.add(back);
	}
}
