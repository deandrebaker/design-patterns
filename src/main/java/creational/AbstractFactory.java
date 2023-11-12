package creational;

import javax.swing.*;
import java.awt.*;

public class AbstractFactory {
    public static void main(String[] args) {
        final Factory factory1 = new WinFactory();
        final Factory factory2 = new MacFactory();
        final Frame frame1 = new Frame(factory1);
        final Frame frame2 = new Frame(factory2);
        frame1.setVisible(true);
        frame2.setVisible(true);
    }
}

interface Label {
    void paint(JFrame frame);
}

class WinLabel implements Label {
    private final JLabel Label;

    public WinLabel() {
        this.Label = new JLabel("Windows Label");
    }

    @Override
    public void paint(JFrame frame) {
        frame.add(this.Label);
    }
}

class MacLabel implements Label {
    private final JLabel Label;

    public MacLabel() {
        this.Label = new JLabel("Mac Label");
    }

    @Override
    public void paint(JFrame frame) {
        frame.add(this.Label);
    }
}

interface Checkbox {
    void paint(JFrame frame);
}

class WinCheckbox implements Checkbox {
    private final JCheckBox checkbox;

    public WinCheckbox() {
        this.checkbox = new JCheckBox("Windows Checkbox");
    }

    @Override
    public void paint(JFrame frame) {
        frame.add(this.checkbox);
    }
}

class MacCheckbox implements Checkbox {
    private final JCheckBox checkbox;

    public MacCheckbox() {
        this.checkbox = new JCheckBox("Mac Checkbox");
    }

    @Override
    public void paint(JFrame frame) {
        frame.add(this.checkbox);
    }
}

interface Factory {
    Label createLabel();

    Checkbox createCheckbox();
}

class WinFactory implements Factory {
    @Override
    public Label createLabel() {
        return new WinLabel();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}

class MacFactory implements Factory {
    @Override
    public Label createLabel() {
        return new MacLabel();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

class Frame {
    private final JFrame frame;

    public Frame(Factory factory) {
        this.frame = new JFrame("Frame");
        this.frame.setSize(500, 500);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(new GridLayout(0, 2));

        final Label label = factory.createLabel();
        final Checkbox checkbox = factory.createCheckbox();

        label.paint(this.frame);
        checkbox.paint(this.frame);
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}