import java.util.HashMap;

public class AbstractFigureManager {
    private HashMap<String, FigureManager> figureManagers;
    private BoardPanel board;
    public AbstractFigureManager(BoardPanel board) {
        figureManagers = new HashMap<>();
        this.board = board;
        figureManagers.put("Point", new MyPoint(board));
    }

    public FigureManager getFigureManager(String type) {
        if (!figureManagers.containsKey(type)) {
            figureManagers.put(type, null);
        }
        return figureManagers.get(type);
    }

    /**
     * Set field 'drawable' false for all figures except one with 'type' key
     */
    public void noDrawableExcept(String type) {
        for (var el :
                figureManagers.keySet()) {
            if (el.equals(type)) {
                continue;
            }
            if (figureManagers.get(el) != null) {
                figureManagers.get(el).setDrawable(false);
                figureManagers.get(el).setDragable(false);
            }
        }
    }
    public void noDrawable() {
        for (var el :
                figureManagers.keySet()) {
            if (figureManagers.get(el) != null) {
                figureManagers.get(el).setDrawable(false);
            }
        }
    }

    public void setAllDragable() {
        for (var el :
                figureManagers.keySet()) {
            if (figureManagers.get(el) != null) {
                figureManagers.get(el).setDragable(!figureManagers.get(el).isDragable());
            }
        }
    }
}
