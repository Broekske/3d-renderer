package app;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class App {

    JFrame frame;
    RenderPanel panel;

    Cube cube = new Cube();
    Object3D rabbit = new Object3D("./assets/chunk.obj", new Vec3d(5f,0f,10f));
    Object3D kegelke = new Object3D("./assets/cone.obj", new Vec3d(0f,0f,30f));
    Cone cone = new Cone(1f,0.5f,1f);

    public static void main(String[] args) throws Exception {
        System.out.println(new File(".").getCanonicalPath());
        System.out.println("Hello Java");
        App app = new App(800, 600);
    }

    public App(int width, int height) {
        
        frame = new JFrame("3D test");
        panel = new RenderPanel(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        run();
    }

    private void update(){
        for(Renderable renderer : panel.renderers){
            renderer.update();
        }
    }

    private void run() {

        cone.offsetZ = 5f;
        
        panel.addRenderer(cube);
        panel.addRenderer(kegelke);
        panel.addRenderer(rabbit);
        panel.addRenderer(cone);

        try {
            while (true) {
                update();
                panel.repaint();
                Thread.sleep(16);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}