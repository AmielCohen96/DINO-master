import javax.swing.*;
import java.awt.*;

public class Cactus extends Thread {
    private int x;
    private int y;
    private int speed;
    private Scene scene;
    private int width;
    private int height;

    public Cactus(Scene scene, int speed){
        this.x= Utils.X_CACTUS;
        this.y= Utils.Y_CACTUS;
        this.speed = speed;
        this.scene = scene;
        this.width = Utils.CACTUS_WIDTH;
        this.height = Utils.CACTUS_HEIGHT;
    }
    public void run () {
        while (!Scene.getIsGameOver()) {
            this.x -= this.speed;
            Utils.sleep(100);
        }
    }

    public void rest()
    {
        this.x= Utils.X_CACTUS;
        this.y= Utils.Y_CACTUS;
        this.speed = Utils.SPEED;
    }
    public void paint (Graphics graphics) {
        ImageIcon imageIcon = new ImageIcon("dino-master/src/picture/200.png");
        imageIcon.paintIcon(this.scene, graphics, this.x, this.y);
        this.width = imageIcon.getIconWidth();
    }

    public Rectangle creatRect(){
        Rectangle rectangle = new Rectangle(this.x,this.y,this.width-30,this.height-25);
        return rectangle;
    }

}

