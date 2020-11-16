package app;

public class Vec3d{
    public float x;
    public float y;
    public float z;

    public Vec3d(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3d(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vec3d(Vec3d vector){
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }
}