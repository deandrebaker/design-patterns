package behavioral;

public class TemplateMethod {
    public static void main(String[] args) {
        GameAI orcs = new OrcsAI();
        GameAI monsters = new MonstersAI();
        System.out.println("Orcs turn");
        orcs.turn();
        System.out.println("\nMonsters turn");
        monsters.turn();
        System.out.println("\nOrcs turn");
        orcs.turn();
        System.out.println("\nMonsters turn");
        monsters.turn();
    }
}

abstract class GameAI {
    public void turn() {
        collectResources();
        buildStructures();
        buildUnits();
        attack();
    }

    void collectResources() {
        System.out.println("Collecting resources");
    }

    abstract void buildStructures();

    abstract void buildUnits();

    void attack() {
        if (Math.random() < 0.5) {
            sendScouts((int) (Math.random() * 100));
        } else {
            sendWarriors((int) (Math.random() * 100));
        }
    }

    abstract void sendScouts(int position);

    abstract void sendWarriors(int position);
}

class OrcsAI extends GameAI {
    @Override
    void buildStructures() {
        System.out.println("Building farms, barracks, and stronghold...");
    }

    @Override
    void buildUnits() {
        if (Math.random() < 0.5) {
            System.out.println("Building peon scouts...");
        } else {
            System.out.println("Building grunt warriors...");
        }
    }

    @Override
    void sendScouts(int position) {
        System.out.println("Sending scouts to " + position + "...");
    }

    @Override
    void sendWarriors(int position) {
        System.out.println("Sending warriors to " + position + "...");
    }
}

class MonstersAI extends GameAI {
    @Override
    void collectResources() {

    }

    @Override
    void buildStructures() {

    }

    @Override
    void buildUnits() {

    }

    @Override
    void sendScouts(int position) {
        System.out.println("Sending monsters to position " + position + "...");
    }

    @Override
    void sendWarriors(int position) {
        System.out.println("Sending monsters to position " + position + "...");
    }
}