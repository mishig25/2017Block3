
/**
 * SimpleCanvas.java
 *
 * Part of the basic graphics Template.
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.*;

public class SimpleCanvas extends JPanel{

  BufferedImage img = null;
  String fname;

  public SimpleCanvas(BufferedImage _img, String _fname){

    img = _img; // get image
    fname = _fname;
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(400,301));
		setBackground(Color.lightGray);
  }

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.

    g2d.drawImage(img,0,0,null);
    // draw glasses
    Ellipse2D.Double left = new Ellipse2D.Double(140,110,44,44);
    Ellipse2D.Double right = new Ellipse2D.Double(200,110,44,44);
    Line2D.Double line = new Line2D.Double();
    line.setLine(new Point2D.Double(140+44,110+22),new Point2D.Double(200,110+22));
    g2d = img.createGraphics();
    g2d.setPaint(Color.green);
    g2d.setStroke(new BasicStroke(5));
    g2d.draw(left);
    g2d.draw(right);
    g2d.draw(line);
    saveImage(fname);
	}

  public void saveImage(String fname){
    String newFileName = fname.substring(0,fname.length()-4);
    newFileName += "Updated.jpg";
    try {
      ImageIO.write(img, "JPEG", new File(newFileName));
    } catch (IOException e) {
      System.out.println("FAIL WRITE IMAGE");
    }
  }

}// SimpleCanvas
