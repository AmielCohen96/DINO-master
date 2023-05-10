import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sounds {
    private Clip clip;

    public Sounds(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void play() {
        if (this.clip != null) {
            this.clip.setFramePosition(0);
            this.clip.start();
        }
    }

    public void stop() {
        if (this.clip != null) {
            this.clip.stop();
            this.clip.setFramePosition(0);
        }
    }
}
