import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Scene extends JPanel {
    private Player player;
    private static Sound jump;
    private static Sound crash;

    private static ArrayList<Cactus> cactuses;
    private static boolean isGameOver;
    private int speed;
    private static int counter;
    private JLabel score;
    private JLabel level;
    private JLabel highScore;




    public Scene(int x, int y, int width, int height, Window window) {
        isGameOver = true;
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        this.player = new Player(this);
        this.player.start();
//        crash = new Sound(Utils.CRASH);
//        jump = new Sound(Utils.JUMP);
        this.addKeyListener(new Movement(this.player));
        this.setFocusable(true);
        this.requestFocus();
        cactuses = new ArrayList<>();
        this.speed = Utils.SPEED;


        new Thread(() ->
        {
            while (true)
            {
                this.repaint();
                if(!isGameOver) {
                    Random random = new Random();
                    Cactus cactus = new Cactus(this, speed);
                    Utils.sleep(random.nextInt(4500, 7500));
                    cactus.start();
                    this.speed += 2;
                    cactuses.add(cactus);
                }
            }
        }).start();


        new Thread(() ->
        {
            while (true){
                this.repaint();
                if(!isGameOver) {
                    this.score = new JLabel();
                    add(this.score);
                    this.score.setBounds(Utils.X_WINDOW + 75, Utils.Y_WINDOW + 75, Utils.TEXT_WIDTH, Utils.TEXT_HEIGHT);
                    this.score.setFont(new Font("David", Font.PLAIN, Utils.FONT_SIZE));
                    this.score.setVisible(true);
                    this.level = new JLabel();
                    add(this.level);
                    this.level.setBounds(score.getX() + score.getWidth(), score.getY(), Utils.TEXT_WIDTH, Utils.TEXT_HEIGHT);
                    this.level.setFont(new Font("David", Font.PLAIN, Utils.FONT_SIZE));
                    this.level.setVisible(true);
                    this.highScore = new JLabel();
                    add(this.highScore);
                    this.highScore.setBounds(Utils.WIDTH-700, score.getY(), Utils.TEXT_WIDTH + 100, Utils.TEXT_HEIGHT);
                    this.highScore.setFont(new Font("David", Font.PLAIN, Utils.FONT_SIZE));
                    this.highScore.setVisible(true);
                    counter = 0;
                    this.score.setText("SCORE: " + (counter));
                    this.level.setText("LEVEL " + ((counter/100)+1));
                    this.highScore.setText("HIGHEST LEVEL: " + Utils.maxLevel);
                    this.repaint();
                    while (true) {
                        this.repaint();
                        if(this.speed < 50){
                            Utils.sleep((600-(this.speed*10)));
                        }
                        else {
                            Utils.sleep(100);
                        }
                        if (!isGameOver){
                            counter += 1;
                        }
                        this.score.setText("SCORE: " + (counter));
                        this.level.setText("LEVEL " + ((counter/100)+1));
                        this.highScore.setText("HIGHEST LEVEL: " + Utils.maxLevel);
                        this.score.setVisible(true);
                    }
                }
            }

        }).start();

        new Thread(() -> {
            while (true) {
                this.repaint();
                this.requestFocus();
                Utils.sleep(10);
                this.repaint();
                for (Cactus cactus : cactuses) {
                    if(Utils.collision(this.player.creatRect(),cactus.creatRect())){
                        crash.play();
                        window.switchScreen("Game Over");
                        this.gameOver();
                    }
                }
            }

        }).start();

    }


    public void gameOver(){
        System.out.println(counter);
        Game_over.setTempLevel(counter/10+1);
        if((counter/100)+1 > Utils.maxLevel){
            Utils.maxLevel = ((counter/100)+1);
        }
        this.speed = Utils.SPEED;
        isGameOver = true;
        for (Cactus cactus : cactuses){
            cactus.rest();
        }
    }

    public static Sound getJump() {
        return jump;
    }

    public static int getCounter() {
        return counter;
    }

    public static boolean getIsGameOver() {
        return isGameOver;
    }

    public static void initGame() {
        isGameOver = false;
        cactuses.clear();
        counter=0;
    }

    public void paintBackground(Graphics graphics) {
        ImageIcon imageIcon = new ImageIcon("dino-master/src/picture/170%.jpg");
        imageIcon.paintIcon(this, graphics, 0, 0);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        paintBackground(graphics);
        this.player.paint(graphics);
        try {
            for (Cactus cactus : cactuses) {
                cactus.paint(graphics);
                this.repaint();
        }
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}