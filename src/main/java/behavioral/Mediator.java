package behavioral;

public class Mediator {
    public static void main(String[] args) {
        IMediator mediator = new AuthenticationDialog();
        UICheckbox loginRegisterCheckbox = new UICheckbox(mediator);
        mediator.setCheckbox(loginRegisterCheckbox);
        UITextbox title = new UITextbox(mediator);
        title.text = "Log in";
        mediator.setTextbox(title);
        UIButton okButton = new UIButton(mediator);
        mediator.setButton(okButton);

        mediator.print();
        loginRegisterCheckbox.check();
        mediator.print();
        okButton.click();
        mediator.print();
    }
}

interface IMediator {
    void notify(UIComponent sender, String event);

    void setCheckbox(UICheckbox checkbox);

    void setTextbox(UITextbox textbox);

    void setButton(UIButton button);

    void print();
}

class AuthenticationDialog implements IMediator {
    public UICheckbox loginRegisterCheckbox;
    public UIButton okButton;
    public UITextbox title;

    @Override
    public void setCheckbox(UICheckbox checkbox) {
        this.loginRegisterCheckbox = checkbox;
    }

    @Override
    public void setTextbox(UITextbox textbox) {
        this.title = textbox;
    }

    @Override
    public void setButton(UIButton button) {
        this.okButton = button;
    }

    @Override
    public void notify(UIComponent sender, String event) {
        if (sender == this.loginRegisterCheckbox && event.equals("check")) {
            if (!this.loginRegisterCheckbox.checked) {
                this.title.text = "Log in";
            } else {
                this.title.text = "Register";
            }
        }

        if (sender == this.okButton && event.equals("click")) {
            if (!this.loginRegisterCheckbox.checked) {
                this.title.text = "Logged In!";
            } else {
                this.title.text = "Registered";
            }
        }
    }


    @Override
    public void print() {
        System.out.printf("Title: %s%n", this.title.text);
    }

}

class UIComponent {
    IMediator dialog;

    public UIComponent(IMediator dialog) {
        this.dialog = dialog;
    }

    void click() {
        this.dialog.notify(this, "click");
    }
}

class UIButton extends UIComponent {
    public UIButton(IMediator dialog) {
        super(dialog);
    }
}

class UICheckbox extends UIComponent {
    boolean checked = false;

    public UICheckbox(IMediator dialog) {
        super(dialog);
    }

    public void check() {
        this.checked = !this.checked;
        this.dialog.notify(this, "check");
    }
}

class UITextbox extends UIComponent {
    String text;

    public UITextbox(IMediator dialog) {
        super(dialog);
    }
}