
/**
 * Canvas.java
 *
 * Draws Three Rectangles to Form Pythagorean Triangle.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;


public class Canvas extends JPanel implements MouseListener{

  // define vertices x,y,z
  double[] vertA = {-1,1,1};
  double[] vertB = {1,1,1};
  double[] vertC = {1,-1,1};
  double[] vertD = {-1,-1,1};
  double[] vertG = {-1,1,-1};
  double[] vertH = {1,1,-1};
  double[] vertE = {1,-1,-1};
  double[] vertF = {-1,-1,-1};

  HashMap state = new HashMap();

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


  public Canvas (){
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

    // plot points

    for(ArrayList<double[]> face: cube){
      // draw wireframe
      Path2D.Double wireFace = null;
      for(int i=0;i<5;i++){
        double[] vert = face.get(i%4);

        vert = rotateY(vert);

        int s = 50;
        double x = vert[0]*s, y=vert[1]*s, z=vert[2]*s;
        // print(vert);
        // print(rotate(vert));
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
    //  rotate();
		 repaint();
	 }

  //  rotate method
  public double[] rotateZ(double[] vert){
    // arount z-axis
    double rad = Math.toRadians(30);
    double[] M1 = {Math.cos(rad),-Math.sin(rad),0};
    double[] M2 = {Math.sin(rad),Math.cos(rad),0};
    double[] M3 = {0,0,1};

    double[] vertT = new double[3];
    for(int i=0; i<3; i++) vertT[0] += (M1[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[1] += (M2[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[2] += (M3[i]*vert[i]);

    return vertT;
  }

  public double[] rotateY(double[] vert){
    // arount z-axis
    double rad = Math.toRadians(3);
    double[] M1 = {Math.cos(rad),0,Math.sin(rad)};
    double[] M2 = {0,1,0};
    double[] M3 = {-Math.sin(rad),0,Math.cos(rad)};

    double[] vertT = new double[3];
    for(int i=0; i<3; i++) vertT[0] += (M1[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[1] += (M2[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[2] += (M3[i]*vert[i]);

    return vertT;
  }

  public double[] rotateX(double[] vert){
    // arount z-axis
    double rad = Math.toRadians(30);
    double[] M1 = {1,0,0};
    double[] M2 = {0,Math.cos(rad),-Math.sin(rad)};
    double[] M3 = {0,Math.sin(rad),Math.cos(rad)};

    double[] vertT = new double[3];
    for(int i=0; i<3; i++) vertT[0] += (M1[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[1] += (M2[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[2] += (M3[i]*vert[i]);

    return vertT;
  }

  public void print(double[] arr){
    for(double val: arr)System.out.print(val+" ");
    System.out.println();
  }

  public void update(){
    repaint();
  }

    //Empty methods to satisfy MouseListener interface
	 public void mouseEntered(MouseEvent e){}

	 public void mouseExited(MouseEvent e){}

	 public void mousePressed(MouseEvent e){}

	 public void mouseReleased(MouseEvent e){}

}// Canvas
