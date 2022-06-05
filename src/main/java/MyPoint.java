import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class MyPoint extends JPanel {
    private Point2D.Float _point;
    public MyPoint() {
        _point = new Point2D.Float(0, 0);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graph2 = (Graphics2D) g;
        graph2.setPaint(Color.BLUE);
        graph2.fillOval((int) _point.x, (int) _point.y, 10, 10);
        repaint();
    }
}
