import java.awt.*;

public class Bullet extends MoveObject {

    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
    }




    public boolean collision() {
        if (collisionBound()) {
            alive = false;
            return true;
        }




        for (GameObject object : TankGame.gameClient.getGameObjects()) {
            if (!(object instanceof Bullet ) ){


                if (object instanceof Tank) {
                    if (((Tank) object).enemy == enemy) {
                        continue;
                    }
                }
                if (object.getRectangle().intersects(this.getRectangle())) {
                    TankGame.gameClient.addGameObject(new Explosion(x,y,GameClient.explosionImg));
                    alive = false;
                    if (object instanceof Tank) {
                        object.alive = false;
                    }

                    return true;
                }
            }
        }
        return false;
    }
}