import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashSet;

public class BoardPanel extends JPanel implements MouseListener {
    private final Mediator mediator;
    public static HashSet<Point> points = new HashSet<>();
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
        System.out.println("PRESS");
        pressPoint = e.getPoint();
        mediator.handlePress(pressPoint);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("RELEASE");
        mediator.handleRelease(e.getPoint());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }
    public Point tryFindClosest(Point2D curPoint) {
        for (Point point : points) {
            if (point.distance(curPoint) <= MyPoint.diameter / 2) {
                return point;
            }
        }
        return null;
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private class DragListener extends MouseAdapter implements MouseMotionListener {
        Point startPt;
        Line2D.Float curLine;
        HashSet<Line2D.Float> lines = new HashSet<>();

        JComponent comp;
        public void mouseReleased(MouseEvent me){
            Graphics2D g2d = (Graphics2D) getGraphics();
            g2d.setColor(Color.GREEN);
            Point lastPoint = tryFindClosest(new Point(comp.getX() + me.getX(),comp.getY() + me.getY()));
            if (lastPoint != null) {
                System.out.println("WAOOSODOASDOOAD");
                curLine.setLine(curLine.x1, curLine.y1, lastPoint.x, lastPoint.y);
            }
            for (var line : lines) {
                g2d.drawLine((int) line.x1, (int) line.y1, (int) line.x2, (int) line.y2);
            }
        }
        public void mouseDragged(MouseEvent me)
        {
            if (!(comp instanceof MyPoint.Figure)) {
                return;
            }
            if (!((MyPoint.Figure) comp).getManager().isDragable()) {
                Point secondEnd = new Point(comp.getX() + me.getX(),comp.getY() + me.getY());
                curLine.setLine(comp.getX() + MyPoint.diameter / 2, comp.getY() + MyPoint.diameter / 2, secondEnd.x, secondEnd.y);
                repaint();
                return;
            }
            comp.setLocation(comp.getX()+me.getX()-startPt.x,comp.getY()+me.getY()-startPt.y);
        }
        public void repaint() {
            BoardPanel.this.repaint();
            Graphics2D g2d = (Graphics2D) getGraphics();
            g2d.setColor(Color.GREEN);
            for (var line : lines) {
                g2d.drawLine((int) line.x1, (int) line.y1, (int) line.x2, (int) line.y2);
            }
        }
        public void mousePressed(MouseEvent me)
        {
            startPt = me.getPoint();
            comp = (JComponent)me.getSource();

            curLine = new Line2D.Float(comp.getX()+me.getX()-startPt.x,comp.getY()+me.getY()-startPt.y, comp.getX()+me.getX()-startPt.x,comp.getY()+me.getY()-startPt.y);
            lines.add(curLine);
        }
    }
}
