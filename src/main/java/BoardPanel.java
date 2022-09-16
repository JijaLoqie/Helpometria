import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashSet;

public class BoardPanel extends JPanel implements MouseListener {
    private final Mediator mediator;
    private Point pressPoint;
    DragListener dragListener = new DragListener();


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
    public DPoint tryFindClosest(Point2D curPoint) {
        for (DPoint point : mediator.figureManager.dpoints) {
            if (point.getPoint().distance(curPoint) <= point.diameter / 2.0) {
                return point;
            }
        }
        return null;
    }
    private class DragListener extends MouseAdapter implements MouseMotionListener {
        Point startPt;
        DPoint pressDP, releaseDP;
        Line2D.Float curLine;

        JComponent comp;
        public void repaint() {
            for (var dline : mediator.figureManager.dlines) {
                mediator.figureManager.draw(dline);
            }
        }
        public void mouseEntered(MouseEvent me) {
            comp = (JComponent) me.getSource();
        }

        public void mouseDragged(MouseEvent me)
        {
            BoardPanel.this.repaint();
            repaint();
            mediator.figureManager.draw(new DLine((Graphics2D) getGraphics(), mediator.figureManager, curLine));
            if (!(comp instanceof DPoint)) {
                return;
            }
            if (!(mediator.figureManager.isDragable())) {
                Point secondEnd = new Point(comp.getX() + me.getX(),comp.getY() + me.getY());
                curLine.setLine(comp.getX() + ((DPoint)comp).diameter / 2.0, comp.getY() + ((DPoint)comp).diameter / 2.0, secondEnd.x, secondEnd.y);
                return;
            }
            comp.setLocation(comp.getX()+me.getX()-startPt.x,comp.getY()+me.getY()-startPt.y);
        }
        public void mousePressed(MouseEvent me)
        {
            startPt = me.getPoint();
            comp = (JComponent)me.getSource();
            if (comp instanceof DPoint) {
                pressDP = (DPoint) comp;
            }
            curLine = new Line2D.Float(comp.getX()+me.getX()-startPt.x,comp.getY()+me.getY()-startPt.y, comp.getX()+me.getX()-startPt.x,comp.getY()+me.getY()-startPt.y);
        }
        public void mouseReleased(MouseEvent me){
            if (comp instanceof DPoint) {
                releaseDP = (DPoint) comp;
            }
            curLine.setLine(curLine.x1, curLine.y1, comp.getX() + me.getX(),comp.getY() + me.getY());
            DPoint lastPoint = tryFindClosest(new Point(comp.getX() + me.getX(),comp.getY() + me.getY()));
            if (lastPoint != null) {
                curLine.setLine(curLine.x1, curLine.y1, lastPoint.x, lastPoint.y);
            }
            if (curLine.x1 == 0 && curLine.y1==0) {
                curLine.setLine(pressPoint.x, pressPoint.y, curLine.x2, curLine.y2);
            }

            if (mediator.figureManager.isPartOfLine()) {
                if (pressDP == null) {
                    pressDP = new DPoint(mediator.figureManager, new Point((int) curLine.x1, (int) curLine.y1));
                }
                if (releaseDP == null) {
                    releaseDP = new DPoint(mediator.figureManager, new Point((int) curLine.x2, (int) curLine.y2));
                }
                System.out.println(pressDP.getPoint() + " TO " + releaseDP.getPoint());
                mediator.figureManager.dlines.add(new DLine((Graphics2D) getGraphics(), pressDP, releaseDP));
                pressDP = null;
                releaseDP = null;
            }
            repaint(); // Был цикл
            for (var dline : mediator.figureManager.dlines) {
                mediator.figureManager.draw(dline);
            }
        }
    }




    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
}
