package creational;

public class Builder {
    public static void main(String[] args) {
        final Director director = new Director();
        final CarBuilder carBuilder = new CarBuilder();
        final ManualBuilder manualBuilder = new ManualBuilder();

        director.constructSUV(carBuilder);
        director.constructSUV(manualBuilder);
        Car car = carBuilder.getProduct();
        System.out.println(car);
        Manual manual = manualBuilder.getProduct();
        System.out.println(manual);

        director.constructCabriolet(carBuilder);
        car = carBuilder.getProduct();
        System.out.println(car);

        System.out.println();

        director.constructSportsCar(manualBuilder);
        manual = manualBuilder.getProduct();
        System.out.println(manual);
    }
}

enum Engine {
    SPORTS_CAR("Sports Car"),
    SUV("SUV"),
    CABRIOLET("Cabriolet");

    public final String label;

    Engine(String label) {
        this.label = label;
    }
}

class Car {
    public boolean gps;
    public boolean tripComputer;
    public int seats;
    public Engine engine;

    @Override
    public String toString() {
        return String.format("""
                Car {
                \tgps: %s,
                \ttripComputer: %s,
                \tseats: %s,
                \tengine: %s,
                }""", this.gps, this.tripComputer, this.seats, this.engine);
    }
}

class Manual {
    public StringBuilder sb;

    public Manual() {
        this.sb = new StringBuilder();
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

interface AutomobileBuilder {
    void reset();

    void setSeats(int seats);

    void setEngine(Engine engine);

    void setTripComputer(boolean tripComputer);

    void setGPS(boolean gps);
}

class CarBuilder implements AutomobileBuilder {
    private Car car;

    public CarBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.car = new Car();
    }

    @Override
    public void setSeats(int seats) {
        this.car.seats = seats;
    }

    @Override
    public void setEngine(Engine engine) {
        this.car.engine = engine;
    }

    @Override
    public void setTripComputer(boolean tripComputer) {
        this.car.tripComputer = tripComputer;
    }

    @Override
    public void setGPS(boolean gps) {
        this.car.gps = gps;
    }

    public Car getProduct() {
        final Car product = this.car;
        this.reset();
        return product;
    }
}

class ManualBuilder implements AutomobileBuilder {
    private Manual manual;

    public ManualBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.manual = new Manual();
    }

    @Override
    public void setSeats(int seats) {
        this.manual.sb.append(String.format("This car has %d seats. ", seats));
    }

    @Override
    public void setEngine(Engine engine) {
        this.manual.sb.append(String.format("This car has an %s engine. ", engine.label));
    }

    @Override
    public void setTripComputer(boolean tripComputer) {
        this.manual.sb.append(String.format("This car %s a trip computer. ", tripComputer ? "has" : "does not have"));
    }

    @Override
    public void setGPS(boolean gps) {
        this.manual.sb.append(String.format("This car %s a GPS. ", gps ? "has" : "does not have"));
    }

    public Manual getProduct() {
        final Manual product = this.manual;
        this.reset();
        return product;
    }
}

class Director {
    public void constructSportsCar(AutomobileBuilder builder) {
        builder.reset();
        builder.setSeats(2);
        builder.setEngine(Engine.SPORTS_CAR);
        builder.setTripComputer(true);
        builder.setGPS(false);
    }

    public void constructSUV(AutomobileBuilder builder) {
        builder.reset();
        builder.setSeats(5);
        builder.setEngine(Engine.SUV);
        builder.setTripComputer(true);
        builder.setGPS(true);
    }

    public void constructCabriolet(AutomobileBuilder builder) {
        builder.reset();
        builder.setSeats(4);
        builder.setEngine(Engine.CABRIOLET);
        builder.setTripComputer(false);
        builder.setGPS(true);
    }
}
