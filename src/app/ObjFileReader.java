package app;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ObjFileReader {

    Vec3d[] vertices;
    int[] faces;

    ArrayList<int[]> faceList = new ArrayList<int[]>();

    public ObjFileReader(String filePath) {
        try {
            File file = new File(filePath);

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = null;

            ArrayList<Vec3d> vertexList = new ArrayList<Vec3d>();

            while ((line = bufferedReader.readLine()) != null) {
                if (line.charAt(0) == 'v') {
                    String[] split = line.split(" ");
                    float x = Float.parseFloat(split[1]);
                    float y = Float.parseFloat(split[2]);
                    float z = Float.parseFloat(split[3]);
                    Vec3d vertex = new Vec3d(x,y,z);
                    vertexList.add(vertex);
                }
                vertices = vertexList.toArray(new Vec3d[vertexList.size()]);

                if (line.charAt(0) == 'f') {
                    String[] split = line.split(" ");
                    int[] face = new int[3];
                    face[0] = Integer.parseInt(split[1])-1; // minus 1 because first vertex in file is 1
                    face[1] = Integer.parseInt(split[2])-1;
                    face[2] = Integer.parseInt(split[3])-1;
                    faceList.add(face);
                }
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Vec3d[] getVertices() {
        Vec3d[] cloneArray = new Vec3d[vertices.length];

        for(int i = 0; i<vertices.length;i++){
            cloneArray[i] = new Vec3d(vertices[i]);
        }
        return cloneArray;
    }

    public ArrayList<int[]> getFaces() {

        return new ArrayList<int[]>(faceList);
    }

}