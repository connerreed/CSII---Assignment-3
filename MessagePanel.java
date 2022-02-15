import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(200, 30, 60, 20);

        int[] xCoordinates = {200, 200, 170, 130, 130, 330, 330, 290, 260, 260};
        int[] yCoordinates = {40, 100, 130, 130, 350, 350, 130, 130, 100, 40};
        int numPoints = xCoordinates.length;
        g.drawPolyline(xCoordinates, yCoordinates, numPoints);




        g.drawString("Help! I'm trapped in CS II!", 165, 240);

    }
}
