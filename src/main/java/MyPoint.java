import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.HashSet;

public class MyPoint extends FigureManager {
    static public float diameter;
    static public Color color = Color.green;
    HashSet<Figure> figures = new HashSet<>();
    public void setColor(Color color) {
        this.color = color;
    }
    class Figure extends JLabel implements MouseListener {
        public MyPoint getManager() {
            return manager;
        }
        public int x, y;
        MyPoint manager;
        private Point prevPoint;

        public Figure(MyPoint manager) {
            this(manager, 0, 0);
        }
        public Figure(MyPoint manager, int x, int y) {
            this.manager = manager;
            this.x = x;
            this.y = y;
            addMouseMotionListener(boardPanel.dragListener);
            addMouseListener(boardPanel.dragListener);
            this.addMouseListener(this);
            setBounds((int)(x - diameter/2), (int)(y - diameter/2), (int)diameter, (int)diameter);
        }
        @Override
        public void paint(Graphics gr) {
            gr.setColor(color);
            gr.fillOval(0,0, (int)diameter, (int)diameter);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }


        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (!isDragable()) {
                return;
            }
            color = Color.red;
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!isDragable()) {
                return;
            }
            color = Color.green;
            repaint();
        }

        public Point2D getPoint() {
            return new Point2D.Float(x,y);
        }
    }
    public MyPoint(BoardPanel board) {
        super(board);
        color = Color.green;
        diameter = 40;
    }
    public MyPoint(BoardPanel board, Color color) {
        this(board);
        this.color = color;
    }
    public MyPoint(BoardPanel board, int diameter) {
        this(board);
        this.diameter = diameter;
    }

    @Override
    public void draw(float x, float y) {
//        g.setColor(color);
//        g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
        Figure figure = new Figure(this, (int)x, (int)y);
        figures.add(figure);
        boardPanel.add(figure);
        figure.repaint();
    }
    @Override
    public void draw(float x, float y, Color color) {
        setColor(color);
        draw(x, y);
        setColor(Color.green);
    }

    private Figure getClosestFigure(Point realPoint, Point vector) {
        for (Figure figure : figures) {
            if (figure.getPoint().distance(realPoint) <= MyPoint.diameter / 2) {
                return figure;
            }
        }
        return null;
    }
}
