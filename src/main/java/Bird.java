import java.awt.*;

public class Bird {

    private Orientation orientation;
    private Position position;

    public Bird(Position position) {
        this.position = position;
        this.orientation = new Orientation();
    }

    public Bird() {
        this.position = new Position(0, 0);
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
        double angleInRad1 = Math.toRadians((orientation.getValue()-0.1) * 360);
        double x1 = position.x - Math.cos(angleInRad1) * 10+ SimulatorConstants.LEFT_MARGIN_IN_PX;
        double x2 = position.x+ SimulatorConstants.LEFT_MARGIN_IN_PX;
        double y1 = position.y + Math.sin(angleInRad1) * 10 + SimulatorConstants.TOP_MARGIN_IN_PX;
        double y2 = position.y + SimulatorConstants.TOP_MARGIN_IN_PX;
        double angleInRad2 = Math.toRadians((orientation.getValue() + 0.1) * 360);
        double x3 = position.x - Math.cos(angleInRad2) * 10+ SimulatorConstants.LEFT_MARGIN_IN_PX;
        double x4 = position.x+ SimulatorConstants.LEFT_MARGIN_IN_PX;
        double y3 = position.y + Math.sin(angleInRad2) * 10 + SimulatorConstants.TOP_MARGIN_IN_PX;
        double y4 = position.y + SimulatorConstants.TOP_MARGIN_IN_PX;
        g.drawPolygon(
                new int[]{(int)x1, (int)x2, (int)x3, (int)x4},
                new int[]{(int)y1, (int)y2, (int)y3, (int)y4},
                3
        );
    }
}
