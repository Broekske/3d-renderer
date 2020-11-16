package app;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.*;

public class Cone implements Renderable {

    public float offsetZ = 7f;
    public float offsetX = 0f;
    public float offsetY = 1.7f;

    Vec3d[] verticesC1; 
    Vec3d[] verticesC2; 

    Coord2d[] spC1;
    Coord2d[] spC2;

    public Cone(float radius1, float radius2, float height) {

        verticesC1 = calcCircle(radius1, 0);
        verticesC2 = calcCircle(radius2, -height);

        spC1 = new Coord2d[verticesC1.length];
        spC2 = new Coord2d[verticesC2.length];
    }

    private Vec3d[] calcCircle(float radius, float height) {
        float step = (float)(Math.PI / 10f);
        ArrayList<Vec3d> vertexList = new ArrayList<Vec3d>();
        for (float a = 0; a <= 2 * Math.PI; a += step) {
            float z = (float) Math.sin(a) * radius;
            float x = (float) Math.cos(a) * radius;

            Vec3d vertex = new Vec3d(x, height, z);
            vertexList.add(vertex);
        }
        return vertexList.toArray(new Vec3d[vertexList.size()]);
    }

    @Override
    public void render(Graphics g, PerspectiveProjector proj) {
        renderCircle(g,proj,spC1,verticesC1);
        renderCircle(g,proj,spC2,verticesC2);

        for(int i = 0; i < spC1.length;i++){
            g.drawLine(spC1[i].x,spC1[i].y,spC2[i].x,spC2[i].y);
        }

    }
    public void renderCircle(Graphics g, PerspectiveProjector proj, Coord2d[] sp, Vec3d[] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            Vec3d offsetVertex = new Vec3d(vertices[i].x + offsetX, vertices[i].y + offsetY, vertices[i].z + offsetZ);
            sp[i] = proj.pointToScreenCoord(offsetVertex);
            if(sp[i] != null){
                g.setColor(Color.RED);
                g.fillOval(sp[i].x, sp[i].y, 5, 5);
            }

        }
        for(int i = 0; i < sp.length-1; i++){
            g.drawLine(sp[i].x, sp[i].y, sp[i+1].x, sp[i+1].y);
        }
        g.drawLine(sp[sp.length-1].x, sp[sp.length-1].y, sp[0].x, sp[0].y);

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}