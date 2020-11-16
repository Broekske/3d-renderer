package app;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.*;

public class Object3D implements Renderable {

    Vec3d[] localVertices;
    Vec3d[] vertices;
    ArrayList<int[]> faces;

    Vec3d position;

    Coord2d[] sp;

    double a;

    @Override
    public void render(Graphics g, PerspectiveProjector proj) {
        for (int i = 0; i < vertices.length; i++) {

            Vec3d offsetVertex = new Vec3d(vertices[i].x, vertices[i].y, vertices[i].z);
            sp[i] = proj.pointToScreenCoord(offsetVertex);

        }
            for (int[] face : faces) {

                drawLine(sp[face[0]], sp[face[1]], g, Color.RED);
                drawLine(sp[face[1]], sp[face[2]], g, Color.RED);
                drawLine(sp[face[2]], sp[face[0]], g, Color.RED);

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

    @Override
    public void update(){
        rotate(0.01);
        //translate(new Vec3d(0.01f,0.01f,0.01f));
    }

    public void rotate(double angle){

        for(int i = 0; i<vertices.length; i++){

            float newZ = (float) (localVertices[i].z * (Math.cos(angle)) - localVertices[i].x * (Math.sin(angle)));
            float newX = (float) (localVertices[i].z * (Math.sin(angle)) + localVertices[i].x * (Math.cos(angle)));
            float newY = (vertices[i].y);

            localVertices[i] = new Vec3d(newX,newY,newZ);
            vertices[i] = new Vec3d(newX+position.x, newY+position.y, newZ+position.z);
            
        }
    }

    public void translate(Vec3d vec){

        for(int i = 0; i<vertices.length; i++){

            float newX = vertices[i].x + vec.x;
            float newY = vertices[i].y + vec.y;
            float newZ = vertices[i].z + vec.z;

            vertices[i] = new Vec3d(newX, newY, newZ);
        }

    }


    public Object3D(String filePath, Vec3d offset){

        ObjFileReader reader = new ObjFileReader(filePath);
        localVertices = reader.getVertices();
        vertices = reader.getVertices();
        translate(offset);

        position = new Vec3d(offset);

        faces = reader.getFaces();

        sp = new Coord2d[vertices.length];

    }
    
    
}