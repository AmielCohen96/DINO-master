import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Thread {


    private int x;
    private int y;
    private Scene scene;
    public static boolean jump;
    private int width;
    private int height;

    public Player(Scene scene) {
        this.x = Utils.X_PLAYER;
        this.y = Utils.Y_PLAYER;
        this.scene = scene;
        jump = false;
        this.width = Utils.PLAYER_WIDTH;
        this.height = Utils.PLAYER_HEIGHT;
    }


    public void run() {
        while (true) {
            if (jump) {
                this.jumping();
            }
            Utils.sleep(10);
        }
    }

    public void jumping() {
        this.y = Utils.PLAYER_JUMP;
        Utils.sleep(2500);
        this.y = Utils.Y_PLAYER;
        jump = false;
    }

    public void setJump(boolean value) {
        jump = value;
    }


    public Rectangle creatRect() {
        Rectangle rectangle = new Rectangle(this.x, this.y, this.width-25, this.height-25);
        return rectangle;
    }


    public void paint(Graphics graphics) {
        ImageIcon imageIcon = new ImageIcon("dino-master/src/picture/IMG_6988 (1).GIF");
        imageIcon.paintIcon(this.scene, graphics, this.x, this.y);

    }

}