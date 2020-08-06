import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/***
 * 坦克大戰主程式
 */
public class TankGame {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GameClient gameClient = new GameClient(1024, 768);
        frame.add(gameClient);
        frame.setTitle("TankWar");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();


        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameClient.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                gameClient.keyReleased(e);
            }
        });
    }
}
