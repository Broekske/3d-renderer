package app;

public class PerspectiveProjector{

    private double FOV_H; 
    private double FOV_V;

    private float screenHeight;
    private float screenWidth;
    private float viewPortHeight;
    private float viewPortWidth;

    private float aspectRatio;


    public PerspectiveProjector(float screenWidth, float screenHeight, double FOV_H){
        this.FOV_H = FOV_H;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        aspectRatio = screenWidth/screenHeight;

        this.viewPortWidth = (float)(2 * Math.tan(FOV_H/2));
        this.viewPortHeight = viewPortWidth * (1/aspectRatio);
    }

    public Vec3d projectPoint(Vec3d vector){
        Vec3d projectedPoint;
        Vec3d point = vector;

        float projX= point.x / point.z;  
        float projY= point.y / point.z;
        float projZ= 1;

        projectedPoint = new Vec3d(projX,projY,projZ);

        return projectedPoint;
    }

    public Coord2d pointToScreenCoord(Vec3d vector){
        Coord2d coordinate = null;

        Vec3d projPoint = projectPoint(vector);

        if(-viewPortWidth/2 <= projPoint.x && projPoint.x <= viewPortWidth/2 && -viewPortHeight/2 <= projPoint.y && projPoint.y <= viewPortHeight/2){
            int finalX = (int)(((projPoint.x + viewPortWidth/2)/viewPortWidth)*screenWidth);
            int finalY = (int)(((projPoint.y + viewPortHeight/2)/viewPortHeight)*screenHeight);

            coordinate = new Coord2d(finalX,finalY);
        }
        return coordinate;
    }

}