import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class NewBook extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn ;
	ResultSet rs;
	PreparedStatement pst;
	PreparedStatement pst1;
	private JPanel contentPane;
	private JTextField book_id;
	private JTextField bookName;
	private JTextField publisher;
	private JTextField price;
	private JTextField pages;
	private JTextField librarianName;
	private JTextField quantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewBook frame = new NewBook();
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
	public NewBook() {
		super("NewBook");
		conn = SqlConnection.databaseConnector(); //getting connection to the database
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NEW BOOK ENTRY PORTAL");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(51, 11, 373, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Book ID :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(34, 68, 66, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Book Name :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(34, 102, 85, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Edition :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(34, 127, 66, 23);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Publisher :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(34, 161, 66, 23);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Price :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(34, 195, 66, 23);
		contentPane.add(lblNewLabel_5);
		
		JComboBox edition = new JComboBox();
		edition.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		edition.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"}));
		edition.setBounds(151, 129, 202, 22);
		contentPane.add(edition);
		
		JLabel lblNewLabel_6 = new JLabel("Pages :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(34, 229, 66, 23);
		contentPane.add(lblNewLabel_6);
		
		book_id = new JTextField();
		book_id.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		book_id.setEnabled(false);
		book_id.setBounds(151, 71, 202, 20);
		contentPane.add(book_id);
		book_id.setColumns(10);
		
		bookName = new JTextField();
		bookName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bookName.setBounds(149, 101, 204, 20);
		contentPane.add(bookName);
		bookName.setColumns(10);
		
		publisher = new JTextField();
		publisher.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		publisher.setBounds(149, 164, 204, 20);
		contentPane.add(publisher);
		publisher.setColumns(10);
		
		price = new JTextField();
		price.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		price.setBounds(149, 198, 204, 20);
		contentPane.add(price);
		price.setColumns(10);
		
		pages = new JTextField();
		pages.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		pages.setBounds(151, 232, 202, 20);
		contentPane.add(pages);
		pages.setColumns(10);
		
		Random();			// calling the random function
		
		JButton Add = new JButton("Add");
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "insert into book (book_id,book_name,edition,publisher,price,pages,librarianName,quantity) values(?,?,?,?,?,?,?,?)";		
																						//query to insert new book details  into the database
					pst = conn.prepareStatement(query);									//creating a preparedStatement object for sending parameterized sql statement to the database
					pst.setString(1, book_id.getText());						//setting data to the desired attribute
					pst.setString(2, bookName.getText());
					pst.setString(3,(String)edition.getSelectedItem());
					pst.setString(4, publisher.getText());
					pst.setString(5, price.getText());
					pst.setString(6, pages.getText());
					pst.setString(7, librarianName.getText());
					pst.setString(8, quantity.getText());
					pst.executeUpdate();											//is used wen we try to manipulate(insert , update , delete ) the data in the database
					
					String query1 = "select * from book where book_id='"+book_id.getText()+"'";		//query to invoke a error wen no input is given but add button is clicked
					pst1 = conn.prepareStatement(query1);
					rs=pst1.executeQuery();				//returns the resultSet object generated by the query
					int flag = 0;
					while(rs.next()) {			//moves the cursor forward one row from its current position in the output table
						flag++;
					}
					if(flag >= 1) {
						Random();				//cALLING RANDOM FUNCTION
					}
					JOptionPane.showMessageDialog(null, " successful ");
					setVisible(false);
					Welcome wl = new Welcome();
					wl.setVisible(true);
					
					
				}catch(SQLIntegrityConstraintViolationException e2) {
					//JOptionPane.showMessageDialog(null, e2);
					Random();
					JOptionPane.showMessageDialog(null, "Book id is been reseted due to duplicate id , now u can click on ADD button to insert this book details into the database");
					
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		Add.setFont(new Font("Tahoma", Font.BOLD, 13));
		Add.setBounds(101, 335, 131, 30);
		contentPane.add(Add);
		
		JLabel lblNewLabel_7 = new JLabel("Librarian Name :");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7.setBounds(34, 258, 107, 30);
		contentPane.add(lblNewLabel_7);
		
		librarianName = new JTextField();
		librarianName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		librarianName.setBounds(151, 266, 202, 20);
		contentPane.add(librarianName);
		librarianName.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblQuantity.setBounds(34, 291, 107, 30);
		contentPane.add(lblQuantity);
		
		quantity = new JTextField();
		quantity.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		quantity.setColumns(10);
		quantity.setBounds(151, 299, 202, 20);
		contentPane.add(quantity);
		
		JButton Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);					//disabling the newBook page wen back button is clicked
				Welcome w = new Welcome();
				w.setVisible(true);					//redirecting to the welcome page
			}
		});
		Back.setFont(new Font("Tahoma", Font.BOLD, 13));
		Back.setBounds(256, 335, 131, 30);
		contentPane.add(Back);
		
		
		
	}
	
	public void Random() {
		java.util.Random rd = new java.util.Random();			//creating a random object
		book_id.setText(""+rd.nextInt(1000+1));
		
	}
}
