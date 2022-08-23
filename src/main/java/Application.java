import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    Mediator mediator = new Mediator();

    BoardPanel boardPanel;
    MenuPanel menuPanel;
    public Application() {
        setConfig();

        initElements();

        setVisible(true);
    }

    private void setConfig() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice screen = env.getDefaultScreenDevice();
        GraphicsConfiguration config = screen.getDefaultConfiguration();

        int screenHeight = config.getBounds().height;
        int screenWidth = config.getBounds().width;

        setLayout(new BorderLayout());
        setSize(new Dimension(screenWidth, screenHeight));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Helpometrika");
    }

    private void initElements() {

        boardPanel = new BoardPanel(mediator);
        getContentPane().add(boardPanel, BorderLayout.CENTER);

        menuPanel = new MenuPanel(mediator);
        getContentPane().add(menuPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        new Application();
    }
}
