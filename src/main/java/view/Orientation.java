package view;

public class Orientation {
    private double angle;

    public Orientation() {
        this.angle = Math.random();
    }

    public Orientation(double angle) {
        this.angle = angle;
    }

    public double getValue() {
        return this.angle;
    }

    public void setValue(double newValue) {
        this.angle = newValue;
    }

    public Boolean equals(Orientation orientation) {
        return this.angle == orientation.angle;
    }
}
