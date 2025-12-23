/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author angel
 */
public class CanvasPanel extends JPanel{

    private int posRectangle = 50;
    
    public CanvasPanel() {
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        g2.fillRect(posRectangle, posRectangle, 200, 100);      
    }

    public void setPosRectangle(int posRectangle) {
        this.posRectangle = posRectangle;
    }
  
}
