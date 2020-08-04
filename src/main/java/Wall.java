import javax.swing.*;
import java.awt.*;

public class Wall extends GameObject{
    private int bricks;
    private boolean horizontal;

    public Wall(int x, int y, int bricks, boolean horizontal,Image image) {
        super(x,y,image);
        this.bricks = bricks;
        this.horizontal = horizontal;

        image=Tools.getImage("brick.png");
    }
    public void draw(Graphics g){
        if(horizontal){
            for(int i=0;i<bricks;i++){
                g.drawImage(image,x+i*image.getWidth(null),y,null);
            }
        }else{
            for(int i=0;i<bricks;i++){
                g.drawImage(image,x,y+i*image.getHeight(null),null);
            }

        }
    }
}
