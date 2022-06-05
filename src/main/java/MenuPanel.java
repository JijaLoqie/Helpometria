import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    private JButton newTriangle;
    private JButton newPoint;
    private Application _parent;

    public MenuPanel(Application parent) {
        _parent = parent;
        setConfig();
    }

    private void setConfig() {
        setLayout(new FlowLayout());
        this.setBackground(new Color(40, 56, 61));
        this.setPreferredSize(new Dimension(Application.PREF_W/2, Application.PREF_H));

        newTriangle = new JButton("+ TRIANGLE");
        newTriangle.addActionListener(this);
        add(newTriangle);
        newPoint = new JButton("+ POINT");
        newPoint.addActionListener(this);
        add(newPoint);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.startsWith("+")) {
            _parent.addObject(command.substring(2));
        }
    }
}
