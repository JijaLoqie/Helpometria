import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    private final Mediator mediator;

    MenuPanel(Mediator mediator) {
        this.mediator = mediator;

        setConfig();

        initElements();
    }
    private void setConfig() {
        setPreferredSize(new Dimension(300, 300));
        setBackground(new Color(121, 53, 53));
    }

    private void initElements() {
        add(new JButtonPoint(this, mediator));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Command) {
            Command c = (Command)e.getSource();
            c.execute();
        }
    }
}
