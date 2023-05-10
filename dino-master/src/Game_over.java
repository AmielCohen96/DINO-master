import javax.swing.*;
import java.awt.*;

public class Game_over extends JPanel  {

    private boolean start;
    private JLabel over;
    private JLabel score;
    private static int tempLevel;
    public Game_over(int x, int y, int width, int height, Window window) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        JButton instructions = new JButton("Game Instructions");
        instructions.setBounds(x + Utils.X_START, y + Utils.Y_INSTRUCTION_BUTTON, Utils.START_WIDTH, Utils.START_HEIGHT);
        instructions.setFont(new Font("Game Instructions", Font.BOLD, 20));
        this.add(instructions);
        instructions.addActionListener(e -> {
            try {
                showMessage(this.GameInstructions());
                this.start = true;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton tryAgain = new JButton("Try again");
        tryAgain.setBounds(x + instructions.getX(), y + Utils.Y_START, Utils.START_WIDTH, Utils.START_HEIGHT);
        tryAgain.setFont(new Font("Try again", Font.BOLD, 20));
        this.add(tryAgain);
        tryAgain.addActionListener(e -> {
            try {
                Scene.initGame();
                window.switchScreen("scene");

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        this.over = new JLabel("GAME  OVER");
        add(this.over);
        this.over.setBounds(x + instructions.getX() - 30,instructions.getY() - 200,Utils.START_WIDTH + 150,Utils.START_HEIGHT);
        this.over.setFont(new Font("David", Font.PLAIN, Utils.FONT_SIZE+10));
        this.over.setVisible(true);
        this.repaint();
        new Thread(()->{
            this.score = new JLabel("YOU  REACH  LEVEL: " + tempLevel);
            add(this.score);
            this.score.setBounds(x + instructions.getX() - 100,over.getY() + 100,Utils.START_WIDTH + 300,Utils.START_HEIGHT);
            this.score.setFont(new Font("David", Font.PLAIN, Utils.FONT_SIZE - 10));
            this.score.setVisible(true);
            while (true){
                requestFocus();
                score.setText("YOU  HAVE  REACHED  LEVEL: " + ((Scene.getCounter()/100)+1));
                this.repaint();
            }
        }).start();
    }




    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Game Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void setTempLevel(int tempLevel) {
        Game_over.tempLevel = tempLevel;
    }

    public String GameInstructions() {
        String instructions;
        instructions = "Welcome to the Dino game\n" +
                "All you have to do is press the start button and the player will start running. \n" +
                "Press the spacebar to jump over the obstacles (like cacti) in your way.\n " +
                "The longer you last, the faster the obstacles will enter the game,\n thus increasing the level of difficulty.\n" +
                "If you collide with one of the cacti, the game will end and you can go back to play again.\n" +
                "Successfully!!!";

        return instructions;
    }

    public void paintBackground(Graphics graphics) {
        ImageIcon imageIcon = new ImageIcon("dino-master/src/picture/170%.jpg");
        imageIcon.paintIcon(this, graphics, 0, 0);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        paintBackground(graphics);
    }

    public void setLevel()
    {
        tempLevel = ((Scene.getCounter()/10)+1);
    }
}

