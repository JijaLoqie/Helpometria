import java.awt.*;

public interface ShapeManager {
    void draw(float x, float y);
    void draw(float x, float y, Color color);
    public void registerGraphics(Graphics g);

}