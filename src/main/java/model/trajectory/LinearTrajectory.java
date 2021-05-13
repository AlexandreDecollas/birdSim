package model.trajectory;

import model.Bird;
import model.Sky;
import view.Orientation;
import view.Position;

public class LinearTrajectory extends Trajectory {
    private static final double VERTICAL_IMPACT_FACTOR = 0.25;
    private static final double HORIZONTAL_IMPACT_FACTOR = 0.5;

    public static Position computeNextPosition(Bird bird, Sky sky) {

        Position position = bird.getPosition();
        Orientation orientation = bird.getOrientation();

        if (isHorizontalImpact(position, sky)) {
            computeHorizontalImpact(orientation);
        }
        if (isVerticalImpact(position, sky)) {
            computeVerticalImpact(orientation);
        }

        double rad = Math.toRadians(orientation.getValue() * 360);
        position.x = computeNextXOnSameLine(position.x, rad, bird.speedInMBS);
        position.y = computeNextYOnSameLine(position.y, rad, bird.speedInMBS);
        return position;

    }

    private static double computeNewAngleAfterImpact(double impactAngle, double impactConstant) {
        return (impactAngle - (impactAngle - impactConstant) * 2 + 0.5) % 1;

    }

    private static void computeVerticalImpact(Orientation orientation) {
        double angle = orientation.getValue();
        double newAngle = computeNewAngleAfterImpact(angle, VERTICAL_IMPACT_FACTOR);
        orientation.setValue(newAngle);
    }

    private static void computeHorizontalImpact(Orientation orientation) {
        double angle = orientation.getValue();
        double newAngle = computeNewAngleAfterImpact(angle, HORIZONTAL_IMPACT_FACTOR);
        orientation.setValue(newAngle);
    }

    private static double computeNextXOnSameLine(double actualXPosition, double angleInRad, double birdSpeedInMBS) {
        return actualXPosition + birdSpeedInMBS * Math.cos(angleInRad);
    }

    private static double computeNextYOnSameLine(double actualYPosition, double angleInRad, double birdSpeedInMBS) {
        return actualYPosition - birdSpeedInMBS * Math.sin(angleInRad);
    }

    private static boolean isHorizontalImpact(Position position, Sky sky) {
        return position.x <= 0 || position.x >= sky.getSkyWidth();
    }

    private static boolean isVerticalImpact(Position position, Sky sky) {
        return position.y <= 0 || position.y >= sky.getSkyHeight();
    }

}
