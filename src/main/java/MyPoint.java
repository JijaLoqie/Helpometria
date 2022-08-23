import java.awt.*;

public class MyPoint implements Shape {
    static public int size;
    public MyPoint() {
        size = 20;
    }
    public MyPoint(int _size) {
        size = _size;
    }
    @Override
    public void draw(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }
}
