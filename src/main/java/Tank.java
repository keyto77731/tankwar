import javax.swing.*;
import java.awt.*;

/***
 * 坦克物件
 */

public class Tank extends GameObject {

    private int speed;
    private Direction direction;
    //上下左右
    private boolean[] dirs = new boolean[4];
    private boolean enemy;


    public Tank(int x, int y, Direction direction, Image[] image) {
        this(x, y, direction, false, image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {
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

    public void collision() {
        if (x < 0) {
            x = 0;
        } else if (x > TankGame.gameClient.getWidth() - width) {
            x = TankGame.gameClient.getWidth() - width;
        }
        if (y < 0) {
            y = 0;
        } else if (y > TankGame.gameClient.getHeight() - height) {
            y = TankGame.gameClient.getHeight() - height;
        }

        for (GameObject object : TankGame.gameClient.getGameObjects())
            if (object != this) {
                if (object.getRectangle().intersects(this.getRectangle())) {
                    x = oldX;
                    y = oldY;
                    return;
                }
            }
    }


    private void determineDirection() {
        //0:上 1:下 2:左 3:右
        if (dirs[0] && !dirs[1] && !dirs[2] && !dirs[3]) {
            direction = Direction.UP;
        } else if (!dirs[0] && dirs[1] && !dirs[2] && !dirs[3]) {
            direction = Direction.DOWN;
        } else if (!dirs[0] && !dirs[1] && dirs[2] && !dirs[3]) {
            direction = Direction.LEFT;
        } else if (!dirs[0] && !dirs[1] && !dirs[2] && dirs[3]) {
            direction = Direction.RIGHT;
        } else if (dirs[0] && !dirs[1] && dirs[2] && !dirs[3]) {
            direction = Direction.UP_LEFT;
        } else if (dirs[0] && !dirs[1] && !dirs[2] && dirs[3]) {
            direction = Direction.UP_RIGHT;
        } else if (!dirs[0] && dirs[1] && dirs[2] && !dirs[3]) {
            direction = Direction.DOWN_LEFT;
        } else if (!dirs[0] && dirs[1] && !dirs[2] && dirs[3]) {
            direction = Direction.DOWN_RIGHT;
        }
    }


    public void draw(Graphics g) {
        if (!isStop()) {
            determineDirection();
            move();
            collision();
        }

        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    private boolean isStop() {
        for (boolean dir : dirs) {
            if (dir) {
                return false;
            }
        }
        return true;
    }

    public boolean[] getDirs() {
        return dirs;
    }
}
