import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JButtonPoint extends JButton implements Command{
    Mediator mediator;

    public JButtonPoint(ActionListener actionListener, Mediator mediator) {
        super("POINT");
        addActionListener(actionListener);
        this.mediator = mediator;
        mediator.registerPoint(this);

    }
    @Override
    public void execute() {
        mediator.switchDrawable("Point");
    }

}
