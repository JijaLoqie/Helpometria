import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;

public class MyPoint implements ShapeManager {
    static public float diameter;
    Graphics g;
    private Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public MyPoint() {
        color = Color.green;
        diameter = 10;
    }
    public MyPoint(Color color) {
        this();
        this.color = color;
    }
    public MyPoint(int diameter) {
        this();
        this.diameter = diameter;
    }
    @Override
    public void registerGraphics(Graphics g) {
        this.g = g;
    }

    @Override
    public void draw(float x, float y) {
        g.setColor(color);
        g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
    }
    @Override
    public void draw(float x, float y, Color color) {
        setColor(color);
        draw(x, y);
        setColor(Color.green);
    }
}
