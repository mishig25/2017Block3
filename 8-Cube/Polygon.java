/**
 * Polygon.java
 *
 * Polygon class for creation and drawing of faces
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class Polygon implements Comparable<Polygon>{

  // instace vars
  ArrayList<double[]> vertices;
  ArrayList<double[]> verticesTransformed;
  double averageZ = 0;
  Color color = Color.gray;

  public Polygon(ArrayList<double[]> _vertices){
    vertices = _vertices;
  }// end constructor

  // rotate face
  public void rotate(String axis, int deg, double[] arb){
    verticesTransformed = new ArrayList<>();
    for(double[] vert: vertices) verticesTransformed.add(rotateVert(vert,axis,deg,arb));
  }

  // draw wireframe face
  public void wireframe(Graphics2D g2d){
    Path2D.Double wireFace = createPath();
    g2d.draw(wireFace);
  }

  // draw solid face
  public void solid(Graphics2D g2d){
    Path2D.Double wireFace = createPath();
    g2d.setColor(color);
    g2d.fill(wireFace);
  }

  // create path for drawing
  private Path2D.Double createPath(){
    Path2D.Double wireFace = null;
    for(int i=0;i<5;i++){
      double[] vert = verticesTransformed.get(i%4);
      int s = 20; // scalar factor
      double x = vert[0]*s, y=vert[1]*s, z=vert[2]*s;
      // perspective transformation
      double e = 150;
      x = x/(1-z/e);
      y = y/(1-z/e);

      if(wireFace == null){
        wireFace = new Path2D.Double();
        wireFace.moveTo(x,y);
      }else{
        wireFace.lineTo(x,y);
      }
    }

    return wireFace;
  }

  // implementation of Comparable for Painter's algorithm
  @Override
  public int compareTo(Polygon another) {
    if(this.averageZ < another.averageZ) return -1;
    return 1;
  }

  // calculate average Z point
  public double calcAverageZ(){
    double result = 0;
    for(double[] vert: verticesTransformed) result+=vert[2];
    result /= verticesTransformed.size();
    averageZ = result;
    return result;
  }

  // rotate vertex
  private double[] rotateVert(double[] vert, String axis, int deg,double[] arb){
    if(axis=="Z-axis") return rotateZ(vert,deg);
    if(axis=="Y-axis") return rotateY(vert,deg);
    if(axis=="X-axis") return rotateX(vert,deg);
    return rotateArb(vert,deg,arb);
  }

  // rotate vertex around Z-axis
  private double[] rotateZ(double[] vert, int deg){
    double rad = Math.toRadians(deg);
    double[] M1 = {Math.cos(rad),-Math.sin(rad),0};
    double[] M2 = {Math.sin(rad),Math.cos(rad),0};
    double[] M3 = {0,0,1};
    return matmult(vert,M1,M2,M3);
  }

  // rotate vertex around Y-axis
  public double[] rotateY(double[] vert, int deg){
    double rad = Math.toRadians(deg);
    double[] M1 = {Math.cos(rad),0,Math.sin(rad)};
    double[] M2 = {0,1,0};
    double[] M3 = {-Math.sin(rad),0,Math.cos(rad)};
    return matmult(vert,M1,M2,M3);
  }

  // rotate vertex around X-axis
  public double[] rotateX(double[] vert, int deg){
    double rad = Math.toRadians(deg);
    double[] M1 = {1,0,0};
    double[] M2 = {0,Math.cos(rad),-Math.sin(rad)};
    double[] M3 = {0,Math.sin(rad),Math.cos(rad)};
    return matmult(vert,M1,M2,M3);
  }

  // rotate vertex around Arb-axis
  private double[] rotateArb(double[] vert, int deg, double[] arb){
    // normalize arb
    double[] arbN = new double[3];
    double len = Math.sqrt(Math.pow(arb[0],2)+Math.pow(arb[1],2)+Math.pow(arb[2],2));
    for(int i=0;i<arbN.length;i++) arbN[i] = arb[i]/len;

    double rad = Math.toRadians(deg);
    double c = Math.cos(rad);
    double s = Math.sin(rad);
    double x = arbN[0], y = arbN[1], z = arbN[2];


    double[] M1 = {(c+(1-c)*Math.pow(x,2)),((1-c)*x*y-s*z),((1-c)*x*z+s*y)};
    double[] M2 = {((1-c)*x*y+s*z),(c+(1-c)*Math.pow(y,2)),((1-c)*y*z-s*x)};
    double[] M3 = {((1-c)*x*z-s*y),((1-c)*y*z+s*x),(c+(1-c)*Math.pow(z,2))};

    return matmult(vert,M1,M2,M3);
  }

  // for 3x3 3x1 matrix multiplication
  private double[] matmult(double[] vert,double[] M1,double[] M2,double[] M3){
    double[] vertT = new double[3];
    for(int i=0; i<3; i++) vertT[0] += (M1[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[1] += (M2[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[2] += (M3[i]*vert[i]);
    return vertT;
  }

}// end Polygon
