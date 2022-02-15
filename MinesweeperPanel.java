import javax.swing.*;
import java.awt.*;

public class MinesweeperPanel extends JPanel {
    MinesweeperModel grid;

    public MinesweeperPanel() {
        grid = new MinesweeperModel();
        setLayout(new GridLayout(MinesweeperModel.NUM_ROWS, MinesweeperModel.NUM_ROWS));
        for(int i = 0; i < MinesweeperModel.NUM_ROWS; i++) {
            for(int j = 0; j < MinesweeperModel.NUM_ROWS; j++) {
                add(new MinesweeperJButton(i, j, grid));
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

}
