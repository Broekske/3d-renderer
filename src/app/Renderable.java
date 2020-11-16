package app;

import java.awt.*;

public interface Renderable{

    public void render(Graphics g, PerspectiveProjector proj);
    public void update();
    
}