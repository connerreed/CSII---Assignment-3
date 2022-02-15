import javax.swing.*;


public class MinesweeperFrame extends JFrame {
    public MinesweeperFrame() {
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new MinesweeperPanel());
        setVisible(true);
    }

}
