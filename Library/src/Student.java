import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Student extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection conn;
	ResultSet rs;
	PreparedStatement pst;
	PreparedStatement pst1;
	private JTextField student_id;
	private JTextField studentName;
	private JTextField fatherName;
	private JTextField branch;
	private JTextField librarianName;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Student() {
		super("Student");
		conn = SqlConnection.databaseConnector(); //getting connection to the database
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Student Entry Portal");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(78, 11, 295, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student ID :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(28, 68, 95, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(28, 101, 73, 22);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Father Name :");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(28, 134, 95, 22);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Course :");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(28, 167, 73, 22);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Branch :");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_5.setBounds(28, 200, 73, 22);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Year :");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_6.setBounds(28, 233, 48, 22);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Semester :");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7.setBounds(28, 266, 73, 22);
		contentPane.add(lblNewLabel_7);
		
		student_id = new JTextField();
		student_id.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		student_id.setEnabled(false);
		student_id.setBounds(143, 70, 198, 20);
		contentPane.add(student_id);
		student_id.setColumns(10);
		
		studentName = new JTextField();
		studentName.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		studentName.setBounds(143, 103, 198, 20);
		contentPane.add(studentName);
		studentName.setColumns(10);
		
		fatherName = new JTextField();
		fatherName.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		fatherName.setBounds(143, 136, 198, 20);
		contentPane.add(fatherName);
		fatherName.setColumns(10);
		
		JComboBox course = new JComboBox();
		course.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		course.setModel(new DefaultComboBoxModel(new String[] {"B.TECH", "BSC", "M.TECH", "MCA", "MSC"}));
		course.setBounds(143, 168, 198, 22);
		contentPane.add(course);
		
		JComboBox year = new JComboBox();
		year.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		year.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		year.setBounds(143, 234, 198, 22);
		contentPane.add(year);
		
		JComboBox semester = new JComboBox();
		semester.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		semester.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		semester.setBounds(143, 267, 198, 22);
		contentPane.add(semester);
		
		branch = new JTextField();
		branch.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		branch.setBounds(143, 203, 198, 20);
		contentPane.add(branch);
		branch.setColumns(10);
		
		JButton create = new JButton("Create");
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String studentDetailsInsertionIntoTable = "insert into student (student_id,name,father_name,course,branch,year,semester,librarianName) values(?,?,?,?,?,?,?,?)";
				try {														//query to insert new students details into the database
					
					pst = conn.prepareStatement(studentDetailsInsertionIntoTable);			//creating a preparedStatement object for sending parameterized sql query to the database
					pst.setString(1, student_id.getText());
					pst.setString(2, studentName.getText());
					pst.setString(3, fatherName.getText());
					pst.setString(4, (String)course.getSelectedItem());
					pst.setString(5, branch.getText());
					pst.setString(6, (String)year.getSelectedItem());
					pst.setString(7, (String)semester.getSelectedItem());
					pst.setString(8, librarianName.getText());
					pst.executeUpdate();
					
					String checkingForDuplicateId = "select * from student where student_id='"+student_id.getText()+"'";		//query to invoke a error wen no input is given but add button is clicked
					pst1 = conn.prepareStatement(checkingForDuplicateId);
					rs=pst1.executeQuery();					//Executes the SQL query in this PreparedStatement objectand returns the ResultSet object generated by the query
					int flag = 0;
					while(rs.next()) {
						flag++;
					}
					if(flag >= 1) {
						Random();
					}
					JOptionPane.showMessageDialog(null, "Details Insertion was successful");
					setVisible(false);
					Welcome wl = new Welcome();
					wl.setVisible(true);
					
					
				}catch(SQLIntegrityConstraintViolationException e2) {
					//JOptionPane.showMessageDialog(null, e2);
					Random();			//calling random function
					JOptionPane.showMessageDialog(null, "Book id is been reseted due to duplicate id , now u can click on ADD button to insert this book details into the database");
					
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
					
				
				
			}
		});
		create.setFont(new Font("Times New Roman", Font.BOLD, 14));
		create.setBounds(123, 334, 110, 28);
		contentPane.add(create);
		
		JLabel lblLibrarianName = new JLabel("Librarian Name :");
		lblLibrarianName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblLibrarianName.setBounds(28, 301, 110, 22);
		contentPane.add(lblLibrarianName);
		
		librarianName = new JTextField();
		librarianName.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		librarianName.setBounds(143, 303, 198, 20);
		contentPane.add(librarianName);
		librarianName.setColumns(10);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Welcome w = new Welcome();
				w.setVisible(true);
			}
		});
		back.setFont(new Font("Times New Roman", Font.BOLD, 14));
		back.setBounds(263, 334, 110, 28);
		contentPane.add(back);
		
		Random();    //calling random function to generate a random book_id when Student page opens
	}
	
	public void Random() {
		java.util.Random rd = new java.util.Random();
		student_id.setText(""+rd.nextInt(1000+1));
		
	}
}
