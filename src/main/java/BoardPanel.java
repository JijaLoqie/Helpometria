import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.util.HashSet;

public class BoardPanel extends JPanel implements MouseListener {
    private final Mediator mediator;
    public static HashSet<Point2D.Float> points = new HashSet<>();
    private Point clickPoint;

    MyPoint closestPoint;

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
        clickPoint = e.getPoint();
        if (mediator.isRegistered("Point")) {
            points.add(new Point2D.Float(clickPoint.x - MyPoint.diameter / 2, clickPoint.y - MyPoint.diameter / 2));
            repaint();
        } else if (mediator.isRegistered("Drag")) {
            Point2D.Float closestPoint = tryFindClosest(e.getPoint());
            if (closestPoint == null) {
                return;
            }
            mediator.drawPoint(closestPoint.x, closestPoint.y, Color.red);
        } else {
            System.out.println("CAN'T DO IT");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }
    public Point2D.Float tryFindClosest(Point2D curPoint) {
        for (Point2D.Float point : points) {
            if (point.distance(curPoint) <= MyPoint.diameter / 2) {
                return point;
            }
        }
        return null;
    }

    @Override
    public void repaint() {
        for (Point2D.Float el : points) {
            mediator.drawPoint(el.x, el.y);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            Point curPoint = e.getPoint();
        }
    }
}
