import java.awt.*;

public class Bird {

    public double speedInMBS = 1;

    private static final double VERTICAL_IMPACT_FACTOR = 0.25;
    private static final double HORIZONTAL_IMPACT_FACTOR = 0.5;

    private Orientation orientation;
    private Position position;

    private Integer skyWidth;
    private Integer skyHeight;

    public Bird(Integer skyWidth, Integer skyHeight) {
        Position position = new Position(skyWidth, skyHeight);
        this.skyWidth = skyWidth;
        this.skyHeight = skyHeight;
        this.position = position;
        this.orientation = new Orientation();
    }
    public Orientation getOrientation() {
        return this.orientation;
    }

    public Position getPosition() {
        return this.position;
    }

    public Position updatePosition(Position position) {
        return this.position = position;
    }

    public Boolean equals(Bird bird) {
        return this.orientation.equals(bird.orientation) && this.position.equals(bird.position);
    }

    public void draw(Graphics g) {
        double angleInRad1 = getAngleInRad(orientation.getValue() - 0.05);
        double x1 = calculateTriangleSideXPosition(angleInRad1);
        double x2 = position.x + SimulatorConstants.LEFT_MARGIN_IN_PX;
        double y1 = calculateTriangleSideYPosition(angleInRad1);
        double y2 = position.y + SimulatorConstants.TOP_MARGIN_IN_PX;

        double angleInRad2 = getAngleInRad(orientation.getValue() + 0.05);
        double x3 = calculateTriangleSideXPosition(angleInRad2);
        double x4 = position.x + SimulatorConstants.LEFT_MARGIN_IN_PX;
        double y3 = calculateTriangleSideYPosition(angleInRad2);
        double y4 = position.y + SimulatorConstants.TOP_MARGIN_IN_PX;

        g.drawPolygon(
                new int[]{(int) x1, (int) x2, (int) x3, (int) x4},
                new int[]{(int) y1, (int) y2, (int) y3, (int) y4},
                3
        );
    }

    private double getAngleInRad(double angle) {
        return Math.toRadians(angle * 360);
    }

    private double calculateTriangleSideXPosition(double angle) {
        return position.x - Math.cos(angle) * 10 + SimulatorConstants.LEFT_MARGIN_IN_PX;
    }

    private double calculateTriangleSideYPosition(double angle) {
        return position.y + Math.sin(angle) * 10 + SimulatorConstants.TOP_MARGIN_IN_PX;
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
