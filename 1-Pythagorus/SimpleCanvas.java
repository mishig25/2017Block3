
/**
 * SimpleCanvas.java
 *
 * Draws Three Rectangles to Form Pythagorean Triangle.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class SimpleCanvas extends JPanel implements MouseListener{

  Rectangle2D.Double myRect1;
  Rectangle2D.Double myRect2;
  Rectangle2D.Double myRect3;
  Polygon triangle;
  Shape[] shapes;

  int rotateDegree;
  Color[] colors;
  int colorCyle;


  public SimpleCanvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(300,300));
		setBackground(Color.lightGray);
		addMouseListener(this);
    // define colors
    colors = new Color[] {Color.green,Color.red,Color.blue,Color.cyan,Color.yellow,Color.orange};
    colorCyle = 0;
    // defines shapes
    myRect1 = new Rectangle2D.Double(0,0,40,20);
    myRect2 = new Rectangle2D.Double(40,-30,30,30);
    myRect3 = new Rectangle2D.Double(0,-20,50,20);
    triangle = new Polygon(new int[]{0, 40, 40}, new int[]{0, 0, -30}, 3);
    shapes = new Shape[] {myRect1, myRect2, triangle, myRect3};

    rotateDegree = 60; // defining overall rotation degree
  }

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    // transformation on the entire set
		g2d.translate(getWidth()/4,getHeight()/2);
    g2d.scale(2,2);
    g2d.rotate(Math.toRadians(rotateDegree),25,-15);
    // coloring different shapes, and rotating third rectangle
    for(int i=0; i<shapes.length; ++i){
      if (i==3) g2d.rotate(Math.toRadians(-36.5),myRect3.x,myRect3.y+myRect3.height);
      g2d.setPaint(colors[(i+colorCyle)%colors.length]);
      g2d.fill(shapes[i]);
    }
	}

	 public void mouseClicked(MouseEvent e){
    // increment rotation degrees and color cycle index
    rotateDegree += 30;
    colorCyle += 1;
		repaint();
	 }

    //Empty methods to satisfy MouseListener interface
	 public void mouseEntered(MouseEvent e){}

	 public void mouseExited(MouseEvent e){}

	 public void mousePressed(MouseEvent e){}

	 public void mouseReleased(MouseEvent e){}

}// SimpleCanvas
