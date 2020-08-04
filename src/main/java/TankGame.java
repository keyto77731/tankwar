import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/***
 * 主遊戲類別
 */
public class TankGame {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        GameClient gameClient = new GameClient(1024, 768);
        frame.setTitle("坦克大戰!");
        frame.setResizable(false);
        frame.add(gameClient);
        frame.pack();
        //置中顯示
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gameClient.repaint();

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
