import java.awt.*;

/***
 * ©Z§Jª«¥ó
 */

public abstract class MoveObject extends GameObject {

    protected int speed;
    protected Direction direction;


    protected boolean enemy;


    public MoveObject(int x, int y, Direction direction, Image[] image) {
        this(x, y, direction, false, image);
    }

    public MoveObject(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, image);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.enemy = enemy;
        speed = 10;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void move() {
        oldX = x;
        oldY = y;
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP_LEFT:
                y -= speed;
                x -= speed;
                break;
            case UP_RIGHT:
                y -= speed;
                x += speed;
                break;
            case DOWN_LEFT:
                x -= speed;
                y += speed;
                break;
            case DOWN_RIGHT:
                x += speed;
                y += speed;
                break;
        }
    }

    public boolean collisionBound() {
        boolean collision = false;
        if (x < 0) {
            x = 0;
            collision = true;
        } else if (x > TankGame.gameClient.getWidth() - width) {
            x = TankGame.gameClient.getWidth() - width;
            collision = true;
        }
        if (y < 0) {
            y = 0;
            collision = true;
        } else if (y > TankGame.gameClient.getHeight() - height) {
            y = TankGame.gameClient.getHeight() - height;
            collision = true;
        }
        return collision;
    }

    public abstract void collision();


    public void draw(Graphics g) {
        if (!alive) {
            return;
        }
        move();
        collision();


        g.drawImage(image[direction.ordinal()], x, y, null);
    }


}
