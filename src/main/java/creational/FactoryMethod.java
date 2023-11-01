package creational;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FactoryMethod {
    public static void main(String[] args) {
        final Dialog dialog1 = new WindowsDialog();
        final Dialog dialog2 = new HTMLDialog();
        dialog1.render();
        dialog2.render();
    }
}

interface Button {
    void render(JFrame frame);

    void onClick(ActionListener l);
}

class WindowsButton implements Button {
    final JButton button;

    public WindowsButton() {
        this.button = new JButton("Windows Button");
    }

    @Override
    public void render(JFrame frame) {
        frame.add(button);
    }

    @Override
    public void onClick(ActionListener l) {
        button.addActionListener(e -> {
            l.actionPerformed(e);
            System.out.println("Event emitted by Windows Button");
        });
    }
}

class HTMLButton implements Button {
    final JButton button = new JButton("HTML Button");

    @Override
    public void render(JFrame frame) {
        frame.add(button);
    }

    @Override
    public void onClick(ActionListener l) {
        button.addActionListener(e -> {
            l.actionPerformed(e);
            System.out.println("Event emitted by HTML Button");
        });
    }
}

abstract class Dialog {
    public abstract Button createButton();

    public void render() {
        final JFrame frame = new JFrame("Dialog");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Button button = createButton();
        button.onClick(e -> System.out.println("Clicked Windows Button at " + e.getWhen()));
        button.render(frame);

        frame.setVisible(true);
    }
}

class WindowsDialog extends Dialog {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}

class HTMLDialog extends Dialog {
    @Override
    public Button createButton() {
        return new HTMLButton();
    }
}
