
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

class Polygon implements Comparable<Polygon>{

  ArrayList<double[]> vertices;
  ArrayList<double[]> verticesTransformed;
  double averageZ = 0;
  Color color = Color.gray;

  public Polygon(ArrayList<double[]> _vertices){
    vertices = _vertices;
  }

  public void rotate(String axis, int deg){
    verticesTransformed = new ArrayList<>();
    for(double[] vert: vertices) verticesTransformed.add(rotateVert(vert,axis,deg));
  }

  public void wireframe(Graphics2D g2d){
    Path2D.Double wireFace = createPath();
    g2d.draw(wireFace);
  }

  public void solid(Graphics2D g2d){
    Path2D.Double wireFace = createPath();
    g2d.setColor(color);
    g2d.fill(wireFace);
  }

  private Path2D.Double createPath(){
    Path2D.Double wireFace = null;
    for(int i=0;i<5;i++){
      double[] vert = verticesTransformed.get(i%4);
          int s = 20;
          double x = vert[0]*s, y=vert[1]*s, z=vert[2]*s;
      if(wireFace == null){
        wireFace = new Path2D.Double();
        wireFace.moveTo(x,y);
      }else{
        wireFace.lineTo(x,y);
      }
    }
    return wireFace;
  }

  @Override
  public int compareTo(Polygon another) {
    if(this.averageZ < another.averageZ) return -1;
    return 1;
  }

  public double calcAverageZ(){
    double result = 0;
    for(double[] vert: verticesTransformed) result+=vert[2];
    result /= verticesTransformed.size();
    averageZ = result;
    return result;
  }

  public double[] rotateVert(double[] vert, String axis, int deg){
    if(axis=="Z-axis") return rotateZ(vert,deg);
    if(axis=="Y-axis") return rotateY(vert,deg);
    return rotateX(vert,deg);
  }
  //  rotate method
  public double[] rotateZ(double[] vert, int deg){
    // arount z-axis
    double rad = Math.toRadians(deg);
    double[] M1 = {Math.cos(rad),-Math.sin(rad),0};
    double[] M2 = {Math.sin(rad),Math.cos(rad),0};
    double[] M3 = {0,0,1};

    double[] vertT = new double[3];
    for(int i=0; i<3; i++) vertT[0] += (M1[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[1] += (M2[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[2] += (M3[i]*vert[i]);

    return vertT;
  }

  public double[] rotateY(double[] vert, int deg){
    // arount z-axis
    double rad = Math.toRadians(deg);
    double[] M1 = {Math.cos(rad),0,Math.sin(rad)};
    double[] M2 = {0,1,0};
    double[] M3 = {-Math.sin(rad),0,Math.cos(rad)};

    double[] vertT = new double[3];
    for(int i=0; i<3; i++) vertT[0] += (M1[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[1] += (M2[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[2] += (M3[i]*vert[i]);

    return vertT;
  }

  public double[] rotateX(double[] vert, int deg){
    // arount z-axis
    double rad = Math.toRadians(deg);
    double[] M1 = {1,0,0};
    double[] M2 = {0,Math.cos(rad),-Math.sin(rad)};
    double[] M3 = {0,Math.sin(rad),Math.cos(rad)};

    double[] vertT = new double[3];
    for(int i=0; i<3; i++) vertT[0] += (M1[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[1] += (M2[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[2] += (M3[i]*vert[i]);

    return vertT;
  }
}// end Polygon

public class Canvas extends JPanel{

  // define vertices x,y,z
  double[] vertA = {-1,1,1};
  double[] vertB = {1,1,1};
  double[] vertC = {1,-1,1};
  double[] vertD = {-1,-1,1};
  double[] vertE = {-1,1,-1};
  double[] vertF = {1,1,-1};
  double[] vertG = {1,-1,-1};
  double[] vertH = {-1,-1,-1};

  HashMap state = new HashMap();

  ArrayList<double[]> verts = new ArrayList<>(Arrays.asList(vertA,vertB,vertC,vertD,vertE,vertF,vertG,vertH));

  // define faces counter-clockwise
  Polygon faceA = new Polygon(new ArrayList<>(Arrays.asList(vertC,vertB,vertA,vertD)));
  Polygon faceB = new Polygon(new ArrayList<>(Arrays.asList(vertG,vertF,vertB,vertC)));
  Polygon faceC = new Polygon(new ArrayList<>(Arrays.asList(vertH,vertE,vertF,vertG)));
  Polygon faceD = new Polygon(new ArrayList<>(Arrays.asList(vertD,vertA,vertE,vertH)));
  Polygon faceE = new Polygon(new ArrayList<>(Arrays.asList(vertB,vertF,vertE,vertA)));
  Polygon faceF = new Polygon(new ArrayList<>(Arrays.asList(vertG,vertC,vertD,vertH)));

  // define cube
  ArrayList<Polygon> cube = new ArrayList<>(Arrays.asList(faceA,faceB,faceC,faceD,faceE,faceF));


  public Canvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(300,300));
		setBackground(Color.white);

    Color[] colors = {Color.red,Color.green,Color.blue,
                      Color.orange,Color.lightGray,Color.black};
    for(int i=0; i<colors.length; i++){
      cube.get(i).color = colors[i];
    }

    // init state
    state.put("axis","Z-axis");
    state.put("degree",0);
    state.put("renderMode","Wireframe");

  }

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    // transformation on the entire set
		g2d.translate(getWidth()/2,getHeight()/2);
    g2d.scale(2,2);
    g2d.setColor(Color.black);

    // plot points

    System.out.println((String)state.get("axis")+" "+(Integer)state.get("degree"));

    String axis = (String)state.get("axis");
    int deg = (Integer)state.get("degree");
    String renderMode = (String)state.get("renderMode");

    for(Polygon face: cube){
      face.rotate(axis,deg);
      face.calcAverageZ();
    }
    Collections.sort(cube);

    if(renderMode == "Wireframe"){
      for(Polygon face: cube) face.wireframe(g2d);
    }else{
      for(Polygon face: cube) face.solid(g2d);
    }

	}

  public void print(double[] arr){
    for(double val: arr)System.out.print(val+" ");
    System.out.println();
  }

  public void update(){
    repaint();
  }

}// Canvas
