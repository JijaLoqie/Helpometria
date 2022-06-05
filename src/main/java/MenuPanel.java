import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    static JButton newTriangle;
    static JButton newPoint;
    public MenuPanel() {
        setConfig();
    }

    private void setConfig() {
        setLayout(new FlowLayout());
        this.setBackground(new Color(40, 56, 61));
        this.setPreferredSize(new Dimension(Application.PREF_W/2, Application.PREF_H));

        newTriangle = new JButton("+ TRIANGLE");
        add(newTriangle);
        newPoint = new JButton("+ POINT");
        add(newPoint);

    }
}
