import java.awt.*;
import java.util.HashMap;

public class Mediator {
    static private AbstractFigureManager abstractFigureManager;
    private BoardPanel board;
    private MenuPanel menu;
    HashMap<String, ButtonCommand> buttons = new HashMap<>();

    Point prevPoint;

    private Mediator() {
    }

    public static Mediator createInstance() {
        Mediator mediator = new Mediator();
        mediator.initBoard();
        mediator.initMenu();
        abstractFigureManager = new AbstractFigureManager(mediator.board);
        return mediator;
    }

    private void initMenu() {
        this.menu = new MenuPanel(this);
    }

    public void initBoard() {
        this.board = new BoardPanel(this);
    }

    public void initButton(ButtonCommand button) {
        buttons.put(button.getText(), button);
    }


    public void pressButton(String type) throws Exception {
        FigureManager figure = abstractFigureManager.getFigureManager(type);
        if (figure != null) {
            figure.setDrawable(!figure.isDrawable());
            figure.setDragable(false);
            abstractFigureManager.noDrawableExcept(type);
        } else if (type.equals("Drag")) {
            abstractFigureManager.setAllDragable();
            abstractFigureManager.noDrawable();
        } else {
            throw new Exception("Unknown figure");
        }
        for (String buttonType : buttons.keySet()) {
            if (buttonType.equals(type)) {
                buttons.get(buttonType).switchState();
            } else {
                buttons.get(buttonType).switchOff();
            }
        }

    }

    public BoardPanel getBoard() {
        return board;
    }

    public MenuPanel getMenu() {
        return menu;
    }

    public void handlePress(Point pressPoint) {
        FigureManager figure = abstractFigureManager.getFigureManager("Point");
        if (figure.isDrawable()) {
            figure.draw(pressPoint.x, pressPoint.y);
        } else if (figure.isDragable()) {
            prevPoint = pressPoint;
        } else {
            System.out.println("Can't do it :(");
        }
    }

}
