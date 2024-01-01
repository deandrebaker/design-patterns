package behavioral;

import java.util.ArrayList;
import java.util.List;

public class Visitor {
    public static void main(String[] args) {
        Shape dot = new Dot(0, 5);
        Shape circle = new Circle(5, 5, 3);
        List<Shape> shapes1 = new ArrayList<>();
        shapes1.add(dot);
        shapes1.add(circle);
        Shape compound1 = new CompoundShape(shapes1);
        Shape rect = new Rectangle(6, 3, 9, 10);
        List<Shape> shapes2 = new ArrayList<>();
        shapes2.add(compound1);
        shapes2.add(rect);
        Shape compound2 = new CompoundShape(shapes2);

        ShapeVisitor v = new PrintVisitor();
        compound2.accept(v);

    }
}

interface Shape {
    void move(int x, int y);

    void draw();

    void accept(ShapeVisitor v);
}

class Dot implements Shape {
    int x;
    int y;

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
        System.out.println(this);
    }

    @Override
    public void accept(ShapeVisitor v) {
        v.visitDot(this);
    }

    @Override
    public String toString() {
        return "Dot{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Circle extends Dot {
    int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void accept(ShapeVisitor v) {
        v.visitCircle(this);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

class Rectangle extends Dot {
    int width;
    int height;

    public Rectangle(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void accept(ShapeVisitor v) {
        v.visitRectangle(this);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

class CompoundShape implements Shape {
    List<Shape> children;

    public CompoundShape(List<Shape> children) {
        this.children = children;
    }

    @Override
    public void move(int x, int y) {
        for (Shape shape : this.children) {
            shape.move(x, y);
        }
    }

    @Override
    public void draw() {
        System.out.println(this);
    }

    @Override
    public void accept(ShapeVisitor v) {
        v.visitCompoundShape(this);
    }

    @Override
    public String toString() {
        return "CompoundShape{" +
                "childrenSize=" + children.size() +
                '}';
    }
}

interface ShapeVisitor {
    void visitDot(Dot dot);

    void visitCircle(Circle circle);

    void visitRectangle(Rectangle rectangle);

    void visitCompoundShape(CompoundShape cs);
}

class PrintVisitor implements ShapeVisitor {
    @Override
    public void visitDot(Dot dot) {
        dot.draw();
    }

    @Override
    public void visitCircle(Circle circle) {
        circle.draw();
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
        rectangle.draw();
    }

    @Override
    public void visitCompoundShape(CompoundShape cs) {
        cs.draw();
        System.out.println("[");
        for (Shape shape : cs.children) {
            shape.accept(this);
        }
        System.out.println("]");
    }
}