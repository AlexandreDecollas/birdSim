import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Sky {
    public static final Integer width = 400;
    public static final Integer height = 400;

    protected JFrame skyFrame;
    private List<Bird> birds = new ArrayList<Bird>();
    protected SkyFramePicture skyFramePicture;


    public Sky() {
        this.skyFramePicture = new SkyFramePicture(width, height);
    }

    public Sky(String title) {
        skyFrame = new JFrame(title);
        skyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.skyFramePicture = new SkyFramePicture(width, height);
        this.skyFrame.add(skyFramePicture);
        this.skyFrame.pack();
        skyFrame.setSize(width, height);
        this.skyFrame.setVisible(true);

    }

    public Integer getFlyingBirdsCount() {
        return this.birds.size();
    }


    public void addBird(Bird bird) {
        this.birds.add(bird);
    }

    public void addBird() {
        Position position = new Position(this.skyFrame.getSize().width, this.skyFrame.getSize().height);
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

    public void refreshSkyPicture() {
        skyFramePicture.paintBirds(this.birds);
    }
}
