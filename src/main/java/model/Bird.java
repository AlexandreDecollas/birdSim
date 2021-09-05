package model;

public class Bird {
    private Double x, y;
    private Integer angle;

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

    public void move(Integer width, Integer height) {

        angle = getBouncedAngle(width, height);

        this.setX(Math.cos(Math.toRadians(angle)) + this.x);
        this.setY(Math.sin(Math.toRadians(angle)) + this.y);
    }

    private Integer getBouncedAngle(Integer width, Integer height) {
        if (x <= 0 && angle >= 90 && angle < 180) {
            return 180 - angle;
        }
        if (x <= 0 && angle >= 180 && angle < 270){
            return 540 - angle;
        }
        if ((x >= width && angle >= 270 && angle < 360)) {
            return 540 - angle;
        }
        if (x >= width && angle >= 0 && angle < 90) {
            return 180 - angle;
        }
        if (y <= 0 && angle >= 270 && angle < 360) {
            return 360 - angle;
        }
        if (y <= 0 && angle >= 0 && angle < 90) {
            return 360 - angle;
        }
        if (y >= height && angle >= 0 && angle < 90) {
            return 360 - angle;
        }
        if (y >= height && angle >= 90 && angle < 180) {
            return 360 - angle;
        }
        if (y <= 0 && angle >= 180 && angle < 270) {
            return 360 - angle;
        }
        return angle;
    }
}
