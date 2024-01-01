package behavioral;

import java.util.ArrayList;
import java.util.List;

public class Command {
    public static void main(String[] args) {
        Application app = new Application();
        Editor editor = new Editor();
        AbstractCommand copy = new CopyCommand(app, editor);
        AbstractCommand cut = new CutCommand(app, editor);
        AbstractCommand paste = new PasteCommand(app, editor);
        AbstractCommand undo = new UndoCommand(app, editor);

        editor.text = "Hello world!";
        System.out.printf("Text: \"%s\"%n", editor.text);
        System.out.println("Selected \"world\"");
        editor.selectionStart = editor.text.indexOf("world");
        editor.selectionEnd = editor.selectionStart + "world".length();
        System.out.println("Cutting");
        app.executeCommand(cut);
        System.out.printf("Text: \"%s\"%n", editor.text);
        System.out.println("undoing");
        app.undo();
        System.out.printf("Text: \"%s\"%n", editor.text);


    }
}

class Editor {
    String text = "";
    int selectionStart = 0;
    int selectionEnd = 0;

    public String getSelection() {
        return this.text.substring(this.selectionStart, this.selectionEnd);
    }

    public void deleteSelection() {
        this.text = this.text.substring(0, this.selectionStart) + this.text.substring(this.selectionEnd + 1);
    }

    public void replaceSelection(String text) {
        this.text = this.text.substring(0, this.selectionStart) + text + this.text.substring(this.selectionEnd + 1);
    }
}

abstract class AbstractCommand {
    Application app;
    Editor editor;
    String backup;

    public AbstractCommand(Application app, Editor editor) {
        this.app = app;
        this.editor = editor;
    }

    public void saveBackup() {
        this.backup = this.editor.text;
    }

    public void undo() {
        this.editor.text = this.backup;
    }

    abstract boolean execute();
}

class CopyCommand extends AbstractCommand {
    public CopyCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    public boolean execute() {
        this.app.clipboard = this.editor.getSelection();
        return false;
    }
}

class CutCommand extends AbstractCommand {
    public CutCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    boolean execute() {
        this.saveBackup();
        this.app.clipboard = this.editor.getSelection();
        this.editor.deleteSelection();
        return true;
    }
}

class PasteCommand extends AbstractCommand {
    public PasteCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    boolean execute() {
        this.saveBackup();
        this.editor.replaceSelection(this.app.clipboard);
        return true;
    }
}

class UndoCommand extends AbstractCommand {
    public UndoCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    boolean execute() {
        this.app.undo();
        return false;
    }
}

class CommandHistory {
    List<AbstractCommand> history;

    public CommandHistory() {
        this.history = new ArrayList<>();
    }

    public void push(AbstractCommand c) {
        this.history.add(c);
    }

    public AbstractCommand pop() {
        return this.history.removeLast();
    }
}

class Application {
    String clipboard;
    CommandHistory history;

    public Application() {
        this.history = new CommandHistory();
    }

    public void executeCommand(AbstractCommand command) {
        if (command.execute()) {
            this.history.push(command);
        }
    }

    public void undo() {
        AbstractCommand command = this.history.pop();
        if (command != null) {
            command.undo();
        }
    }
}





