import java.awt.*;

public class Bird {

    public double speedInMBS = 1;

    private static final double VERTICAL_IMPACT_FACTOR = 0.25;
    private static final double HORIZONTAL_IMPACT_FACTOR = 0.5;

    private Orientation orientation;
    private Position position;

    private Integer skyWidth;
    private Integer skyHeight;

    private ILifeEntityShape shape;

    public Bird(Integer skyWidth, Integer skyHeight) {
        Position position = new Position(skyWidth, skyHeight);
        this.skyWidth = skyWidth;
        this.skyHeight = skyHeight;
        this.position = position;
        this.orientation = new Orientation();
        this.shape = new Triangle();
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public Position getPosition() {
        return this.position;
    }

    public void updatePosition(Position position) {
        this.position = position;
    }

    public Boolean equals(Bird bird) {
        return this.orientation.equals(bird.orientation) && this.position.equals(bird.position);
    }

    public void draw(Graphics graphic) {
        this.shape.draw(orientation, position, graphic);
    }

    public void move() {
        this.position = getBirdPositionUpdated();
    }

    private Position getBirdPositionUpdated() {
        if (isHorizontalImpact(position)) {
            computeHorizontalImpact(orientation);
        }
        if (isVerticalImpact(position)) {
            computeVerticalImpact(orientation);
        }

        double rad = Math.toRadians(orientation.getValue() * 360);
        position.x = computeNextXOnSameLine(position.x, rad);
        position.y = computeNextYOnSameLine(position.y, rad);
        return position;
    }


    private double computeNewAngleAfterImpact(double impactAngle, double impactConstant) {
        return (impactAngle - (impactAngle - impactConstant) * 2 + 0.5) % 1;

    }

    private void computeVerticalImpact(Orientation orientation) {
        double angle = orientation.getValue();
        double newAngle = computeNewAngleAfterImpact(angle, VERTICAL_IMPACT_FACTOR);
        orientation.setValue(newAngle);
    }

    private void computeHorizontalImpact(Orientation orientation) {
        double angle = orientation.getValue();
        double newAngle = computeNewAngleAfterImpact(angle, HORIZONTAL_IMPACT_FACTOR);
        orientation.setValue(newAngle);
    }

    private double computeNextXOnSameLine(double actualXPosition, double angleInRad) {
        return actualXPosition + this.speedInMBS * Math.cos(angleInRad);
    }

    private double computeNextYOnSameLine(double actualYPosition, double angleInRad) {
        return actualYPosition - this.speedInMBS * Math.sin(angleInRad);
    }

    private boolean isHorizontalImpact(Position position) {
        return position.x <= 0 || position.x >= this.skyWidth;
    }

    private boolean isVerticalImpact(Position position) {
        return position.y <= 0 || position.y >= this.skyHeight;
    }
}
