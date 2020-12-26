import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Sky {
    private Integer width = 400;
    private Integer height = 400;

    protected JFrame skyFrame;
    private List<Bird> birds = new ArrayList<Bird>();
    protected SkyFramePicture skyFramePicture;

    public Sky(String title) {
        initNewSkyFrame(title);
    }

    private void initNewSkyFrame(String title) {
        skyFrame = new JFrame(title);
        skyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initSkyFramePicture();
        this.skyFrame.add(skyFramePicture);
        this.skyFrame.pack();

        skyFrame.setSize(width, height);
        this.skyFrame.repaint();
        this.skyFrame.setVisible(true);

        width = this.skyFrame.getSize().width - SimulatorConstants.LEFT_MARGIN_IN_PX - SimulatorConstants.RIGHT_MARGIN_IN_PX - 5;
        height = this.skyFrame.getSize().height - SimulatorConstants.TOP_MARGIN_IN_PX - SimulatorConstants.BOTTOM_MARGIN_IN_PX - 33;

        skyFrame.setResizable(false);
    }

    public Sky(String title, Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.initNewSkyFrame(title);
    }

    public Integer getSkyHeight() {
        return this.height;
    }

    public Integer getSkyWidth() {
        return this.width;
    }

    private void initSkyFramePicture() {
        this.skyFramePicture = new SkyFramePicture();
    }

    public Integer getFlyingBirdsCount() {
        return this.birds.size();
    }


    public void addBird(Bird bird) {
        this.birds.add(bird);
    }

    public void addBird() {
        Position position = new Position(this.width, this.height);
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
