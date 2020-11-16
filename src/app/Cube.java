package app;

import java.awt.*;

public class Cube implements Renderable{
    
    Coord2d[] sp = new Coord2d[8];
    /*
    Vec3d p0 = new Vec3d(1, -1, -1);
    Vec3d p1 = new Vec3d(-1, -1, -1);
    Vec3d p2 = new Vec3d(-1, 1, -1);
    Vec3d p3 = new Vec3d(1, 1, -1);
    Vec3d p4 = new Vec3d(1, -1, 1);
    Vec3d p5 = new Vec3d(-1, -1, 1);
    Vec3d p6 = new Vec3d(-1, 1, 1);
    Vec3d p7 = new Vec3d(1, 1, 1);
    */
    Vec3d p0 = new Vec3d(1, -1, -1);
    Vec3d p1 = new Vec3d(-1, -1, -1);
    Vec3d p2 = new Vec3d(-2, 1, -2);
    Vec3d p3 = new Vec3d(2, 1, -2);
    Vec3d p4 = new Vec3d(1, -1, 1);
    Vec3d p5 = new Vec3d(-1, -1, 1);
    Vec3d p6 = new Vec3d(-2, 1, 2);
    Vec3d p7 = new Vec3d(2, 1, 2);

    Vec3d[] cube = new Vec3d[]{
        p0,p1,p2,p3,p4,p5,p6,p7
      };

    float offsetZ = 10f;
    float offsetX = 0f;
    float offsetY = 0f;

    double a = 0;

    public void render(Graphics g, PerspectiveProjector proj){

        g.setColor(Color.RED);
        for(int i = 0; i<cube.length; i++){
            Vec3d offsetVertex = new Vec3d(cube[i].x + offsetX,cube[i].y + offsetY,cube[i].z + offsetZ);
            Coord2d point = proj.pointToScreenCoord(offsetVertex);
            
            if (point != null){
                g.fillOval(point.x,point.y,7,7);
                sp[i] = point;
            }
        }
        
        drawLine(sp[0],sp[1],g,Color.BLUE);
        drawLine(sp[1],sp[2],g,Color.RED);
        drawLine(sp[2],sp[3],g,Color.GREEN);
        drawLine(sp[3],sp[0],g,Color.BLACK);
        drawLine(sp[3],sp[0],g,Color.PINK);
        drawLine(sp[1],sp[5],g,Color.MAGENTA);
        drawLine(sp[2],sp[6],g,Color.MAGENTA);
        drawLine(sp[3],sp[7],g,Color.MAGENTA);
        drawLine(sp[0],sp[4],g,Color.MAGENTA);
        drawLine(sp[5],sp[4],g,Color.MAGENTA);
        drawLine(sp[5],sp[6],g,Color.MAGENTA);
        drawLine(sp[6],sp[7],g,Color.MAGENTA);
        drawLine(sp[4],sp[7],g,Color.MAGENTA);

        a= 0.02;
        for(int i = 0; i<cube.length; i++){
            float newZ = (float) (cube[i].z * (Math.cos(a)-0) - cube[i].x * (Math.sin(a)-0));
            float newX = (float) (cube[i].z * (Math.sin(a)+0) + cube[i].x * (Math.cos(a)+0));
            float newY = cube[i].y;
            cube[i] = new Vec3d(newX, newY, newZ);
        }
    }
    
    public void drawLine(Coord2d p0, Coord2d p1, Graphics g, Color color){
        if(p0 != null && p1 != null){
            g.setColor(color);
            g.drawLine(p0.x, p0.y, p1.x, p1.y);
            g.drawLine(p0.x+1, p0.y+1, p1.x+1, p1.y+1);
            g.drawLine(p0.x+2, p0.y+2, p1.x+2, p1.y+2);
        }
    }

    public Cube(){

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}