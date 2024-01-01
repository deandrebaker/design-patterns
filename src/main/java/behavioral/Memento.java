package behavioral;

public class Memento {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        textEditor.text = "Hello world!";
        textEditor.curX = 0;
        textEditor.curY = 3;
        textEditor.selectionWidth = 5;

        System.out.println(textEditor);
        System.out.println("Creating backup...");
        Snapshot backup = textEditor.createSnapshot();
        textEditor.curX = 100;
        textEditor.curY = 200;
        textEditor.selectionWidth = 5000;
        textEditor.text = "Lorem ipsum";
        System.out.println(textEditor);
        System.out.println("Restoring from backup...");
        backup.restore();
        System.out.println(textEditor);
    }
}

class TextEditor {
    String text;
    int curX;
    int curY;
    int selectionWidth;

    public void setText(String text) {
        this.text = text;
    }

    public void setCursor(int x, int y) {
        this.curX = x;
        this.curY = y;
    }

    public void setSelectionWidth(int width) {
        this.selectionWidth = width;
    }

    public Snapshot createSnapshot() {
        return new Snapshot(this, this.text, this.curX, this.curY, this.selectionWidth);
    }

    @Override
    public String toString() {
        return "TextEditor{" +
                "text='" + text + '\'' +
                ", curX=" + curX +
                ", curY=" + curY +
                ", selectionWidth=" + selectionWidth +
                '}';
    }
}

class Snapshot {
    TextEditor textEditor;
    String text;
    int curX;
    int curY;
    int selectionWidth;

    public Snapshot(TextEditor textEditor, String text, int curX, int curY, int selectionWidth) {
        this.textEditor = textEditor;
        this.text = text;
        this.curX = curX;
        this.curY = curY;
        this.selectionWidth = selectionWidth;
    }

    public void restore() {
        this.textEditor.setText(this.text);
        this.textEditor.setCursor(this.curX, this.curY);
        this.textEditor.setSelectionWidth(this.selectionWidth);
    }
}