import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    public static final int PREF_W = 600;
    public static final int PREF_H = 300;

    private FieldPanel field;
    private MenuPanel menu;
    public Application() {
        setConfig();
    }

    private void setConfig() {
        setSize(new Dimension(PREF_W, PREF_H));
        field = new FieldPanel();
        menu = new MenuPanel();
        field.add(menu, BorderLayout.EAST);

        this.getContentPane().add(field);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }
}
