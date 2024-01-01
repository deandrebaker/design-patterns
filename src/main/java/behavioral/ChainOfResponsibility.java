package behavioral;

import java.util.ArrayList;
import java.util.List;

public class ChainOfResponsibility {
    public static void main(String[] args) {
        Dialog dialog = new Dialog();
        dialog.wikiPageURL = "https://dialog.com";
        Panel panel = new Panel();
        panel.modalHelpText = "This is the info panel";
        Button ok = new Button();
        ok.tooltipText = "This is an Ok button";
        Button cancel = new Button();
        panel.add(ok);
        panel.add(cancel);
        dialog.add(panel);

        System.out.println("Hovering over Ok button...");
        ok.showHelp();

        System.out.println("Hovering over Cancel button...");
        cancel.showHelp();
    }
}

interface ComponentWithContextualHelp {
    void showHelp();
}

abstract class Component implements ComponentWithContextualHelp {
    String tooltipText;
    protected Container container;

    @Override
    public void showHelp() {
        if (tooltipText != null) {
            System.out.println(this.tooltipText);
        } else {
            this.container.showHelp();
        }
    }
}

abstract class Container extends Component {
    protected List<Component> children;

    public Container() {
        this.children = new ArrayList<>();
    }

    public void add(Component child) {
        this.children.add(child);
        child.container = this;
    }
}

class Button extends Component {
}

class Panel extends Container {
    String modalHelpText;

    @Override
    public void showHelp() {
        if (this.modalHelpText != null) {
            System.out.println(this.modalHelpText);
        } else {
            super.showHelp();
        }
    }
}

class Dialog extends Container {
    String wikiPageURL;

    public void showHelp() {
        if (this.wikiPageURL != null) {
            System.out.printf("Opening wiki page: %s%n", this.wikiPageURL);
        } else {
            super.showHelp();
        }
    }
}