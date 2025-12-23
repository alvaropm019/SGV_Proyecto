/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import traffic.Traffic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jvila
 */
public class TrafficPlot extends JPanel {
    private Traffic tr;
    private TrafficGraphic tg;
    private GenericRadarPanel rp;
    private Dimension preferredSize;
    private final int RADIUS=6;
    private final int offX = 5;
    private int offY = 10;
    
    // Aircraft icon
    private BufferedImage aircraft = null;
    
    PositionGraphic gPos;
    
    java.text.DecimalFormat formatter1;

    public TrafficPlot(TrafficGraphic tg) {
        this.tg = tg;
        this.tr = tg.getTraffic();
        this.rp = tg.getRadarPanel();
        
        try {
            aircraft = ImageIO.read(new File("src/resources/avion24_azul.png"));
        } catch (IOException ex) {
            Logger.getLogger(TrafficPlot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setBackground(Color.black);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(80,20));

        PositionGraphic gPos = tg.getgPos();
        this.setBounds(gPos.getX()-offX, gPos.getY()-offY, 80, 30);
        
        this.setVisible(true);
        
        formatter1 = new java.text.DecimalFormat("000");
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                System.out.println(tr.getICAO24()+" "+gPos.getX()+","+gPos.getY()+ " "+tg.isSelected());
                tg.setSelected(!tg.isSelected());
            }
        });
    }
    
    public TrafficGraphic getTrafficGraphic() {
        return tg;
    }

    public void setPreferredSize(Dimension preferredSize) {
        this.preferredSize=preferredSize;
        //super.setPreferredSize(preferredSize); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dimension getPreferredSize() {
        //return super.getPreferredSize(); //To change body of generated methods, choose Tools | Templates.
        return this.preferredSize;
    }

    public PositionGraphic getPosGraphic() {
        return gPos;
    }   
    
    @Override
    public void paintComponent(Graphics g) {
        if (rp instanceof exercise7.RadarPanel) {
            paintComponent2(g);
        } else {
            paintComponent1(g);
        }
    }
    
    public void paintComponent1(Graphics g) {
        int lon, lat;        
        super.paintComponent(g);
        
        int pw2=RADIUS;
        int ph2=RADIUS;
        
        this.gPos = new PositionGraphic(tr.getPosition_ext(), (RadarPanelInt) rp);
        
        if (! tg.isSelected() ){
            this.setBounds(gPos.getX()-pw2, gPos.getY()-ph2, pw2+80, ph2+15);
            //g.drawRect(0, 0, pw2+80, ph2+15);
            if (tg.isInConflict()) {
                g.setColor(Color.RED);
            } else { 
                g.setColor(tg.getColor()); 
            }
            paintPlane1(g);
            //g.setColor(tg.getColor());
            
            
            
            g.drawString(tr.getCallsign(), pw2+20, ph2+10);           
        }  else {
            this.setBounds(gPos.getX()-pw2, gPos.getY()-ph2, pw2+135, ph2+30);
            g.setColor(Color.GRAY);
            g.drawRect(0, 0, pw2+135, ph2+30);
            if (tg.isInConflict()) {
                g.setColor(Color.RED);
            } else { 
                g.setColor(tg.getColor()); 
            }            
            paintPlane1(g);
            //g.setColor(tg.getColor());
            
            
            g.drawString(tr.getCallsign()+" "+formatter1.format(tr.getGs()) + " kts", pw2+20, ph2+10);
            g.drawString(formatter1.format(tr.getAlt_ext()) + " ft " + formatter1.format(tr.getVr())+ " fpm", pw2+20, ph2+25);
        }       
    }

    public void paintComponent2(Graphics g) {
        int lon, lat;        
        super.paintComponent(g);               
        Graphics2D g2=(Graphics2D) g;
        
        int pw2=aircraft.getWidth()/2;
        int ph2=aircraft.getHeight()/2;
        
        this.gPos = new PositionGraphic(tr.getPosition_ext(), (RadarPanelInt) rp);       
        
        if (! tg.isSelected() ){
            this.setBounds(gPos.getX()-pw2, gPos.getY()-ph2, pw2+75, ph2+10);
            //g2.drawRect(0, 0, pw2+75, ph2+10);
            paintPlane2(g2);
            //g2.setColor(tg.getColor());
            if (tg.isInConflict()) {
                g2.setColor(Color.RED);
            } else { 
                g2.setColor(tg.getColor()); 
            }
            
            g2.drawString(tr.getCallsign(), pw2+10, ph2);           
        }  else {
            this.setBounds(gPos.getX()-pw2, gPos.getY()-ph2, pw2+130, ph2+20);
            //g2.drawRect(0, 0, pw2+130, ph2+20);
            paintPlane2(g2);
            //g2.setColor(tg.getColor());
            if (tg.isInConflict()) {
                g2.setColor(Color.RED);
            } else { 
                g2.setColor(tg.getColor()); 
            }
            g2.drawString(tr.getCallsign()+" "+formatter1.format(tr.getGs()) + " kts", pw2+15, ph2+5);
            g2.drawString(formatter1.format(tr.getAlt_ext()) + " ft " + formatter1.format(tr.getVr())+ " fpm", pw2+15, ph2+20);
        } 
    }
    
    private void paintPlane1(Graphics g) {
        //g.setColor(tg.getColor());            
        
        // Definimos el color según la prioridad de los estados
        if (tr.isExtrapolation()) {
            // Prioridad 1: Si se está extrapolando, color Violeta (Magenta)
            g.setColor(Color.MAGENTA); 
        } else if (tg.isInConflict()) {
            // Prioridad 2: Si hay conflicto de distancia, color Rojo
            g.setColor(Color.RED);
        } else {
            // Prioridad 3: Estado normal
            g.setColor(Color.WHITE);
        }
        //if (tr.isExtrapolation())
        //    g.setColor(Color.RED);
        //else
        //    g.setColor(Color.WHITE);
        g.drawLine(RADIUS+2, RADIUS+2, RADIUS + 18, RADIUS + 5);
        g.fillOval(RADIUS, RADIUS, RADIUS, RADIUS); 
    }
    
    private void paintPlane2(Graphics2D g) {
        AffineTransform at = new AffineTransform();
        // inverse order:
        // 3. translate it to the center of the component
        at.translate(aircraft.getWidth() / 2, aircraft.getHeight() / 2);
        // 2. do the actual rotation
        at.rotate(Math.PI * tr.getTrack() / 180.0);
        // 1. trans/late the object so that you rotate it around the center
        at.translate(-aircraft.getWidth() / 2, -aircraft.getHeight() / 2);
        g.drawImage(aircraft, at, null);
        //g.drawImage(aircraft, 0, 0,null);
    }
 
}
//holagi