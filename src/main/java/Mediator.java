import java.awt.*;
import java.util.HashMap;

public class Mediator {
    public FigureManager figureManager;
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
        return mediator;
    }

    private void initMenu() {
        this.menu = new MenuPanel(this);
    }

    public void initBoard() {
        this.board = new BoardPanel(this);
        this.figureManager = new FigureManager(board);
    }

    public void initButton(ButtonCommand button) {
        buttons.put(button.getText(), button);
    }


    public void pressButton(String type) throws Exception {
        if (type.equals("Point")) {
            figureManager.changeStatus(Status.DRAW);
        } else if (type.equals("Drag")) {
            figureManager.changeStatus(Status.DRAG);
        } else if (type.equals("Line")) {
            figureManager.changeStatus(Status.LINE);
        } else {
            throw new Exception("Unknown Action");
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
        if (figureManager.isDrawable()) {
            figureManager.draw(new DPoint(figureManager, pressPoint));
            if (figureManager.isPartOfLine()) { //?
                prevPoint = pressPoint;
            }
        } else if (figureManager.isDragable()) { //?
            prevPoint = pressPoint;
        } else {
            System.out.println("Can't do it :(");
        }
    }

    public void handleRelease(Point releasePoint) {
        //?
    }
}
