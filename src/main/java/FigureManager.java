import java.awt.*;
import java.util.HashSet;

public class FigureManager {
    Status status = Status.NOTHING;
    HashSet<DPoint> dpoints = new HashSet<>();
    HashSet<DLine> dlines = new HashSet<>();
    public BoardPanel boardPanel;
    FigureManager(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }
    public boolean isDrawable() {
        return status.equals(Status.DRAW);
    }
    public boolean isDragable() {
        return status.equals(Status.DRAG);
    }
    public boolean isPartOfLine() {
        return status.equals(Status.LINE);
    }

    public void changeStatus(Status newStatus) {
        if (newStatus.equals(status)) {
            status = Status.NOTHING;
        } else {
            status = newStatus;
        }
    }

    public void draw(DPoint dpoint) {
        dpoints.add(dpoint);
        boardPanel.add(dpoint);
        dpoint.repaint();
    }
    public void draw(DLine dline) {
        dline.paintLine();
    }
}