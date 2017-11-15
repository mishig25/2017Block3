/**
 * Emitter.java
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Emitter{
  // instace vars
  ArrayList<Particle> particles; // particles
  Random ran; // random number generator
  HashMap state; // configurations

  public Emitter(HashMap _state){
    // init instance vars
    ran = new Random();
    particles = new ArrayList();
    state = _state;
  }// end constructor

  // for emitting n number of new particles
  public void emit(){
    // get current emitter settings
    int n = (Integer)state.get("emfr");
    String emtype = (String)state.get("emtype");
    double speed = (double)state.get("speed");
    Color color1 = (Color)state.get("color1");
    Color color2 = (Color)state.get("color2");
    int x = (int)state.get("x");
    int y = (int)state.get("y");
    // create particles
    for(int i=0; i<n; i++){
      Particle particle = new Particle(x,y,ran,speed,color1,color2,emtype);
      particles.add(particle);
    }
  }// end emit

  // update particles positions and draw them
  public void update(Graphics2D g2d){
    // get current force settings
    double xforce = (double)state.get("xforce");
    double yforce = (double)state.get("yforce");
    // update all particles
    for(Particle part: particles){
      part.update(xforce, yforce);
      part.draw(g2d);
    }
  }// end update

  // removes particles from the scene that are too small
  public void removeSmallParticles(){
    ArrayList<Particle> _particles = new ArrayList();
    for(Particle part: particles){
      if(part.size > 1.0) _particles.add(part);
      // TODO if they exited screen boundry
    }
    particles = _particles;
  }// end removeSmallParticles
}
