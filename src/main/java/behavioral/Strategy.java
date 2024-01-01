package behavioral;

public class Strategy {
    public static void main(String[] args) {
        double a = 5;
        double b = 32.3;
        System.out.printf("a = %s, b = %s%n", a, b);

        ArithmeticContext ctx = new ArithmeticContext();

        System.out.println("Using multiplication strategy");
        ArithmeticStrategy strategy = new MultipleStrategy();
        ctx.setStrategy(strategy);
        System.out.println(ctx.executeStrategy(a, b));

        System.out.println("Using subtraction strategy");
        strategy = new SubtractStrategy();
        ctx.setStrategy(strategy);
        System.out.println(ctx.executeStrategy(a, b));

    }
}

interface ArithmeticStrategy {
    double execute(double a, double b);
}

class AddStrategy implements ArithmeticStrategy {
    @Override
    public double execute(double a, double b) {
        return a + b;
    }
}

class SubtractStrategy implements ArithmeticStrategy {
    @Override
    public double execute(double a, double b) {
        return a - b;
    }
}

class MultipleStrategy implements ArithmeticStrategy {
    @Override
    public double execute(double a, double b) {
        return a * b;
    }
}

class ArithmeticContext {
    ArithmeticStrategy strategy;

    public void setStrategy(ArithmeticStrategy strategy) {
        this.strategy = strategy;
    }

    public double executeStrategy(double a, double b) {
        return this.strategy.execute(a, b);
    }
}