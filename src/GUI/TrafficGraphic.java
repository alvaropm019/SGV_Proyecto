/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import traffic.Traffic;
import traffic.TrafficExtended;


/**
 *
 * @author fms
 */
public class TrafficGraphic implements TrafficExtended {
    private Traffic tr;
    private GenericRadarPanel rp;
    
    private PositionGraphic gPos;

    private Color color;    
    private boolean selected;
    
    private boolean inConflict = false;
    public static boolean CALCULO_CONFLICT = false;
    
    private TrafficPlot tfp;
    
    private boolean visible=false;
    
     
    public TrafficGraphic(Traffic tr, GenericRadarPanel rp) {
        this.tr=tr;
        this.rp=rp;
        
        this.color = Color.WHITE;        
        this.selected = false;
        
        this.gPos = new PositionGraphic(tr.getPosition_ext(),rp);
        
        tfp=new TrafficPlot(this);
        
        //this.setTrafficVisible(true);
        tr.setTrafficExt(this); 
               
    }

    @Override
    public void newPosition() { 
        // You can use this method to add the new position to a traffic log
        this.gPos = new PositionGraphic(tr.getPosition_ext(), rp);
    }

    
    public Traffic getTraffic() {
        return tr;
    }

    public TrafficPlot getTrafficPlot() {
        return tfp;
    }
    
    public GenericRadarPanel getRadarPanel() {
        return rp;
    }
    
    public PositionGraphic getgPos() {
        this.gPos = new PositionGraphic(tr.getPosition_ext(), rp);
        return gPos;
    }
       
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public synchronized boolean isSelected() {
        return selected;
    }

    public synchronized void setSelected(boolean selected) {
        this.selected = selected;
    }

    public synchronized boolean isVisible() {
        return visible;
    }
    
    public void setInConflict(boolean state) { 
        this.inConflict = state; 
    }
    public boolean isInConflict() { 
        return inConflict;
    }
    
//    public void setTrafficVisible(boolean vis) {
//        this.gPos = new PositionGraphic(tr.getPosition_ext(), rp);
//
//        if (vis) {
//            if (!visible) {
//                //tfp.setBounds(gPos.getX() - 5, gPos.getY() - 10, 80, 30);
//                rp.add(tfp);
//                visible = true;
//            }
//        } else {
//            if (visible) {
//                rp.remove(tfp);
//                visible = false;
//            }
//        }
//
//        tfp.repaint();
//    }

}
//holagi