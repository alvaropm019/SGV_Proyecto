/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

/**
 *
 * @author fms
 */
public class Aoi {

    private JPanel panel;
    private int centreX, centreY, radius;
    private Color color;
    private Ellipse2D ellipse;

    public Aoi(JPanel panel, int centreX, int centreY, int radius, Color color) {
        this.panel = panel;
        this.color = color;
        this.centreX = centreX;
        this.centreY = centreY;
        this.radius = radius;
    }
    
    public void paint(){
        Graphics g = panel.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        ellipse = new Ellipse2D.Double(centreX-radius, centreY-radius, 2*radius, 2*radius);
        g2.setPaint(color);
        g2.fill(ellipse);
       
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCentreX() {
        return centreX;
    }

    public int getCentreY() {
        return centreY;
    }

    public int getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    public Ellipse2D getEllipse() {
        return ellipse;
    }

}
