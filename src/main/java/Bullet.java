import java.awt.*;

public class Bullet extends MoveObject {

    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
    }




    public void collision() {
        if (collisionBound()) {
            alive = false;
            return;
        }




        for (GameObject object : TankGame.gameClient.getGameObjects()) {
            if (object != this) {


                if (object instanceof Tank) {
                    if (((Tank) object).enemy == enemy) {
                        continue;
                    }
                }
                if (object.getRectangle().intersects(this.getRectangle())) {
                    alive = false;
                    if (object instanceof Tank) {
                        object.alive = false;
                    }

                    return;
                }
            }
        }
    }
}