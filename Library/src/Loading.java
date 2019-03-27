import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("serial")
public class Loading extends JFrame implements Runnable{

	private JPanel contentPane;
	Connection conn ;
	int s=0;
	Thread th;
	ResultSet rs;
	PreparedStatement pst;
	JProgressBar progressBar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loading frame = new Loading();
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
	public Loading() {
		super("Loading");
		th = new Thread((Runnable)this);			// a new child Thread is created
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UVCE LIBRARY...");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(108, 32, 254, 34);
		contentPane.add(lblNewLabel);
		
		 progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(99, 110, 219, 34);
		contentPane.add(progressBar);
		
		JLabel lblNewLabel_1 = new JLabel("Please Wait...");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(179, 188, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Setting up dashboard...");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2.setBounds(139, 155, 161, 22);
		contentPane.add(lblNewLabel_2);
	}
	public void uploading() {
		setVisible(false);		
		th.start();			//it starts the execution of the child  thread
	}
	public void run() {
		try {
			for(int i=0; i<200; i++) {
				int p = progressBar.getMaximum();		//obtaining the MaxValue of the progressBar
				int m = progressBar.getValue();			//retrieving the current progressBar value
				if(m<p) {
					progressBar.setValue(progressBar.getValue()+1);
				}else {
					i=201;
					setVisible(false);				//disabling the Loading page 
					Welcome home = new Welcome();
					home.setVisible(true);			//redirecting to the Welcome page(dashBoard)
				}Thread.sleep(25);
				
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
