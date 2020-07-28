import javax.swing.*;

public class TankWar {

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.add(new GameClient(20,20));
        frame.setTitle("©Z§J¤j¾Ô");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }
}
