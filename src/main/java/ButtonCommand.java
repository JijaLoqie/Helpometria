import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonCommand extends JButton {
    Mediator mediator;
    boolean enabled = false;
    public ButtonCommand(String type, ActionListener actionListener, Mediator mediator) {
        super.setText(type);
        addActionListener(actionListener);
        this.mediator = mediator;
        switchOff();
        mediator.initButton(this);
    }
    void execute() {
        try {
            mediator.pressButton(getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void switchState() {
        if (enabled) {
            switchOff();
        } else {
            switchOn();
        }

    }

    public void switchOff() {
        System.out.println("DISABLING " + getText());
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        enabled = false;
    }

    public void switchOn() {
        System.out.println("ENABLING " + getText());
        setBackground(Color.RED);
        setForeground(Color.WHITE);
        enabled = true;
    }
}
