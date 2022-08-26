import javax.swing.*;
import java.awt.event.ActionListener;

public class DragButton extends JButton implements Command {
    Mediator mediator;

    public DragButton(ActionListener actionListener, Mediator mediator) {
        super("DRAG");
        addActionListener(actionListener);
        this.mediator = mediator;
        mediator.registerDrag(this);

    }
    @Override
    public void execute() {
        mediator.switchDrawable("Drag");
    }
}
