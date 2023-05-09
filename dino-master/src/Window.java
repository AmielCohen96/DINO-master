import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public Window() {
        this.setSize(Utils.WIDTH, Utils.HEIGHT);
        this.setLayout(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(this.cardLayout); // Set the layout of the mainPanel to cardLayout
        this.mainPanel.add(new ButtonsScreen(Utils.X_WINDOW, Utils.Y_WINDOW, Utils.WIDTH, Utils.HEIGHT, this), "ButtonsScreen");
        this.mainPanel.add(new Scene(Utils.X_WINDOW, Utils.Y_WINDOW, Utils.WIDTH, Utils.HEIGHT, this), "scene");
        this.mainPanel.add(new Game_over(Utils.X_WINDOW, Utils.Y_WINDOW, Utils.WIDTH, Utils.HEIGHT, this), "Game Over");
        this.getContentPane().add(mainPanel, BorderLayout.CENTER); // Add the mainPanel to the content pane of the Window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void switchScreen (String name) {
        cardLayout.show(mainPanel, name);
    }


}