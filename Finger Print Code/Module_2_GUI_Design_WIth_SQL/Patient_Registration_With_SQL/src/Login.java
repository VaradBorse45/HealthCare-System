import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

import javax.swing.border.*;
import javax.swing.event.*;
import java.applet.*;

public class Login extends JFrame implements ActionListener,Runnable
{
    static String path="D:\\login.dat";

    static String FingerDBpath="imagesfp\\";
   
	private JPanel jpInfo = new JPanel();
	private JLabel l1,l2,l3,l4,l5,l6,l7;
	private JPasswordField PinVal;

	private JButton btnLogin,btnChange;

	  JComboBox cboUser;


	Login(int i){}

	public void run(){}

	Login ()
	{
		super ("System Authention");
	try
		{
		File file = new File(path);           // create file object

		if(!(file.exists()))
		{
		  DataOutputStream dout = new DataOutputStream(new FileOutputStream(path));
		  dout.writeUTF("login");
		  dout.close();
		}
 
		  
		AudioClip ac = Applet.newAudioClip(new File("images/Please Enter Password.wav").toURL());
		ac.play();

		setSize (575, 370);
		setResizable(false);
		setLocationRelativeTo(null);
		jpInfo.setBounds (0, 0, 500, 115);
		jpInfo.setLayout (null);

		String UserName[] = {"  Admin  "};
		 cboUser = new JComboBox (UserName);
		cboUser.setFont(new java.awt.Font("Bitstream Vera Sans Mono",Font.BOLD,14 ));
		cboUser.setBounds (15, 303, 105, 30);

		PinVal = new JPasswordField(10);
		PinVal.setHorizontalAlignment (JTextField.RIGHT);
		PinVal.setEchoChar('$');
		PinVal.setBounds (130, 303, 160, 30);

		btnLogin = new JButton ("Sign In");
		btnLogin.setBounds (300, 303, 120, 30);
		btnLogin.addActionListener (this);

		btnChange = new JButton ("Change");
		btnChange.setBounds (435, 303, 120, 30);
		btnChange.addActionListener (this);

		JPanel panel = new JPanel ();
        JLabel label = new JLabel();
        panel.setBounds(2,2,575,320);
        label.setIcon(new ImageIcon("Images/two.jpg"));// your image here
        panel.add(label);

		jpInfo.add (PinVal);
		jpInfo.add (cboUser);
		jpInfo.add (btnLogin);
		jpInfo.add (btnChange);
		jpInfo.add (panel);

		getContentPane().add (jpInfo);

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
		catch(Exception e){}
}
	private void quitApp ()
	{
		try
		{
		    	int reply = JOptionPane.showConfirmDialog (this,"Are you really want to exit\nFrom System?","System - Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (reply == JOptionPane.YES_OPTION)
				{
				setVisible (false);	//Hide the Frame.

				AudioClip ac = Applet.newAudioClip(new File("Images/Good_Bye.wav").toURL());
				ac.play();

				Login b=new Login(0);
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

		String pass = PinVal.getText();

   	    String user=(""+cboUser.getSelectedItem ()).trim();

		if(obj == btnChange)
		{
		  changePassword();
		}
      else
      {
	 try
	  {
     	 if(checkPassword())
		 {
	 	   setVisible(false);
		   AudioClip ac = Applet.newAudioClip(new File("Images/Welcome.wav").toURL());
		   ac.play();
		   new Project(10).start();
        }
		else
		{
				AudioClip ac = Applet.newAudioClip(new File("Images/Invalid Password.wav").toURL());
				ac.play();

			  JOptionPane.showMessageDialog(null,"Password does not matches with original password","Wrong User",JOptionPane.ERROR_MESSAGE);
	          PinVal.setText("");
		}
	  }
	   catch(Exception e1){}
    }
   }

  //===================================================================================================================================================================
	   public boolean checkPassword()
	   {
		  String original = null;
		  try
		  {
		 	DataInputStream dout = new DataInputStream(new FileInputStream(path));
		 	original = dout.readUTF();
		  }
		  catch(Exception e){}

		  if(PinVal.getText().equals(original))
		 	return true;
		  else
		    return false;
	   }
//===================================================================================================================================================================

   public void changePassword()
   {
	try
	{
	  DataInputStream din = new DataInputStream(new FileInputStream(path));        // Open Secure file
	  String pass = din.readUTF();           //Read Password
	  din.close();

	  final JFrame frame = new JFrame("Change PIN");

	  JPanel panel = new JPanel();

		  JLabel lblOldPass = new JLabel("Old PIN ");          // create label
	  JLabel lblNewPass = new JLabel("New PIN ");          // for password
	  JLabel lblConPass = new JLabel("Confirm PIN ");      // input and verification

	  final JPasswordField txtOldPass = new JPasswordField(15);  // create TextArea
	  final JPasswordField txtNewPass = new JPasswordField(15);  // for password entry
	  final JPasswordField txtConPass = new JPasswordField(15);  // and conform password

	  JButton btnOk = new JButton("Change");
	  JButton btnCancel = new JButton("Cancel");

	  panel.setBackground(new Color(22,243,25));

	  GridBagLayout gbl= new GridBagLayout();
	  GridBagConstraints gbc= new GridBagConstraints();

	  panel.setLayout(gbl);

	  gbc.weighty= 4;     // divide vertical space into 4 parts

	  gbc.gridx= 0;       // locate first part
	  gbl.setConstraints(lblOldPass, gbc);         // old password
	  panel.add(lblOldPass);                       // label

	  gbl.setConstraints(lblNewPass, gbc);  // New password
	  panel.add(lblNewPass);                // label

	  gbl.setConstraints(lblConPass, gbc);  // conform password
	  panel.add(lblConPass);                // label

	  gbl.setConstraints(btnOk, gbc);  // Change button
	  panel.add(btnOk);

	  gbc.gridx= 1;       //locate second part
	  gbl.setConstraints(txtOldPass, gbc);   // old password textArea
	  panel.add(txtOldPass);

	  gbl.setConstraints(txtNewPass, gbc);   // New password textArea
	  panel.add(txtNewPass);

      gbl.setConstraints(txtConPass, gbc);   // conform password textArea
	  panel.add(txtConPass);

	  gbl.setConstraints(btnCancel, gbc);  // cancel button
	  panel.add(btnCancel);

	  getContentPane().add(panel);

      frame.add(panel);

	  frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	  frame.setResizable(false);
	  frame.setLocation(400,300);
	  frame.setSize(310,200);
	  frame.setVisible(true);
	  this.setVisible(false);  // hide first window

	  btnOk.addActionListener(new ActionListener()
	  {
	   public void actionPerformed(ActionEvent event)
	   {
	    try
	    {
	 	  DataInputStream din = new DataInputStream(new FileInputStream(path));
		  String orginalPass = din.readUTF();
		  din.close();

		  if(txtOldPass.getText().equals(orginalPass))
		  {
		    if(txtNewPass.getText().equals(txtConPass.getText()))
		    {
		     try
		     {
		 	   DataOutputStream dout = new DataOutputStream(new FileOutputStream(path));
			   dout.writeUTF(txtNewPass.getText());
			   dout.close();

 		       AudioClip ac = Applet.newAudioClip(new File("image\\password_changed.wav").toURL());
  	           ac.play();

			   JOptionPane.showMessageDialog(null,"Password Changed!!!");
			   frame.setVisible(false);             // hide password window
			   new Login().setVisible(true); // disply first window
			 }
			 catch(Exception e){}
		    }
		    else
			{
			  JOptionPane.showMessageDialog(null,"New password does not match with confirmation password");
			}
		  }
		  else
			  JOptionPane.showMessageDialog(null,"Password does not match with the original password");
		 }
		   catch(Exception e){}
		  }
		  });

		  btnCancel.addActionListener(new ActionListener()
		  {
			 public void actionPerformed(ActionEvent event)
			 {
				frame.setVisible(false);              //hide password window
			   new Login().setVisible(true); // disply first window
			 }
		   });
		  }
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

//===================================================================================================================================================================

	public static void main(String args[])
   {
			Login obj=new Login();
			obj.setVisible(true);
		}
}