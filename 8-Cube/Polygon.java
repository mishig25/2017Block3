import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class Polygon implements Comparable<Polygon>{

  ArrayList<double[]> vertices;
  ArrayList<double[]> verticesTransformed;
  double averageZ = 0;
  Color color = Color.gray;

  public Polygon(ArrayList<double[]> _vertices){
    vertices = _vertices;
  }

  public void rotate(String axis, int deg, double[] arb){
    verticesTransformed = new ArrayList<>();
    for(double[] vert: vertices) verticesTransformed.add(rotateVert(vert,axis,deg,arb));
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

  public double[] rotateVert(double[] vert, String axis, int deg,double[] arb){
    if(axis=="Z-axis") return rotateZ(vert,deg);
    if(axis=="Y-axis") return rotateY(vert,deg);
    if(axis=="X-axis") return rotateX(vert,deg);
    return rotateArb(vert,deg,arb);
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

  public double[] rotateArb(double[] vert, int deg, double[] arb){
    // normalize arb
    double[] arbN = new double[3];
    double len = Math.sqrt(Math.pow(arb[0],2)+Math.pow(arb[1],2)+Math.pow(arb[2],2));
    for(int i=0;i<arbN.length;i++) arbN[i] = arb[i]/len;

    // arount arb-axis
    double rad = Math.toRadians(deg);
    double c = Math.cos(rad);
    double s = Math.sin(rad);
    double x = arbN[0], y = arbN[1], z = arbN[2];


    double[] M1 = {(c+(1-c)*Math.pow(x,2)),((1-c)*x*y-s*z),((1-c)*x*z+s*y)};
    double[] M2 = {((1-c)*x*y+s*z),(c+(1-c)*Math.pow(y,2)),((1-c)*y*z-s*x)};
    double[] M3 = {((1-c)*x*z-s*y),((1-c)*y*z+s*x),(c+(1-c)*Math.pow(z,2))};

    double[] vertT = new double[3];
    for(int i=0; i<3; i++) vertT[0] += (M1[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[1] += (M2[i]*vert[i]);
    for(int i=0; i<3; i++) vertT[2] += (M3[i]*vert[i]);

    return vertT;
  }

}// end Polygon
