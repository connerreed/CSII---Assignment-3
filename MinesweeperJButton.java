import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MinesweeperJButton extends JButton implements MouseListener {
    private JButton currSquare;
    private int row;
    private int column;
    private MinesweeperModel grid;

    public MinesweeperJButton(int row, int column, MinesweeperModel grid) {
        this.row = row;
        this.column = column;
        this.grid = grid;
        this.setText("?");

        this.addMouseListener(this);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)) {
            System.out.println("You left clicked at " + "(" + row + ", " + column + ")");
        }
        if(SwingUtilities.isRightMouseButton(e)) {
            System.out.println("You right clicked at " + "(" + row + ", " + column + ")");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
