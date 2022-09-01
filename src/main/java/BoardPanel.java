import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.HashSet;

public class BoardPanel extends JPanel implements MouseListener {
    private final Mediator mediator;
    public static HashSet<Point2D.Float> points = new HashSet<>();
    private Point pressPoint;
    DragListener dragListener = new DragListener();

    MyPoint closestPoint;

    BoardPanel(Mediator mediator) {
        this.mediator = mediator;

        setConfig();

        initElements();

    }

    private void setConfig() {
        this.addMouseListener(this);
        this.setPreferredSize(new Dimension(200, 200));
        this.setBackground(Color.BLACK);
    }

    private void initElements() {
        addMouseMotionListener(dragListener);
        addMouseListener(dragListener);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("CLICK");
        pressPoint = e.getPoint();
        mediator.handlePress(pressPoint);
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
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    private class DragListener extends MouseAdapter implements MouseMotionListener {
        Point startPt;
        JComponent comp;
        public void mouseMoved(MouseEvent me){}
        public void mouseDragged(MouseEvent me)
        {
            if (!(comp instanceof MyPoint.Figure)) {
                return;
            }
            if (!((MyPoint.Figure) comp).getManager().isDragable()) {
                return;
            }
            comp.setLocation(comp.getX()+me.getX()-startPt.x,comp.getY()+me.getY()-startPt.y);
        }
        public void mousePressed(MouseEvent me)
        {
            startPt = me.getPoint();
            comp = (JComponent)me.getSource();
        }
    }
}
