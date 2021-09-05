package model;

public class Bird {
    private Integer x, y, angle;

    public Bird(Integer x, Integer y, Integer angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public Integer getY() {
        return y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer newX) {
        this.x = newX;
    }

    public void setY(Integer newY) {
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

        this.setX(Math.abs((int) (Math.cos(Math.toRadians(angle)) + this.x)));
        this.setY((Math.abs((int) (Math.sin(Math.toRadians(angle)) + this.y))));
    }

    private Integer getBouncedAngle(Integer width, Integer height) {
        if (x.equals(0) && angle >= 90 && angle < 180) {
            return 180 - angle;
        }
        if ((x.equals(0) && angle >= 180 && angle < 270) || (x.equals(width) && angle >= 270 && angle < 360)) {
            return 540 - angle;
        }
        if (x.equals(width) && angle >= 0 && angle < 90) {
            return 180 - angle;
        }
        if (y.equals(0) && angle >= 270 && angle < 360) {
            return 360 - angle;
        }
        if (y.equals(0) && angle >= 0 && angle < 90) {
            return 360 - angle;
        }
        if (y.equals(height) && angle >= 0 && angle < 90) {
            return 360 - angle;
        }
        if (y.equals(height) && angle >= 90 && angle < 180) {
            return 360 - angle;
        }
        return angle;
    }
}
