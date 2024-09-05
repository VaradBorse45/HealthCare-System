import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class FileHandling {
    /**
     * Use FileWriter when number of write operations are less
     * @param Arraybs
     */
     static void writeUsingFileWriter(byte[] Arraybs,String name) {


 		// TODO Auto-generated method stub

 			OutputStream output = null;
 			try {
 				output = new BufferedOutputStream(new FileOutputStream(
 						name));
 				output.write(Arraybs);
 				output.close();
 			} catch (FileNotFoundException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} catch (Exception e) {
 				// TODO Auto-generated catch block
 				JOptionPane.showMessageDialog(null, "Error saving file.");
 			}
 	}
    public static byte[] readFile(String FILENAME)
    {
		// TODO Auto-generated method stub
    		System.out.println(FILENAME);
			InputStream input = null;
			try 
			{
				input = new BufferedInputStream(new FileInputStream(FILENAME));
				byte[] data = new byte[input.available()];
				input.read(data, 0, input.available());
				input.close();
				return data;
			}
			catch (FileNotFoundException e) 
			{
				JOptionPane.showMessageDialog(null, "file path Invalid.");
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error saving file.");
			}
		return null;
	}

}


