import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application extends JFrame {
    public static final int PREF_W = 600;
    public static final int PREF_H = 300;

    private FieldPanel field;
    private MenuPanel menu;
    public Application() {

        setConfig();
    }

    /***
     * Set configs of the field.
     */
    private void setConfig() {
        setSize(new Dimension(PREF_W, PREF_H));
        field = new FieldPanel();
        menu = new MenuPanel(this);
        field.add(menu, BorderLayout.EAST);

        this.getContentPane().add(field);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

    /***
     * Add an object to the field.
     * @param object
     */
    public void addObject(String object) {
        System.out.println(object);

        if (object.equals("POINT")) {
            field.addPoint();
        } else if (object.equals("TRIANGLE")) {
            field.addTriangle();
        }

    }
}
