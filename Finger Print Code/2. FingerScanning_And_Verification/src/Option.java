import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

//===================================================================================================================================================================

class Option extends JFrame implements ActionListener
{
	static JButton Register=new JButton(new ImageIcon("image\\Register.jpg"));
	
	static JButton Verify=new JButton(new ImageIcon("image\\Varify.jpg"));

	int width=550,height=165;
	
	JPanel panel=new JPanel();
	
	static Option frame=null;
	
	public void actionPerformed(ActionEvent e){}

	Option()
	{
		setSize(width,height);
		
		setTitle("Select Platform");
		
		setResizable(false);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		setLocation((d.width-width)/2,(d.height-height)/2);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("image\\stop1.jpg"));
		
		Register.setOpaque(true);
	
		Register.setToolTipText("Click here for Serial_Apriori");
		
		Verify.setToolTipText("Click here for Parallel_Apriori");

		panel.add(Register,"East");

		panel.add(Verify,"West");
		
		add(panel);
		
		setVisible(true);
		
		setAlwaysOnTop(true);
		
		setDefaultCloseOperation(3);
	}

//===================================================================================================================================================================

	public static void method()
	{

		frame = new Option();

		Register.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				try
				{
					AudioClip ac = Applet.newAudioClip(new File("image\\Serial_Mining.wav").toURL());
					ac.play();

					 FingerPrintScan frame1 = new FingerPrintScan();
					 frame1.setVisible(true);
				}
				catch(Exception ace){}
		    }
		});


		Verify.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				try
				{
					AudioClip ac = Applet.newAudioClip(new File("image\\Verify_Mining.wav").toURL());
					ac.play();

					 FingerPrintVerify frame1 = new FingerPrintVerify();
					 frame1.setVisible(true);


				}
				catch(Exception ace){}

			}
		});


		 frame.addWindowListener(new WindowAdapter()
		 {
		   public void windowClosing(WindowEvent e)
		   {
    	     try
   	         {
			  System.exit(0);
             }
	         catch(Exception e1){}

             System.exit(0);
		   }
		 });
	}

	public static void main(String args[])
	   {
		Option.method();
	   }

}

//==================================================================================================================================================================
