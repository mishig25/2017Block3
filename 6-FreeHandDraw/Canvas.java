
/**
 * Canvas.java
 *
 * Part of the basic graphics Template.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class Canvas extends JPanel implements MouseListener,MouseMotionListener{

  Color myColor;
	Line2D.Double myLine;
  Rectangle2D.Double drawingArea;
  Rectangle2D.Double clearArea;
  Path2D.Double path;

  public Canvas (){
  		//The following is another way to guarantee correct size.
  		setPreferredSize(new Dimension(600,400));
  		setBackground(Color.lightGray);
  		addMouseListener(this);
      addMouseMotionListener(this);
      drawingArea = new Rectangle2D.Double(50,50,500,300);
      clearArea = new Rectangle2D.Double(550,350,50,50);
    }

  public void paintComponent(Graphics g){
		super.paintComponent(g);  //without this no background color set.
		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.

    g2d.setColor(Color.white);
    g2d.fill(drawingArea);

    g2d.setColor(Color.red);
    g2d.fill(clearArea);

    g2d.setColor(Color.black);
    if(path != null) g2d.draw(path);

	 }

   //Methods to satisfy MouseListener interface
	 public void mousePressed(MouseEvent e){
     int x = e.getX(), y = e.getY();
     boolean isInDrawingArea = drawingArea.contains(x,y);
     if(isInDrawingArea){
       if(path == null){
         path = new Path2D.Double();
         path.moveTo(x,y);
       }else{
         path.lineTo(x,y);
       }
     }else if(clearArea.contains(x,y)) path=null;
     repaint();
   }

   public void mouseDragged(MouseEvent e){
    int x = e.getX(), y = e.getY();
    boolean isInDrawingArea = drawingArea.contains(x,y);
    if(isInDrawingArea) path.lineTo(x,y);
    repaint();
   }

   public void mouseEntered(MouseEvent e){}
   public void mouseExited(MouseEvent e){}
   public void mouseMoved(MouseEvent e){}
   public void mouseReleased(MouseEvent e){}
   public void mouseClicked(MouseEvent e){}

}// Canvas
