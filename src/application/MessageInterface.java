package application;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MessageInterface {
    private String name;
    private JFrame frame;
    private JTextArea textArea;

    public MessageInterface(String name) {
        this.name = name;
        frame = new JFrame(name);
        textArea = new JTextArea(10, 30);

        textArea.setEnabled(false);
        frame.add(new JScrollPane(textArea));
        frame.setSize(400, 700);
        frame.setVisible(true);
    }

    public void addText(String message) {
        textArea.append("[" + name + "]: " + message + "\n");
    }

    public void close() {
        frame.dispose();
    }
}
