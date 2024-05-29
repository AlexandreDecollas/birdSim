package model;

import java.util.List;

import static constants.SimulatorConstants.ANGLE_TOWARDING;

public class Bird {
    private Double x, y;
    private Integer angle;
    private final Double universWidth;
    private final Double universHeight;

    public Bird(Double x, Double y, Integer angle, Double skyWidthSize, Double skyHeightSize) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.universWidth = skyWidthSize;
        this.universHeight = skyHeightSize;
    }

    public Double getY() {
        return y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double newX) {
        this.x = newX;
    }

    public void setY(Double newY) {
        y = newY;
    }

    public Trajectory getTrajectory() {
        return Trajectory.LINEAR;
    }

    public Integer getAngle() {
        return angle;
    }

    public void move(List<Bird> otherBirds) {
        if (!otherBirds.isEmpty()) {
            setOrientationByClosestBird(this.universWidth, this.universHeight, otherBirds);
        }

        angle = getBouncedAngle(this.universWidth, this.universHeight);

        this.setX(Math.cos(Math.toRadians(angle)) + this.x);
        this.setY(Math.sin(Math.toRadians(angle)) + this.y);
    }

    public Integer calculateAngleTo(Bird otherBird) {
        double deltaX = otherBird.getX() - x;
        double deltaY = otherBird.getY() - y;
        double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));
        angle = (360 + angle) % 360;
        return (int) angle;
    }

    public void orientTowards(Bird otherBird) {
        Integer angleToOtherBird = calculateAngleTo(otherBird);
        if (angleToOtherBird > (angle - 180 + 360) % 360) {
            angle = (angle - ANGLE_TOWARDING + 360) % 360;
        } else {
            angle = (angle + ANGLE_TOWARDING + 360) % 360;
        }


    }

    private void setOrientationByClosestBird(Double width, Double height, List<Bird> otherBirds) {
        Bird closestBird = getClosestBird(width, height, otherBirds);
        if (closestBird == null) {
            return;
        }
        double a = closestBird.getY() - y;
        double b = closestBird.getX() - x;
        double distance = Math.sqrt(a * a + b * b);

        if (distance > 10)
            orientTowards(closestBird);
    }

    private Bird getClosestBird(Double width, Double height, List<Bird> otherBirds) {
        double distanceToClosest = width + height;
        Bird closestBird = null;
        for (Bird bird : otherBirds) {
            if (this == bird)
                continue;

            double x1 = x;
            double y1 = y;
            double x2 = bird.getX();
            double y2 = bird.getY();

            double distanceToBird = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

            if (distanceToBird < distanceToClosest) {
                distanceToClosest = distanceToBird;
                closestBird = bird;
            }

        }
        return closestBird;
    }

    private Integer getBouncedAngle(Double width, Double height) {
        if (x <= 0 && angle >= 90 && angle <= 180) {
            return 180 - angle;
        }
        if (x <= 0 && angle >= 180 && angle < 270) {
            return 540 - angle;
        }
        if ((x >= width && angle >= 270 && angle < 360)) {
            return 540 - angle;
        }
        if (x >= width && angle >= 0 && angle < 90) {
            return 180 - angle;
        }
        if (y <= 0 && angle >= 0 && angle < 90) {
            return 360 - angle;
        }
        if (y <= 0 && angle >= 180 && angle < 270) {
            return 360 - angle;
        }
        if (y <= 0 && angle >= 270 && angle < 360) {
            return 360 - angle;
        }
        if (y >= height && angle >= 0 && angle < 90) {
            return 360 - angle;
        }
        if (y >= height && angle >= 90 && angle < 180) {
            return 360 - angle;
        }
        return angle;
    }

    public Double getUniversWidth() {
        return this.universWidth;
    }

    public Double getUniversHeight() {
        return this.universHeight;
    }

}
