package model;

import java.util.List;

public class Bird {
    private Double x, y;
    private Integer angle;

    private final Integer angleVariation = 3;

    public Bird(Double x, Double y, Integer angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
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

    public void move(Integer width, Integer height, List<Bird> otherBirds) {
        if (otherBirds.size() > 1) {
            setOrientationByClosestBird(width, height, otherBirds);
        }

        angle = getBouncedAngle(width, height);

        this.setX(Math.cos(Math.toRadians(angle)) + this.x);
        this.setY(Math.sin(Math.toRadians(angle)) + this.y);
    }

    private void setOrientationByClosestBird(Integer width, Integer height, List<Bird> otherBirds) {
        Bird closestBird = getClosestBird(width, height, otherBirds);
        double a = closestBird.getY() - y;
        double b = closestBird.getX() - x;
        double distance = Math.sqrt(a * a + b * b);

        double angleInRadians = Math.acos(Math.abs(b / distance));
        double aimingOtherBirdAngle = (360 + ((int) (Math.toDegrees(angleInRadians)))) % 360;
        if (distance > 10)
            angle = aimingOtherBirdAngle > angle ? angle + angleVariation : angle - angleVariation;
        else
            angle = aimingOtherBirdAngle <= angle ? angle + angleVariation : angle - angleVariation;
    }

    private Bird getClosestBird(Integer width, Integer height, List<Bird> otherBirds) {
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

    private Integer getBouncedAngle(Integer width, Integer height) {
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
}
