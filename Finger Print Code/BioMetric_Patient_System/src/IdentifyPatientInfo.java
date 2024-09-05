import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.mysql.jdbc.Connection;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class IdentifyPatientInfo extends JFrame implements ActionListener
{
	private JPanel jpDep = new JPanel();
	private JLabel lbNo, lbName, lbDate, lbMob,lbAdd,lbReport;
	private JButton btnUpload,btnDown;
	private JTextField txtNo, txtName, txtMob,txtAdd,txtDate;
	private JFileChooser  destfile= new JFileChooser("./");
	private JFileChooser  uploadfile= new JFileChooser("./");

	boolean found = false;
	private String spath,uploadpath,dpath;

	private int recCount = 0;
	private int rows = 0;
	private	int total = 0;

	private String records[][] = new String [500][8];

	private FileInputStream fis;
	private DataInputStream dis;
	String acno;

	IdentifyPatientInfo (String rs)
	{
		super ("Patient Identifier");
		
		System.out.println("Patient No:"+rs);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width-335)/2,(d.height-280)/2);

		acno=rs;
		setSize (335, 280);

		jpDep.setLayout (null);

		lbNo = new JLabel ("Adhar No:");
		lbNo.setForeground (Color.black);
		lbNo.setBounds (15, 20, 80, 25);

		lbName = new JLabel ("Patient Name:");
		lbName.setForeground (Color.black);
	    lbName.setBounds (15, 55, 85, 25);

		lbDate = new JLabel ("Date of Birth:");
		lbDate.setForeground (Color.black);
		lbDate.setBounds (15, 90, 80, 25);

		lbMob = new JLabel ("Mobile No:");
		lbMob.setForeground (Color.black);
		lbMob.setBounds (15, 125, 80, 25);

		lbAdd = new JLabel ("Address.");
		lbAdd.setForeground (Color.black);
		lbAdd.setBounds (15, 160, 80, 25);

		lbReport = new JLabel ("Med. Report");
		lbReport.setForeground (Color.black);
		lbReport.setBounds (15, 195, 80, 25);

		txtNo = new JTextField ();
		txtNo.setEnabled (false);
		txtNo.setBounds (105, 20, 205, 25);

		txtName = new JTextField ();
		txtName.setEnabled (false);
		txtName.setBounds (105, 55, 205, 25);

		txtDate = new JTextField ();
		txtDate.setEnabled (false);
		txtDate.setBounds (105, 90, 205, 25);

		txtMob = new JTextField ();
		txtMob.setEnabled (false);
		txtMob.setBounds (105, 125, 205, 25);

		txtAdd = new JTextField ();
		txtAdd.setEnabled (false);
		txtAdd.setBounds (105, 160, 205, 25);

		btnUpload = new JButton ("Upload");
		btnUpload.setBounds (105, 195, 100, 25);
		btnUpload.addActionListener (this);

		btnDown = new JButton ("Download");
		btnDown.setBounds (210, 195, 100, 25);
		btnDown.addActionListener (this);


		jpDep.add (lbNo);
		jpDep.add (txtNo);
		jpDep.add (lbName);
		jpDep.add (txtName);
		jpDep.add (lbDate);
		jpDep.add (txtDate);
		jpDep.add (lbMob);
		jpDep.add (txtMob);
		jpDep.add (lbAdd);
		jpDep.add (txtAdd);
		jpDep.add (lbReport);
		jpDep.add (btnDown);
		jpDep.add (btnUpload);

		getContentPane().add (jpDep);
		
		acno=acno.replace(".txt","");
	
		showRec (acno);
	}

	public void actionPerformed (ActionEvent ae)
	{
		Object obj = ae.getSource();

		if (obj == btnDown)
		{
			System.out.println("Download Button !!!");
			
			destfile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

	         if(destfile.showOpenDialog(null)== destfile.APPROVE_OPTION)
			{
			  try
			  {
	          dpath= destfile.getSelectedFile().getPath();

	          System.out.println("DPath !!!"+dpath);

	          System.out.println("SPath !!!"+spath);
     
	          String extension = "";
	          int i = spath.lastIndexOf('.');
	          if (i > 0) 
	          {
	              extension = spath.substring(i+1);
	          }

	          copy(spath,dpath+"."+extension);	          
	          JOptionPane.showMessageDialog (null, "Report Downloaded Successfully.","Patient System", JOptionPane.PLAIN_MESSAGE);

			  System.exit(0);
			  }
			  catch(Exception e){System.out.println("error !!!"+e);}
	   		}
		}
		else
			if (obj == btnUpload)
			{
			 try
			 {
				System.out.println("Upload Button !!!");
			    
				uploadfile.setFileSelectionMode(JFileChooser.FILES_ONLY);   //open file chooser dialog box
			      
			    if(uploadfile.showOpenDialog(null)== uploadfile.APPROVE_OPTION)
				{
				 	  uploadpath= uploadfile.getSelectedFile().getPath();   // get file path
				 	  System.out.println("uploadpath :="+uploadpath);
				 	  System.out.println("Sourse path :="+spath);
				 	  
				 	  StringTokenizer st;
					  String UploadFileName="";
						
					  st = new StringTokenizer(uploadpath, "\\");
					  while(st.hasMoreTokens())
					  {
						  UploadFileName = st.nextToken();
					  }
					  
					  System.out.println("Upload File name :="+UploadFileName);
				 	  
				 	  spath=spath+"\\"+UploadFileName;
					  copy(uploadpath,spath);	          
				 	 
				 	  
				 	  JOptionPane.showMessageDialog (null, "Report Uploaded Successfully.","Patient System", JOptionPane.PLAIN_MESSAGE);
					  System.exit(0);
				}
			 }
			 catch(Exception e){System.out.println(e);}
			}
	}
	public void showRec (String intRec)
	{
		DAO data = new DAO();

		Connection conn = (Connection) data.getConnection();
		System.out.println("Connected with DB");
		
		String validateUser = "select * from  registration where id=? ";
		PreparedStatement preparedStatement =null;
		try
		{
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, intRec);

				// execute insert SQL statemen
				ResultSet result = preparedStatement.executeQuery();
				System.out.println(preparedStatement.toString()+"--------");
				if (result.next())
				{
						txtNo.setText (result.getString(2));
						txtName.setText (result.getString(1));
						txtDate.setText (result.getString(3)+"/"+result.getString(4)+"/"+result.getString(5));
						txtMob.setText(result.getString(6));
						txtAdd.setText(result.getString(7));
						spath=result.getString(8);
				}
			}
			catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	}
	
	   public void copy(String s,String d)throws Exception
	   {
		 InputStream in = null;
		 OutputStream out = null; 
		 int len=0;
		 byte[] buf = new byte[5000];
	 	 File sf=new File(s);
	 
		 if(!sf.isDirectory())
		 {
		   File source=new File(s);
		   File dest=new File(d);
		
		   try
		   {
		   	 in = new  FileInputStream(source);
			 out = new FileOutputStream(dest,true);
	
			   while (((len = in.read(buf)) > 0))
			  {
				 out.write(buf, 0, len);    // write buffer data
			   }
			 }
			 catch(IOException e)   // destination memory full
			 {
			}
		 }
			else       //means sf is not a file
			{
				String l[]=sf.list();
				File df=new File(d);
				df.mkdir();
				try
				{
				  for(int k=0;k<l.length;k++)
				 	copy(s+"\\"+l[k],d+"\\"+l[k]);
				}
				catch(Exception e)
				{
				  JOptionPane.showMessageDialog(null,e,"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
	   	}	
}