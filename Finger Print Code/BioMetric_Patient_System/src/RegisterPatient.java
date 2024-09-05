import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.border.*;
import javax.swing.event.*;
import java.applet.*;

public class RegisterPatient extends JFrame implements ActionListener,Runnable
{
	private JPanel jpInfo = new JPanel();
	private JLabel lbNo, lbName, lbDate, lbWordName,lbMobNo,lbWordNo,lbCrimeAdd;
	private JTextField txtNo, txtName, txtMobNo,txtAdd,txtPath;//txtWordNo,txtCrimeAdd;
	private JComboBox cboMonth, cboDay, cboYear;
	private JButton btnSave, btnCancel,btnBrowse;
	private JButton btnStartCam;
	private JFileChooser  sourcefile= new JFileChooser("./");
	private int count = 0;
	private int rows = 0;
	private	int total = 0;

	private String records[][] = new String [500][8];

	private String saves[][] = new String [500][8];

	private String spath;
	
	private FileInputStream fis;
	private DataInputStream dis;
	boolean running=false,detectFace=false,save=false;
	RegisterPatient(int i){}
	public void run(){}
	RegisterPatient ()
	{
		super ("Patient Information System");
		setSize (574, 340);
		setResizable(false);
		setLocationRelativeTo(null);
		jpInfo.setBounds (0, 0, 500, 115);
		jpInfo.setLayout (null);

		lbNo = new JLabel ("Adhar_No:");
		lbNo.setForeground (Color.black);
		lbNo.setBounds (265, 20, 90, 25);

        lbName = new JLabel ("Patient Name:");
		lbName.setForeground (Color.black);
        lbName.setBounds (265, 60, 90, 25);

		lbDate = new JLabel ("Date of Birth:");
		lbDate.setForeground (Color.black);
		lbDate.setBounds (265, 100, 90, 25);

		lbMobNo = new JLabel ("Mobile No: ");
		lbMobNo.setForeground (Color.black);
		lbMobNo.setBounds (265, 140, 90, 25);

		lbWordNo = new JLabel ("Address");
		lbWordNo.setForeground (Color.black);
		lbWordNo.setBounds (265, 180, 90, 25);

		lbWordName = new JLabel ("Medical File");
		lbWordName.setForeground (Color.black);
		lbWordName.setBounds (265, 220, 90, 25);

		txtNo = new JTextField ();
		txtNo.setHorizontalAlignment (JTextField.RIGHT);
		txtNo.setBounds (355, 20, 205, 25);

		txtName = new JTextField ();
		txtName.setHorizontalAlignment (JTextField.RIGHT);
		txtName.setBounds (355, 60, 205, 25);

		txtMobNo = new JTextField ();
		txtMobNo.setHorizontalAlignment (JTextField.RIGHT);
		txtMobNo.setBounds (355, 140, 205, 25);

		txtAdd = new JTextField ();
		txtAdd.setHorizontalAlignment (JTextField.RIGHT);
		txtAdd.setBounds (355, 180, 205, 25);
		
		txtPath = new JTextField ();
		txtPath.setEnabled (false);
		txtPath.setHorizontalAlignment (JTextField.RIGHT);
		txtPath.setBounds (355, 220, 105, 25);

		btnBrowse = new JButton ("Browse");
		btnBrowse.setBounds (470, 220, 90, 25);
		btnBrowse.addActionListener (this);

		
		txtNo.addKeyListener (new KeyAdapter()
		{
			public void keyTyped (KeyEvent ke)
			{
				char c = ke.getKeyChar ();
				if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE))))
				{
					getToolkit().beep ();
					ke.consume ();
      			}
    		}
  		}
		);

		txtName.addKeyListener (new KeyAdapter()
		{
			public void keyTyped (KeyEvent ke)
			{
				char c = ke.getKeyChar ();
				if (Character.isDigit (c))// || (c == KeyEvent.VK_BACK_SPACE))))
				{
					getToolkit().beep ();
					ke.consume ();
      			}
    		}
  		}
		);

		txtMobNo.addKeyListener (new KeyAdapter()
		{
			public void keyTyped (KeyEvent ke)
			{
				char c = ke.getKeyChar ();
				if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE))))
				{
					getToolkit().beep ();
					ke.consume ();
      		    }
    	  }
  		}
		);

		String Months[] = {"January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"};
		cboMonth = new JComboBox (Months);
		cboDay = new JComboBox ();
		cboYear = new JComboBox ();
		for (int i = 1; i <= 31; i++)
		{
			String days = "" + i;
			cboDay.addItem (days);
		}
		for (int i = 1998; i <= 2024; i++)
		{
			String years = "" + i;
			cboYear.addItem (years);
		}

		//Aligning The Date Option Controls.
		cboMonth.setBounds (355, 105, 92, 25);
		cboDay.setBounds (452, 105, 43, 25);
		cboYear.setBounds (500, 105, 60, 25);

		//Aligning The Buttons.
		btnStartCam = new JButton("Start Finger Device");
		btnStartCam.setBounds(20,260,240,35);
		btnStartCam.setEnabled(false);
		btnStartCam.addActionListener(this);
		
		btnSave = new JButton ("Save");
    	btnSave.setBounds (285, 260, 120, 35);
		btnSave.addActionListener (this);

		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (425, 260, 120, 35);
		btnCancel.addActionListener (this);


		JPanel panel = new JPanel ();
        JLabel label = new JLabel();
        panel.setBounds(0,5,260,230);
        label.setIcon(new ImageIcon("Images/reg.jpg"));// your image here
        panel.add(label);

		//Adding the All the Controls to Panel.
		jpInfo.add (lbNo);
		jpInfo.add (txtNo);
		jpInfo.add (lbName);
		jpInfo.add (txtName);
		jpInfo.add (lbDate);
		jpInfo.add (cboMonth);
		jpInfo.add (cboDay);
		jpInfo.add (cboYear);
		jpInfo.add (lbMobNo);
		jpInfo.add (txtMobNo);
		jpInfo.add(lbWordNo);
		jpInfo.add(txtAdd);
		jpInfo.add(txtPath);
		jpInfo.add (btnBrowse);
		jpInfo.add(lbWordName);
		jpInfo.add (btnSave);
		jpInfo.add (btnCancel);
		jpInfo.add (panel);
		jpInfo.add (btnStartCam);

		getContentPane().add (jpInfo);

		//In the End Showing the New Account Window.
		setVisible (true);
		addWindowListener (new WindowAdapter ()
		{
			public void windowClosing (WindowEvent we)
			{
				quitApp ();
			}
		}
		);
}
	private void quitApp ()
	{
		try
		{
		    	int reply = JOptionPane.showConfirmDialog (this,"Are you really want to exit\nFrom  System?","Patient Information System - Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (reply == JOptionPane.YES_OPTION)
				{
				setVisible (false);	//Hide the Frame.

				AudioClip ac = Applet.newAudioClip(new File("Images/Good_Bye.wav").toURL());
				ac.play();

				RegisterPatient b=new RegisterPatient(0);
				Thread t=new Thread(b);
				t.sleep(1000);
				System.exit (0);        //Close the Application.
				}
				else if (reply == JOptionPane.NO_OPTION)
				{
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			   }
		 }
   	    catch (Exception e) {}
     }


	public void actionPerformed (ActionEvent ae)
	{
		Object obj = ae.getSource();

		if (obj == btnSave)
		{
			if (txtNo.getText().equals(""))
			{
				JOptionPane.showMessageDialog (this, "Please! Provide Patient number.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtNo.requestFocus();
			}
			else if ((txtNo.getText().length())!=12)
			{
				JOptionPane.showMessageDialog (this, "Please! Provide Valid Adhar Number.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtMobNo.requestFocus ();
			}
			else if (txtName.getText().equals("")) {
				JOptionPane.showMessageDialog (this, "Please! Provide Name of Patient.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtName.requestFocus ();
			}
			else if (txtMobNo.getText().equals(""))
			{
				JOptionPane.showMessageDialog (this, "Please! Provide Mobile Number.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtMobNo.requestFocus ();
			}
			else if ((txtMobNo.getText().length())!=10)
			{
				JOptionPane.showMessageDialog (this, "Please! Provide Valid Mobile Number.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtMobNo.requestFocus ();
			}
			else if (txtAdd.getText().equals(""))
			{
				JOptionPane.showMessageDialog (this, "Please! Provide Patient Address.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtNo.requestFocus();
			}
			else if (txtPath.getText().equals(""))
			{
				JOptionPane.showMessageDialog (this, "Please! Provide Patient Medical Report.","Patient Information System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				txtNo.requestFocus();
			}		
			else
			{		
				try
				{
					boolean status=true;
					DAO d = new DAO();
					Connection conn = d.getConnection();

					String insertquery = "insert into registration values(?,?,?,?,?,?,?,?)";
					
					PreparedStatement pstmt = null;
					try 
					{
						pstmt = conn.prepareStatement(insertquery);
						pstmt.setString(1,txtNo.getText());
						pstmt.setString(2,txtName.getText());
						pstmt.setString(3,"" + cboMonth.getSelectedItem ());
						pstmt.setString(4,"" + cboDay.getSelectedItem ());
						pstmt.setString(5,"" + cboYear.getSelectedItem ());
						pstmt.setString(6,txtMobNo.getText ());
						pstmt.setString(7,txtAdd.getText());
						pstmt.setString(8,txtPath.getText());
						
						rows = pstmt.executeUpdate();

						btnSave.setEnabled(false);
						btnStartCam.setEnabled(true);				
						
					} catch (SQLException e) 
					{
						JOptionPane.showMessageDialog (this, "Duplicate Entry: Please change ID",
								"Patient System - Duplicate Record Saved", JOptionPane.PLAIN_MESSAGE);

						System.out.println(e);
						status=false;
					} 
					finally 
					{
						try 
						{
							pstmt.close();
							conn.close();
							
						} catch (SQLException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


					}

					if(status)
					{
						JOptionPane.showMessageDialog (this, "The Record Saved Successfully Please Enter Fingerprint !!",
										"Patient System - Record Saved", JOptionPane.PLAIN_MESSAGE);
					}

				}
				catch (Exception ioe)
				{
					JOptionPane.showMessageDialog (this, "There are Some Problem with File",
								"Attendance System - Problem", JOptionPane.PLAIN_MESSAGE);
				}
			}			
			
		}
		
		if (obj == btnCancel)
		{
			txtClear ();
			setVisible (false);
			dispose();
		}

		if(obj == btnBrowse)
		{
	      sourcefile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);   //open file chooser dialog box
	      
			if(sourcefile.showOpenDialog(null)== sourcefile.APPROVE_OPTION)
			{
		 	  spath= sourcefile.getSelectedFile().getPath();   // get file path
		 	  txtPath.setText(spath);  // disply path in text box
			}
		      
		}
		
		if(obj == btnStartCam)
		{
				detectFace=true;
						
				FingerPrintScan.file1 = txtNo.getText();
					FingerPrintScan frame1 = new FingerPrintScan();
						frame1.setVisible(true);	
						
						System.out.println("Reader Start");
						setVisible (false);

		  }
		}
	

	void txtClear ()
	{
		txtNo.setText ("");
		txtName.setText ("");
		txtMobNo.setText ("");
		txtNo.requestFocus ();
	}
}