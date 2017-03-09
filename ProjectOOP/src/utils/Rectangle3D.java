package utils;

/**
 * Created by Nhan on 3/6/2017.
 */
public class Rectangle3D {
    private int x;
    private int y;
    private int z;
    private int maxX;
    private int maxY;
    private int maxZ;
    private int width;
    private int height;
    private int depth;

    public Rectangle3D(int x, int y, int z,  int width, int height, int depth) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.maxX = x + width;
        this.maxY = y + height;
        this.maxZ = z + depth;
    }

    public boolean intersect(Rectangle3D rect) {
        return (this.x <= rect.maxX && this.maxX >= rect.x) &&
                (this.y <= rect.maxY && this.maxY >= rect.y) &&
                (this.z <= rect.maxZ && this.maxZ >= rect.z);
    }
}
