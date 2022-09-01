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
        add(new ButtonCommand("Point", this, mediator));
        add(new ButtonCommand("Drag", this, mediator));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof ButtonCommand) {
            ButtonCommand c = (ButtonCommand)e.getSource();
            c.execute();
        }
    }
}
