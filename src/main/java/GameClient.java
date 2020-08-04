import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameClient extends JComponent {
    //遊戲畫面的寬跟長
    private int screenWidth;
    private int screenHeight;
    //玩家坦克
    private Tank playerTank;


    private boolean stop;
    List<Tank> enemyTank=new ArrayList<>();
    List<Wall> walls=new ArrayList<>();
    //預設遊戲畫面
    GameClient() {
        this(800, 600);
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
        playerTank = new Tank(400, 100, Direction.DOWN);
        for(int i=0;i<3;i++)
            for(int j=0;j<4;j++)
                enemyTank.add(new Tank(300+j*80,500+i*80,Direction.UP,true));
        walls.add(new Wall(250,150,15,true));
        walls.add(new Wall(150,200,15,false));
        walls.add(new Wall(800,200,15,false));
    }

//老師做的↓
    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
//老師做的↑

    @Override
    public void paintComponent(Graphics g) {
        playerTank.draw(g);
        for(Tank tank:enemyTank)
            tank.draw(g);
        for(Wall wall:walls)
            wall.draw(g);
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
        }
    }

}
