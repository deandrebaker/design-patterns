package structural;

public class Adapter {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
        RoundPeg rpeg = new RoundPeg(5);
        System.out.println(hole.fits(rpeg));

        SquarePeg smSqPeg = new SquarePeg(5);
        SquarePeg lgSqPeg = new SquarePeg(10);

        SquarePegAdapter smSqPegAdapter = new SquarePegAdapter(smSqPeg);
        SquarePegAdapter lgSqPegAdapter = new SquarePegAdapter(lgSqPeg);
        System.out.println(hole.fits(smSqPegAdapter));
        System.out.println(hole.fits(lgSqPegAdapter));
    }

}

class RoundPeg {
    final double radius;

    public RoundPeg(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }
}

class SquarePeg {
    final double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return this.width;
    }
}

class SquarePegAdapter extends RoundPeg {
    final SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        super(0);
        this.peg = peg;
    }

    @Override
    public double getRadius() {
        return this.peg.getWidth() * Math.sqrt(2) / 2;
    }
}

class RoundHole {
    final double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    public boolean fits(RoundPeg peg) {
        return this.getRadius() >= peg.getRadius();
    }
}

