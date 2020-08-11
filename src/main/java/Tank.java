import java.awt.*;

/***
 * 坦克物件
 */

public class Tank extends MoveObject {


    //上下左右
    protected boolean[] dirs = new boolean[4];



    public Tank(int x, int y, Direction direction, Image[] image) {
        this(x, y, direction, false, image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y,  direction,  enemy,  image);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.enemy = enemy;
        speed = 10;
    }






    public void collision() {
        collisionBound();

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
    public void fire(){
        TankGame.gameClient.addGameObject(new Bullet(x,y,direction,enemy,TankGame.gameClient.bulletImg));

    }
}
