package creational;

import java.util.ArrayList;
import java.util.List;

public class Prototype {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        Circle circle = new Circle();
        circle.x = 10;
        circle.y = 10;
        circle.radius = 20;
        shapes.add(circle);
        Circle anotherCircle = circle.clone();
        shapes.add(anotherCircle);

        Rectangle rectangle = new Rectangle();
        rectangle.width = 10;
        rectangle.height = 20;
        shapes.add(rectangle);

        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }
}

abstract class Shape {
    int x;
    int y;
    String color;

    public Shape() {
    }

    public Shape(Shape source) {
        this();
        this.x = source.x;
        this.y = source.y;
        this.color = source.color;
    }

    public abstract Shape clone();
}

class Rectangle extends Shape {
    int width;
    int height;

    public Rectangle() {
    }

    public Rectangle(Rectangle source) {
        super(source);
        this.width = source.width;
        this.height = source.height;
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + this.width +
                ", height=" + this.height +
                ", x=" + this.x +
                ", y=" + this.y +
                ", color='" + this.color + '\'' +
                '}';
    }
}

class Circle extends Shape {
    int radius;

    public Circle() {
    }

    public Circle(Circle source) {
        super(source);
        this.radius = source.radius;
    }

    @Override
    public Circle clone() {
        return new Circle(this);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + this.radius +
                ", x=" + this.x +
                ", y=" + this.y +
                ", color='" + this.color + '\'' +
                '}';
    }
}
