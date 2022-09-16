import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

class DPoint extends DFigure implements MouseListener {
    public int x, y;
    public int diameter = 40;
    public FigureManager figureManager;

    public DPoint(FigureManager figureManager) {
        this(figureManager, 0, 0);
    }

    public DPoint(FigureManager figureManager, Point point) {
        this(figureManager, point.x, point.y);
    }
    public DPoint(FigureManager figureManager, int x, int y) {
        this.x = x;
        this.y = y;
        this.figureManager = figureManager;
        addMouseMotionListener(figureManager.boardPanel.dragListener);
        addMouseListener(figureManager.boardPanel.dragListener);
        this.addMouseListener(this);
        setBounds((int) (x - diameter / 2), (int) (y - diameter / 2), (int) diameter, (int) diameter);
    }

    @Override
    public void paint(Graphics gr) {
        gr.setColor(color);
        gr.fillOval(0, 0, (int) diameter, (int) diameter);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!figureManager.isDragable()) {
            return;
        }
        color = Color.red;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!figureManager.isDragable()) {
            return;
        }
        color = Color.green;
        repaint();
    }

    public Point2D getPoint() {
        return new Point2D.Float(x, y);
    }


    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}