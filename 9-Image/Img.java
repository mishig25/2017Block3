
/**
 * BasicDraw.java
 *
 *
 * Template for beginning graphics programs.
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.*;

public class Img
{

    //For this type of program, event handling determines the path of
    //execution.  This main method "looks" like it sets up the frame
    //and then stops.

  public static void main(String[] args){

		JFrame myFrame = new JFrame();

    BufferedImage img = null;
    try {
      String fileName = args[0];
      img = ImageIO.read(new File(fileName));
      SimpleCanvas myCanvas = new SimpleCanvas(img,fileName);
      myFrame.setTitle("Basic Draw");
      myFrame.setSize(img.getWidth(),img.getHeight());
      //Sets the window to close when upper right corner clicked.
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myFrame.add(myCanvas);
      myFrame.pack(); //resizes to preferred size for components.
      myFrame.setResizable(false);
      myFrame.setVisible(true);
    }catch (IOException e) {
      System.out.println("Failed to load image");
      System.exit(1);
    }
  }
} // Img
