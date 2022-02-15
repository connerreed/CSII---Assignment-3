import javax.swing.*;

public class MessageFrame extends JFrame {
    public MessageFrame() {
        setTitle("Message in a bottle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        add(new MessagePanel());
        setVisible(true);
    }
}
