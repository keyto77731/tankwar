import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameClient extends JComponent {

    private int screenWidth;
    private int screenHeight;

    private Tank playerTank;

    private CopyOnWriteArrayList<GameObject> gameObjects = new CopyOnWriteArrayList<>();

    private boolean stop;
    public static Image[] bulletImg = new Image[8];
    public static Image[] explosionImg = new Image[11];
    GameClient() {
        this(1024, 768);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
    public void addGameObject(GameObject object){
        gameObjects.add(object);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        init();

        new Thread(() -> {
            while (!stop) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void init() {

        Image[] iTankImg = new Image[8];
        Image[] eTankImg = new Image[8];
        Image[] brickImage = {Tools.getImage("brick.png")};


        String[] subName = {"U.png", "D.png", "L.png", "R.png", "LU.png", "RU.png", "LD.png", "RD.png"};

        for (int i = 0; i < iTankImg.length; i++) {
            iTankImg[i] = Tools.getImage("itank" + subName[i]);
            eTankImg[i] = Tools.getImage("etank" + subName[i]);
            bulletImg[i] = Tools.getImage("missile" + subName[i]);
        }
        for (int i = 0; i < explosionImg.length; i++)
        explosionImg[i] = Tools.getImage(i+".png");

        playerTank = new Tank(getCenterPosX(47), 100, Direction.DOWN, iTankImg);

        gameObjects.add(playerTank);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                gameObjects.add(new Tank(300 + j * 120, 400 + i * 120, Direction.UP, true, eTankImg));
            }
        }

        gameObjects.add(new Wall(250, 150, true, 15, brickImage));
        gameObjects.add(new Wall(150, 200, false, 15, brickImage));
        gameObjects.add(new Wall(800, 200, false, 15, brickImage));

    }


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());

        for (GameObject object : gameObjects) {
            object.draw(g);
        }
        for (GameObject object : gameObjects) {
            if(!object.alive)
            gameObjects.remove(object);
        }
    }

    private int getCenterPosX(int width) {
        return (screenWidth - width) / 2;
    }

    private int getCenterPosY(int height) {
        return (screenHeight - height) / 2;
    }


    public void keyPressed(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = true;
                break;

            case KeyEvent.VK_LEFT:
                dirs[2] = true;
                break;

            case KeyEvent.VK_RIGHT:
                dirs[3] = true;
                break;
            case KeyEvent.VK_CONTROL:
                playerTank.fire();
                break;
//            case KeyEvent.VK_A:
//                playerTank.superFire();
//                break;

            default:
        }
    }





    public void keyReleased(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = false;
                break;

            case KeyEvent.VK_LEFT:
                dirs[2] = false;
                break;

            case KeyEvent.VK_RIGHT:
                dirs[3] = false;
                break;

            default:
        }
    }
}
