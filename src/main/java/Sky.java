import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Sky {
    public static final Integer width = 400;
    public static final Integer height = 400;

    private JFrame sky;
    private List<Bird> birds = new ArrayList<Bird>();


    public Sky(String title) {
        this.sky = new JFrame(title);

        Canvas canvas = new Canvas();
        canvas.setSize(width, height);
        this.sky.add(canvas);
        this.sky.pack();
        this.sky.setVisible(true);
    }

    public Integer getFlyingBirdsCount() {
        return this.birds.size();
    }

    public void flyBirds() {
        for (Bird bird : birds) {
            Position position = bird.getPosition();
            this.sky.getGraphics().fillOval((int) position.x, (int) position.y, 3, 3);
        }
    }

    public void addBird(Bird bird) {
        this.birds.add(bird);
    }

    public void addBird() {
        Position position = new Position(this.sky.getSize().width, this.sky.getSize().height);
        this.addBird(new Bird(position));
    }

    public List<Bird> getBirds() {
        return this.birds;
    }

    public boolean equals(Sky sky) {
        List<Bird> comparingBirds = sky.getBirds();

        for (int i = 0; i < birds.size(); i++) {
            if (!comparingBirds.get(i).equals(birds.get(i)))
                return false;
        }
        return true;
    }
}
