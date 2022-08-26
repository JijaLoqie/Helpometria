import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Mediator {
    private JButtonPoint buttonPoint;
    private DragButton buttonDrag;
    private BoardPanel board;
    private HashMap<String, ShapeManager> figures = new HashMap<>();
    private HashMap<String, JButton> buttons = new HashMap<>();
    HashMap<String, Boolean> activeButton = new HashMap<>();

    public void registerPoint(JButtonPoint buttonPoint) {
        this.buttonPoint = buttonPoint;
        figures.put("Point", new MyPoint());
        figures.get("Point").registerGraphics(board.getGraphics());
        buttons.put("Point", buttonPoint);
    }
    public void registerDrag(DragButton buttonDrag) {
        this.buttonDrag = buttonDrag;
        buttons.put("Drag", buttonDrag);
    }
    public boolean isRegistered(String type) {
        return (activeButton.containsKey(type) && activeButton.get(type));
    }
    public void registerBoard(BoardPanel board) {
        this.board = board;
    }
    public void drawPoint(Float xx, Float yy) {
        figures.get("Point").registerGraphics(board.getGraphics());
        figures.get("Point").draw(xx, yy);
    }
    public void drawPoint(Float xx, Float yy, Color color) {
        figures.get("Point").draw(xx, yy, color);
    }

    public void switchDrawable(String type) {
        if (activeButton.containsKey(type) && activeButton.get(type).equals(true)) {
            activeButton.put(type, false);
            buttons.get(type).setBackground(Color.white);
            buttons.get(type).setForeground(Color.BLACK);
        } else {
            activeButton.put(type, true);
            buttons.get(type).setBackground(Color.RED);
            buttons.get(type).setForeground(Color.WHITE);
        }
    }
}
