
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


public class SimpleCanvas extends JPanel implements ComponentListener{

  // shape variables
  Graphics2D g2d;
  Rectangle2D.Double myRect;
  Path2D.Double myTriganle;
  Path2D.Double myTriganle2;


  public SimpleCanvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(300,300));
		setBackground(Color.lightGray);
    addComponentListener(this);

    // initialize shapes
    myRect = new Rectangle2D.Double();
    myTriganle = new Path2D.Double();
    myTriganle2 = new Path2D.Double();
  }

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		g2d = (Graphics2D)g; //cast so we can use JAVA2D.

    int w = getWidth();
    int h = getHeight();
    // to get square output
    if(w > h){
      w = h;
    }else{
      h = w;
    }

    myRect.setRect(0,0,w,h);
    paintShapeWithGradient(g2d, myRect, Color.black, Color.blue);

    // use trigonometry to create isoceless triangle
    myTriganle.moveTo(0,h);
    myTriganle.lineTo(w,h);
    int triH = h-(int)Math.sqrt(Math.pow(w,2) - Math.pow(w,2)/4);
    myTriganle.lineTo(w/2,triH);
    myTriganle.closePath();
    paintShapeWithGradient(g2d, myTriganle, Color.blue, Color.black);

    // create smaller triangle by connecting mid points
    // of the larger triangle
    int x1 = (0+w/2)/2;
    int y1 = (h+triH)/2;
    myTriganle2.moveTo(x1,y1);
    myTriganle2.lineTo((w+w/2)/2,y1);
    myTriganle2.lineTo(w/2,h);
    myTriganle2.closePath();
    paintShapeWithGradient(g2d, myTriganle2, Color.black, Color.blue);

	}

  public void paintShapeWithGradient(Graphics2D g2d, Shape shp, Color color1, Color color2){
    // get bounding box values
    Rectangle2D bbox = shp.getBounds2D();
    float x = (float)bbox.getX();
    float y = (float)bbox.getY();
    float w = (float)bbox.getWidth();
    float h = (float)bbox.getHeight();
    // create gradient and paint the shape
    GradientPaint rectGRad = new GradientPaint(0,y,color1,0,y+h,color2);
    g2d.setPaint(rectGRad);
    g2d.fill(shp);
  }

  // component listener methods
  public void componentResized(ComponentEvent e){
    System.out.println("New Window Size: "+getWidth()+"x"+getHeight());
    g2d.setColor(Color.black);
    g2d.clearRect(0,0,getWidth(),getHeight());
    repaint();
  }
  public void componentHidden(ComponentEvent e){}
  public void componentMoved(ComponentEvent e){}
  public void componentShown(ComponentEvent e){}

}// SimpleCanvas
