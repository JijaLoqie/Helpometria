import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class Mediator {
    private JButtonPoint buttonPoint;
    private BoardPanel board;
    private HashMap<String, Shape> figures = new HashMap<>();
    private HashMap<String, Boolean> drawable = new HashMap<>();

    public void registerPoint(JButtonPoint buttonPoint) {
        this.buttonPoint = buttonPoint;
        figures.put("Point", new MyPoint());
    }
    public void registerBoard(BoardPanel board) {
        this.board = board;
    }
    public void drawPoint(Integer xx, Integer yy) {
        System.out.println("DRAWING A POINT");
        Random rnd = new Random();
        System.out.println("COORDS: " + xx + " / " + yy);
        if (drawable.containsKey("Point") && drawable.get("Point")) {
            figures.get("Point").draw(board.getGraphics(),
                    xx, yy, Color.GREEN);
        }
    }

    public void setDrawable(String type) {
        System.out.println("Setting drawable...");
        drawable.put(type, true);
    }
}
