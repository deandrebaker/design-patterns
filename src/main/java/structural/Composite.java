package structural;

import java.util.ArrayList;
import java.util.List;

public class Composite {
    public static void main(String[] args) {
        CompoundGraphic outerBox = new CompoundGraphic();
        Dot dot = new Dot(1, 2);
        outerBox.add(dot);
        outerBox.add(new Circle(5, 3, 10));
        CompoundGraphic innerBox = new CompoundGraphic();
        innerBox.add(new Circle(8, 2, 10));
        outerBox.add(innerBox);
        outerBox.draw();

        System.out.println("Remove dot...");
        outerBox.remove(dot);
        outerBox.draw();
    }
}

interface Graphic {
    void move(int x, int y);

    void draw();
}

class Dot implements Graphic {
    int x, y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void draw() {
        System.out.printf("Dot(x=%s, y=%s)%n", this.x, this.y);
    }
}

class Circle extends Dot {
    int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.printf("Circle(x=%s, y=%s, radius=%s)%n", this.x, this.y, this.radius);
    }
}

class CompoundGraphic implements Graphic {
    List<Graphic> children;

    public CompoundGraphic() {
        this.children = new ArrayList<>();
    }

    public void add(Graphic child) {
        children.add(child);
    }

    public void remove(Graphic child) {
        children.remove(child);
    }

    @Override
    public void move(int x, int y) {
        for (Graphic child : children) {
            child.move(x, y);
        }
    }

    @Override
    public void draw() {
        System.out.printf("Graphic {%n");
        for (Graphic child : children) {
            child.draw();
        }
        System.out.println("}");
    }
}