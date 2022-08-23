import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

public class BoardPanel extends JPanel implements MouseListener {
    private final Mediator mediator;

    BoardPanel(Mediator mediator) {
        this.mediator = mediator;
        mediator.registerBoard(this);

        setConfig();

        initElements();

    }

    private void setConfig() {
        this.addMouseListener(this);
        this.setPreferredSize(new Dimension(200, 200));
        this.setBackground(Color.BLACK);
    }

    private void initElements() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("CLICK");
        Point point = e.getPoint();
        mediator.drawPoint(point.x - MyPoint.size / 2, point.y - MyPoint.size / 2);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
