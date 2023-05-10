
import javax.sound.sampled.Clip;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener {
    private Player player;
    private Clip clip;

    public Movement(Player player){
        this.player= player;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//            Scene.getJump().play();
            this.player.setJump(true);
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
