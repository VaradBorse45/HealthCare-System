import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.applet.*;

public class RegisterPatient extends JFrame implements ActionListener,Runnable
{
	private JPanel jpInfo = new JPanel();
	private JLabel lbNo, lbName, lbDate, lbFileName,lbMobNo,lbInsuranceNo,lbCrimeAdd;
	private JTextField txtNo, txtName, txtMobNo,txtInsurance,txtPath;//txtWordNo,txtCrimeAdd;
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

		lbInsuranceNo = new JLabel ("Insurance No");
		lbInsuranceNo.setForeground (Color.black);
		lbInsuranceNo.setBounds (265, 180, 90, 25);

		lbFileName = new JLabel ("Medical File");
		lbFileName.setForeground (Color.black);
		lbFileName.setBounds (265, 220, 90, 25);

		txtNo = new JTextField ();
		txtNo.setHorizontalAlignment (JTextField.RIGHT);
		txtNo.setBounds (355, 20, 205, 25);

		txtName = new JTextField ();
		txtName.setHorizontalAlignment (JTextField.RIGHT);
		txtName.setBounds (355, 60, 205, 25);

		txtMobNo = new JTextField ();
		txtMobNo.setHorizontalAlignment (JTextField.RIGHT);
		txtMobNo.setBounds (355, 140, 205, 25);

		txtInsurance = new JTextField ();
		txtInsurance.setHorizontalAlignment (JTextField.RIGHT);
		txtInsurance.setBounds (355, 180, 205, 25);

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
		for (int i = 2015; i <= 2020; i++)
		{
			String years = "" + i;
			cboYear.addItem (years);
		}

		//Aligning The Date Option Controls.
		cboMonth.setBounds (355, 105, 92, 25);
		cboDay.setBounds (452, 105, 43, 25);
		cboYear.setBounds (500, 105, 60, 25);

		//Aligning The Buttons.
		btnSave = new JButton ("Save");
		btnSave.setBounds (285, 260, 120, 35);
		btnSave.addActionListener (this);

		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (425, 260, 120, 35);
		btnCancel.addActionListener (this);

		btnStartCam = new JButton("Start Finger Device");
		btnStartCam.setBounds(20,260,240,35);
		btnStartCam.addActionListener(this);

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
		jpInfo.add(lbInsuranceNo);
		jpInfo.add(txtInsurance);
		jpInfo.add(txtPath);
		jpInfo.add (btnBrowse);
		jpInfo.add(lbFileName);
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
			else if (txtInsurance.getText().equals(""))
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
				if(detectFace)
				{
					populateArray ();	//Load All Existing Records in Memory.
					findRec ();		//Finding if Criminal No. Already Exist or Not.
				}
				else
					JOptionPane.showMessageDialog(null,"Detect The Fingerprint First");
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
	      sourcefile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);   //open file chooser dialog box

			if(sourcefile.showOpenDialog(null)== sourcefile.APPROVE_OPTION)
			{
		 	  spath= sourcefile.getSelectedFile().getPath();   // get file path
		 	 txtPath.setText(spath);  // disply path in text box
			}

		}


		if(obj == btnStartCam)
		{
			if(txtNo.getText().trim().length() == 0)
				JOptionPane.showMessageDialog(null,"Enter Patient No");
			else
			{
				boolean found=false;
				populateArray ();
				for (int x = 0; x < total; x++)
				{
				if (records[x][0].equals (txtNo.getText()))
				{
					found = true;
					JOptionPane.showMessageDialog (this, "Patient No. " + txtNo.getText () + " is Already Exist.",
								"Patient Information System - WrongNo", JOptionPane.PLAIN_MESSAGE);
					txtClear ();
					break;
					}
				}
				if(!found)
				{
					if(!running)
					{

						detectFace=true;
//						FingerPrintScan.file1 = txtNo.getText();
//									FingerPrintScan frame1 = new FingerPrintScan();
//									 frame1.setVisible(true);

				System.out.println("Reader Start");
				}
				}

			}
		}

	}

	//Function use to load all Records from File when Application Execute.
	void populateArray ()
	{
		try
		{
			fis = new FileInputStream ("Patient.dat");
			dis = new DataInputStream (fis);
			while (true)
			{
				for (int i = 0; i < 8; i++)
				{
					records[rows][i] = dis.readUTF ();
				}
				rows++;
			}
		}
		catch (Exception ex)
		{
			total = rows;
			if (total == 0) { }
			else {
				try {
					dis.close();
					fis.close();
				}
				catch (Exception exp) { }
			}
		}

	}

	//Function use to Find Record by Matching the Contents of Records Array with ID TextBox.
	void findRec () {

		boolean found = false;
		for (int x = 0; x < total; x++) {
			if (records[x][0].equals (txtNo.getText())) {
				found = true;
				JOptionPane.showMessageDialog (this, "Patient No. " + txtNo.getText () + " is Already Exist.",
							"Patient Information System - WrongNo", JOptionPane.PLAIN_MESSAGE);
				txtClear ();
				break;
			}
		}
		if (found == false) {
			saveArray ();
			save=true;
		}

	}

	//Function use to add new Element to Array.
	void saveArray ()
	{
		saves[count][0] = txtNo.getText ();
		saves[count][1] = txtName.getText ();
		saves[count][2] = "" + cboMonth.getSelectedItem ();
		saves[count][3] = "" + cboDay.getSelectedItem ();
		saves[count][4] = "" + cboYear.getSelectedItem ();
		saves[count][5] = txtMobNo.getText ();
		saves[count][6] = txtInsurance.getText();
		saves[count][7] =txtPath.getText();
		saveFile ();	//Save This Array to File.
		count++;
	}

	void saveFile ()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream ("Patient.dat", true);
			DataOutputStream dos = new DataOutputStream (fos);
			dos.writeUTF (saves[count][0]);
			dos.writeUTF (saves[count][1]);
			dos.writeUTF (saves[count][2]);
			dos.writeUTF (saves[count][3]);
			dos.writeUTF (saves[count][4]);
			dos.writeUTF (saves[count][5]);
			dos.writeUTF (saves[count][6]);
			dos.writeUTF (saves[count][7]);
			JOptionPane.showMessageDialog (this, "The Record has been Saved Successfully",
						"Patient Information System - Record Saved", JOptionPane.PLAIN_MESSAGE);
			setVisible (false);
			Project.method();
		}
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog (this, "There are Some Problem with File",
						"Patient Information System - Problem", JOptionPane.PLAIN_MESSAGE);
		}

	}

	//Function use to Clear all TextFields of Window.
	void txtClear ()
	{
		txtNo.setText ("");
		txtName.setText ("");
		txtMobNo.setText ("");
		txtNo.requestFocus ();
	}
}