package app;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics.*;
import java.util.ArrayList;

public class RenderPanel extends JPanel {

    int panelWidth;
    int panelHeight;

    PerspectiveProjector proj;

    int x = 450;
    int y = 500;
    double b;

    ArrayList<Renderable> renderers = new ArrayList<Renderable>();

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(0,0,panelWidth, panelHeight);

        for(Renderable renderer : renderers){
            renderer.render(g, proj);
        }

        //offsetX += (float)Math.cos(a);
        g.setFont(getFont().deriveFont(30.0f));
        g.setColor(Color.BLACK);
        b += 0.09;
        x = (int)(100* Math.cos(b)+450);
        y = (int)(100* Math.sin(b)+ 500);
        g.drawString("Kobe", x,y);
    }

    public void addRenderer(Renderable renderer){
        renderers.add(renderer);
    }

    public RenderPanel(int width, int height){
        this.panelWidth = width;
        this.panelHeight = height;
        float FOV_H =  (float)((1f/2f)*Math.PI);
        proj = new PerspectiveProjector(width, height, FOV_H);
    }

}