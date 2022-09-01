import java.awt.*;

public abstract class FigureManager {
    protected boolean drawable = false;
    protected boolean dragable = false;
    BoardPanel boardPanel;
    FigureManager(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }
    public boolean isDrawable() {
        return drawable;
    }
    public boolean isDragable() {
        return dragable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }
    public void setDragable(boolean dragable) {
        this.dragable = dragable;
    }

    abstract public void draw(float x, float y);
    abstract public void draw(float x, float y, Color color);
}