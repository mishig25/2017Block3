
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
import java.util.*;


public class SimpleCanvas extends JPanel implements MouseListener{

  // define vertices x,y,z
  double[] vertA = {-1,1,1};
  double[] vertB = {1,1,1};
  double[] vertC = {1,-1,1};
  double[] vertD = {-1,-1,1};
  double[] vertG = {-1,1,-1};
  double[] vertH = {1,1,-1};
  double[] vertE = {1,-1,-1};
  double[] vertF = {-1,-1,-1};
  ArrayList<double[]> verts = new ArrayList<>(Arrays.asList(vertA,vertB,vertC,vertD,vertE,vertF,vertG,vertH));

  // define faces counter-clockwise
  ArrayList<double[]> faceA = new ArrayList<>(Arrays.asList(vertC,vertB,vertA,vertD));
  ArrayList<double[]> faceB = new ArrayList<>(Arrays.asList(vertG,vertF,vertB,vertC));
  ArrayList<double[]> faceC = new ArrayList<>(Arrays.asList(vertH,vertE,vertF,vertG));
  ArrayList<double[]> faceD = new ArrayList<>(Arrays.asList(vertD,vertA,vertE,vertH));
  ArrayList<double[]> faceE = new ArrayList<>(Arrays.asList(vertB,vertF,vertE,vertA));
  ArrayList<double[]> faceF = new ArrayList<>(Arrays.asList(vertG,vertC,vertD,vertH));

  // define cube
  ArrayList<ArrayList<double[]>> cube = new ArrayList<>(Arrays.asList(faceA,faceB,faceC,faceD,faceE,faceF));


  public SimpleCanvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(300,300));
		setBackground(Color.white);
		addMouseListener(this);

  }

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    // transformation on the entire set
		g2d.translate(getWidth()/2,getHeight()/2);
    g2d.scale(2,2);
    g2d.setColor(Color.black);

    // draw all points, scale, perspective
    // for(double[] vert: verts){
    //   // render ellipse 2d
    //   int s = 50;
    //   double x = vert[0]*s, y=vert[1]*s, z=vert[2]*s;
    //   double e = 20;
    //   x /= (1-z/e);
    //   y /= (1-z/e);
    //   Ellipse2D.Double point = new Ellipse2D.Double(x,y,3,3);
    //   g2d.fill(point);
    // }

    for(ArrayList<double[]> face: cube){
      // draw wireframe
      Path2D.Double wireFace = null;
      for(int i=0;i<5;i++){
        double[] vert = face.get(i%4);
        int s = 50;
        double x = vert[0]*s, y=vert[1]*s, z=vert[2]*s;
        double e = 20;
        x /= (1-z/e);
        y /= (1-z/e);
        if(wireFace == null){
          wireFace = new Path2D.Double();
          wireFace.moveTo(x,y);
        }else{
          wireFace.lineTo(x,y);
        }
        // wireFace.closePath();
        g2d.draw(wireFace);
      }

    }

	}

	 public void mouseClicked(MouseEvent e){
     System.out.println("Mouse clicked");
		repaint();
	 }

    //Empty methods to satisfy MouseListener interface
	 public void mouseEntered(MouseEvent e){}

	 public void mouseExited(MouseEvent e){}

	 public void mousePressed(MouseEvent e){}

	 public void mouseReleased(MouseEvent e){}

}// SimpleCanvas
