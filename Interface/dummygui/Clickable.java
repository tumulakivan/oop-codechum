package dummygui;

public interface Clickable {
    public void click();
}

interface Resizable {
    public void resize(int newWidth, int newHeight);
    public void resize(int multiplier);
}

class Checkbox implements Clickable {
    private boolean isChecked;
    private String text;

    public Checkbox(String text) {
        this.text = text;
    }

    public void click() {
        if(!this.isChecked) {
            this.isChecked = true;
            System.out.println("Checkbox is checked");
        } else {
            this.isChecked = false;
            System.out.println("Checkbox is unchecked");
        }
    }

    @Override
    public String toString() {
        if(!this.isChecked)
            return "Checkbox " + "(" + this.text + " - Checked = false)";
        return "Checkbox " + "(" + this.text + " - Checked = true)";
    }
}

abstract class AbstractButton implements Clickable, Resizable{
    protected String title;
    protected int width;
    protected int height;
    protected boolean isClicked;

    public AbstractButton(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.isClicked = false;
    }

    public abstract String getTitle();
    public abstract int getWidth();
    public abstract int getHeight();
}

class NormalButton extends AbstractButton {
    public NormalButton(String title, int width, int height) {
        super(title, width, height);
    }

    public void click() {
        if(!this.isClicked) {
            this.isClicked = true;
            System.out.println("Button is clicked");
        } else {
            this.isClicked = false;
            System.out.println("Button is not clicked anymore");
        }
    }

    public void resize(int newWidth, int newHeight) {
        this.width = newWidth;
        this.height = newHeight;
    }

    public void resize(int multiplier) {
        this.width *= multiplier;
        this.height *= multiplier;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        return "NormalButton ((" + this.width + "px, " + this.height + "px) - Clicked = " + String.valueOf(this.isClicked) + ")";
    }
}