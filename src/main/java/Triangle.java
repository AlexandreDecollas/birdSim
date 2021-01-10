import java.awt.*;

public class Triangle implements ILifeEntityShape {
    @Override
    public void draw(Orientation orientation, Position position, Graphics graphic) {

        double angleInRad1 = getAngleInRad(orientation.getValue() - 0.05);
        double x1 = calculateTriangleSideXPosition(position, angleInRad1);
        double x2 = position.x + SimulatorConstants.LEFT_MARGIN_IN_PX;
        double y1 = calculateTriangleSideYPosition(position, angleInRad1);
        double y2 = position.y + SimulatorConstants.TOP_MARGIN_IN_PX;

        double angleInRad2 = getAngleInRad(orientation.getValue() + 0.05);
        double x3 = calculateTriangleSideXPosition(position, angleInRad2);
        double x4 = position.x + SimulatorConstants.LEFT_MARGIN_IN_PX;
        double y3 = calculateTriangleSideYPosition(position, angleInRad2);
        double y4 = position.y + SimulatorConstants.TOP_MARGIN_IN_PX;

        graphic.drawPolygon(
                new int[]{(int) x1, (int) x2, (int) x3, (int) x4},
                new int[]{(int) y1, (int) y2, (int) y3, (int) y4},
                3
        );
    }

    private double getAngleInRad(double angle) {
        return Math.toRadians(angle * 360);
    }

    private double calculateTriangleSideXPosition(Position position, double angle) {
        return position.x - Math.cos(angle) * 10 + SimulatorConstants.LEFT_MARGIN_IN_PX;
    }

    private double calculateTriangleSideYPosition(Position position, double angle) {
        return position.y + Math.sin(angle) * 10 + SimulatorConstants.TOP_MARGIN_IN_PX;
    }
}
