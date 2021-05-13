package model;

import model.trajectory.LinearTrajectory;
import view.*;

import java.awt.*;

public class Bird {

    public double speedInMBS = 1;

    private final Orientation orientation;
    private Position position;

    private final Sky sky;

    private final ILifeEntityShape shape;

    public Bird(Sky sky) {
        this.position = new Position(sky.getSkyWidth(), sky.getSkyHeight());

        this.sky = sky;
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
        return LinearTrajectory.computeNextPosition(this, this.sky);
    }
}
