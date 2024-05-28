package model;

import java.util.ArrayList;
import java.util.List;

public class Sky {

    private final Double height;
    private final Double width;

    private List<Bird> birds = new ArrayList<>();

    public Sky(Double aHeight, Double aWidth) {
        this.height = aHeight;
        this.width = aWidth;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    public void addBird(Double y, Double x, Integer angle) {
        this.birds.add(new Bird(x, y, angle, this.width, this.height));
    }

    public List<Bird> getBirds() {
        return this.birds;
    }

    public void move() {
        for (Bird bird : this.birds) {
            bird.move(birds);
        }
    }
}
