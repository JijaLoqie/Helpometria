import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class DLine extends DFigure {
    DPoint dp1, dp2;
    Line2D line;
    Graphics2D g2d;
    DLine(Graphics2D g2d, DPoint dp1, DPoint dp2) {
        this.g2d = g2d;
        g2d.setColor(color);
        this.dp1 = dp1;
        this.dp2 = dp2;
    }
    DLine(Graphics2D g2d, FigureManager figureManager, Line2D  line) {
        this.g2d = g2d;
        dp1 = new DPoint(figureManager, (int) line.getX1(), (int) line.getY1());
        dp2 = new DPoint(figureManager, (int) line.getX2(), (int) line.getY2());
        g2d.setColor(color);
    }

    DLine(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.setColor(color);
    }


    public void paintLine() {
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine((int)dp1.getX() + dp1.diameter/2, (int)dp1.getY() + dp1.diameter/2, (int)dp2.getX() + dp1.diameter/2, (int)dp2.getY() + dp1.diameter/2);
    }

    public void setLine(double x1, double y1, int x2, int y2) {
        line.setLine(x1, y1, x2, y2);
    }
}
