import javax.swing.*;
import java.awt.*;

public class SierpinskiFrame extends JFrame {
    public SierpinskiFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sierpinski Triangles");

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        setSize(screenSize.width, screenSize.height);

        add(new SierpinskiPanel());
        setVisible(true);
    }
}
