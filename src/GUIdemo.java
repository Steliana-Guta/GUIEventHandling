//GUIdemo.java
import javax.swing.*;
import java.awt.*;

public class GUIdemo extends JFrame
{
  private DataPanel dp;        // Interface area display

  public GUIdemo()
  {
	setTitle("GUIdemo");               // JFrame title
	setBounds(100,100,500,500);        // JFrame location within screen: Position xleft, ydown, width, height
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	Container cp = getContentPane();   // Need Content Pane so can add to JFrame       
	cp.setLayout(null);                // Suppress default layout

	dp = new DataPanel();              // Create panel containing interface (could just use more than one panel)
	dp.setBounds(90, 90, 400, 400);      // Location within JFrame x, y, w, h

	cp.add(dp);	                     // Add the Panel to the JFrame
  }

  public static void main(String[] args) // main method
  {
	  GUIdemo testRun = new GUIdemo();   // Create object of primary class
	testRun.setVisible(true);          // Make JFrame visible
  }
}