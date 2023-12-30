package structural;

public class Adapter {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
        RoundPeg rPeg = new RoundPeg(5);
        System.out.printf("%s fits in %s: %s%n", rPeg, hole, hole.fits(rPeg));

        SquarePeg smSqPeg = new SquarePeg(5);
        SquarePeg lgSqPeg = new SquarePeg(10);

        SquarePegAdapter smSqPegAdapter = new SquarePegAdapter(smSqPeg);
        SquarePegAdapter lgSqPegAdapter = new SquarePegAdapter(lgSqPeg);
        System.out.printf("%s fits in %s: %s%n", smSqPegAdapter, hole, hole.fits(smSqPegAdapter));
        System.out.printf("%s fits in %s: %s%n", lgSqPegAdapter, hole, hole.fits(lgSqPegAdapter));
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

    @Override
    public String toString() {
        return String.format("RoundPeg(radius = %f)", this.getRadius());
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

    @Override
    public String toString() {
        return String.format("SquarePeg(width = %f)", this.getWidth());
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

    @Override
    public String toString() {
        return String.format("SquarePegAdapter(radius = %f)", this.getRadius());
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

    @Override
    public String toString() {
        return String.format("RoundHole(radius = %f)", this.getRadius());
    }
}

