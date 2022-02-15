import javax.swing.*;
import java.awt.*;

public class SierpinskiPanel extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int length = 0;

        if(getWidth() < getHeight()) {
            length = getWidth();
        } else {
            length = getHeight();
        }
        draw(0, 0, length, g);
    }

    public void draw(int x, int y, int length, Graphics g) {
        if(length == 1)
            g.drawRect(x, y, 1, 1);
        else {
            draw(x + (length / 4), y, length / 2, g); // tip of triangle
            draw(x, y + (length / 2), length / 2, g); // bottom left of triangle
            draw(x + (length / 2), y + (length / 2),  length / 2, g); // bottom right of triangle
        }
    }
}
