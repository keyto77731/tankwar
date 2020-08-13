import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected Image[] image;

    protected int width;
    protected int height;
    protected int oldX;
    protected int oldY;
    protected boolean alive;
    protected int frame;
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }


    public GameObject(int x, int y, Image[] image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.alive=true;
        width = image[0].getWidth(null);
        height = image[0].getHeight(null);
    }

    public abstract void draw(Graphics g);
}
