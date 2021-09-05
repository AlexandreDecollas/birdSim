package model;

import java.util.ArrayList;
import java.util.List;

public class Sky {

    private final Integer height;
    private final Integer width;

    private List<Bird> birds = new ArrayList<>();

    public Sky(Integer aHeight, Integer aWidth) {
        this.height = aHeight;
        this.width = aWidth;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public void addBird(Integer y, Integer x, Integer angle) {
        this.birds.add(new Bird(x, y, angle));
    }

    public List<Bird> getBirds() {
        return this.birds;
    }

    public void move() {
        for (Bird bird : this.birds){
            bird.move(this.width, this.height);
        }
    }
}
