import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Scene extends JPanel {
    private Player player;
    private static ArrayList<Cactus> cactuses;
    public static boolean isGameOver;
    private int speed;
    private int counter;
    private JLabel score;




    public Scene(int x, int y, int width, int height, Window window) {
        isGameOver = true;
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        this.player = new Player(this);
        this.player.start();
        this.addKeyListener(new Movement(this.player));
        this.setFocusable(true);
        this.requestFocus();
        this.cactuses = new ArrayList<>();
        this.speed = Utils.SPEED;
        this.counter = 0;

        new Thread(() ->
        {
            while (true)
            {
                this.repaint();
                if(!isGameOver) {
                    Random random = new Random();
                    Cactus cactus = new Cactus(this, speed);
                    System.out.println("cactus created");
                    Utils.sleep(random.nextInt(4500, 7500));
                    cactus.start();
                    this.speed += 2;
                    this.cactuses.add(cactus);
                }
//                else {
//                    this.restGame();
//                }
            }
        }).start();


        new Thread(() ->
        {
            while (true){
                this.repaint();
                if(!isGameOver) {
                    score = new JLabel();
                    add(score);
                    score.setBounds(Utils.X_WINDOW + 75, Utils.Y_WINDOW + 75, 200, 50);
                    score.setFont(new Font("David", Font.PLAIN, 40));
                    score.setVisible(true);
                    this.counter = 0;
                    score.setText("SCORE: " + (counter));
                    this.repaint();
                    while (true) {
                        this.repaint();
                        if(this.speed < 50){
                            Utils.sleep((600-(this.speed*10)));
                        }
                        else {
                            Utils.sleep(100);
                        }
                        this.counter += 1;
                        score.setText("SCORE: " + (counter));
                        score.setVisible(true);
                    }
                }
//                else{
//                    this.restGame();
//                }
            }

        }).start();

        new Thread(() -> {
            while (true) {
                this.repaint();
                this.requestFocus();
                Utils.sleep(10);
                this.repaint();
                for (Cactus cactus : this.cactuses) {
                    if(Utils.collision(this.player.creatRect(),cactus.creatRect())){
//                        System.out.println("Crash");
                        this.gameOver();
                        window.switchScreen("Game Over");
                    }
                }
            }
        }).start();
    }


    public void gameOver(){
        this.speed = Utils.SPEED;
        isGameOver = true;
        this.counter = 0;
        for (Cactus cactus : cactuses){
            cactus.rest();
        }
    }

//    public void restGame(){
//        this.isGameOver = true;
//        System.out.println("Game Over");
//        this.counter = 0;
//        this.speed = Utils.SPEED;
//        cactuses.clear();
//
//    }

    public static void initGame() {
        isGameOver = false;
    }

    public void paintBackground(Graphics graphics) {
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\Downloads\\dino-master\\dino-master\\src\\picture\\170%.jpg");
        imageIcon.paintIcon(this, graphics, 0, 0);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        paintBackground(graphics);
        this.player.paint(graphics);
        for (Cactus cactus : this.cactuses) {
            cactus.paint(graphics);
            this.repaint();
        }
    }
}