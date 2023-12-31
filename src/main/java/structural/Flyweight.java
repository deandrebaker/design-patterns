package structural;

import java.util.ArrayList;
import java.util.List;

public class Flyweight {
    public static void main(String[] args) {
        Forest forest = new Forest();

        forest.plantTree(0, 0, "Maple", "Brown", "Smooth");
        forest.plantTree(8, 4, "Spruce", "Black", "Smooth");
        forest.plantTree(2, -5, "Maple", "Brown", "Smooth");

        forest.draw();
    }
}

class TreeType {
    final String name;
    final String color;
    final String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }
}

class Tree {
    final int x;
    final int y;
    final TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw() {
        System.out.printf("Tree(x=%s, y=%s, name=%s, color=%s, texture=%s)%n", this.x, this.y, this.type.name, this.type.color, this.type.texture);
    }
}


class TreeFactory {
    final List<TreeType> types;

    public TreeFactory() {
        this.types = new ArrayList<>();
    }

    public TreeType getTreeType(String name, String color, String texture) {
        for (TreeType type : types) {
            if (type.name.equals(name) && type.color.equals(color) && type.texture.equals(texture)) {
                return type;
            }
        }
        TreeType type = new TreeType(name, color, texture);
        types.add(type);
        System.out.printf("Added %s tree type%n", name);
        return type;
    }
}

class Forest {
    final List<Tree> trees;
    final TreeFactory tf;

    public Forest() {
        this.trees = new ArrayList<>();
        this.tf = new TreeFactory();
    }

    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeType type = this.tf.getTreeType(name, color, texture);
        Tree tree = new Tree(x, y, type);
        this.trees.add(tree);
    }

    public void draw() {
        for (Tree tree :
                this.trees) {
            tree.draw();
        }
    }
}