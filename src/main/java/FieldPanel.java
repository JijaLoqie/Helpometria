import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class FieldPanel extends JPanel {
    LinkedList<Point2D.Float> points;
    public FieldPanel() {
        setConfig();
    }

    private void setConfig() {
        this.setBackground(new Color(0, 0, 0));
        this.setLayout(new BorderLayout());
        points = new LinkedList<>();
    }

    public void addTriangle() {

    }
    public void addPoint() {
        Point2D.Float newPoint = new Point2D.Float(0, 0);
        points.add(newPoint);
    }
// TODO: Каждый раз перерисовывать поле - плохо. Надо решить этот вопрос
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graph2 = (Graphics2D) g;
        graph2.setPaint(Color.BLUE);
        for (Point2D.Float point : points) {
            graph2.fillOval((int) point.x, (int) point.y, 10, 10);
        }
        repaint();
    }
}
