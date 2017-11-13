/**
 * Emitter.java
 *
 * Draws Three Rectangles to Form Pythagorean Triangle.
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

  ArrayList<Particle> particles;
  Random ran;
  HashMap state;

  public Emitter(HashMap _state){
    ran = new Random();
    particles = new ArrayList();
    state = _state;
  }
  public void emit(int n){
    double speed = (double)state.get("speed");
    Color color1 = (Color)state.get("color1");
    Color color2 = (Color)state.get("color2");
    for(int i=0; i<n; i++){
      Particle particle = new Particle(ran,speed,color1,color2);
      particles.add(particle);
    }
  }
  public void update(Graphics2D g2d){
    double xforce = (double)state.get("xforce");
    double yforce = (double)state.get("yforce");
    for(Particle part: particles){
      part.update(xforce, yforce);
      part.draw(g2d);
    }
  }
  public void removeSmallParticles(){
    ArrayList<Particle> _particles = new ArrayList();
    for(Particle part: particles){
      if(part.size > 1.0) _particles.add(part);
      // TODO if they exited screen boundry
    }
    particles = _particles;
  }
}
