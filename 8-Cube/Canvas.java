
/**
 * Canvas.java
 *
 * Draws Cube
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class Canvas extends JPanel{

  // define vertices x,y,z for Cube
  double[] vertA = {-1,1,1};
  double[] vertB = {1,1,1};
  double[] vertC = {1,-1,1};
  double[] vertD = {-1,-1,1};
  double[] vertE = {-1,1,-1};
  double[] vertF = {1,1,-1};
  double[] vertG = {1,-1,-1};
  double[] vertH = {-1,-1,-1};

  // state
  HashMap state = new HashMap();

  // instance vars
  Polygon faceA,faceB,faceC,faceD,faceE,faceF;
  ArrayList<Polygon> cube;


  public Canvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(300,300));
		setBackground(Color.white);

    // init state
    state.put("axis","Z-axis");
    state.put("degree",0);
    state.put("renderMode","Wireframe");
    state.put("arbX",1);
    state.put("arbY",1);
    state.put("arbZ",0);

    // init faces
    faceA = new Polygon(new ArrayList<>(Arrays.asList(vertC,vertB,vertA,vertD)));
    faceB = new Polygon(new ArrayList<>(Arrays.asList(vertG,vertF,vertB,vertC)));
    faceC = new Polygon(new ArrayList<>(Arrays.asList(vertH,vertE,vertF,vertG)));
    faceD = new Polygon(new ArrayList<>(Arrays.asList(vertD,vertA,vertE,vertH)));
    faceE = new Polygon(new ArrayList<>(Arrays.asList(vertB,vertF,vertE,vertA)));
    faceF = new Polygon(new ArrayList<>(Arrays.asList(vertG,vertC,vertD,vertH)));
    // init cube
    cube = new ArrayList<>(Arrays.asList(faceA,faceB,faceC,faceD,faceE,faceF));

    // set different face colors
    Color[] colors = {Color.red,Color.green,Color.blue,
                      Color.orange,Color.lightGray,Color.black};
    for(int i=0; i<colors.length; i++) cube.get(i).color = colors[i];
  }// end constructor

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    // transformation on the entire set
		g2d.translate(getWidth()/2,getHeight()/2);
    g2d.scale(2,2);
    g2d.setColor(Color.black);

    // parse current state
    String axis = (String)state.get("axis");
    int deg = (Integer)state.get("degree");
    String renderMode = (String)state.get("renderMode");
    double[] arbAxis = {(Integer)state.get("arbX"),(Integer)state.get("arbY"),(Integer)state.get("arbZ"),};

    // rotate each faces
    for(Polygon face: cube){
      face.rotate(axis,deg,arbAxis);
      face.calcAverageZ();
    }
    // sort faces based on Z values for Painter's algorithm
    Collections.sort(cube);
    // draw faces
    if(renderMode == "Wireframe"){
      for(Polygon face: cube) face.wireframe(g2d);
    }else{
      for(Polygon face: cube) face.solid(g2d);
    }
	}// end paintComponent

  // for other classes to call repaint
  public void update(){
    repaint();
  }// end update

}// Canvas
