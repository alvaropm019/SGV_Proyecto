/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author fms
 */
public class CanvasPanel extends JPanel {
    boolean visible;

    public CanvasPanel() {
        visible=false;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if (visible) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.red);
            g2.fillRect(50, 50, 200, 100);
        }
    }

}
