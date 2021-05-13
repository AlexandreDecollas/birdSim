package model;

import simulator.SimulatorConstants;
import view.SkyFramePicture;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Sky {
    private Integer width = 400;
    private Integer height = 400;

    public JFrame skyFrame;
    private List<Bird> birds = new ArrayList<>();
    public SkyFramePicture skyFramePicture;

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

        width = this.skyFrame.getSize().width - SimulatorConstants.LEFT_MARGIN_IN_PX - SimulatorConstants.RIGHT_MARGIN_IN_PX;
        height = this.skyFrame.getSize().height - SimulatorConstants.TOP_MARGIN_IN_PX - SimulatorConstants.BOTTOM_MARGIN_IN_PX - SimulatorConstants.WINDOW_MENU_SIZE;

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
        this.addBird(new Bird(this));
    }

    public List<Bird> getBirds() {
        return this.birds;
    }

    public boolean equals(Sky sky) {
        if (dimensionsNotEquals(sky)) {
            return false;
        }

        List<Bird> comparingBirds = sky.getBirds();
        for (int birdIndex = 0; birdIndex < birds.size(); birdIndex++) {
            if (areBirdsNotTheSame(comparingBirds, birdIndex))
                return false;
        }
        return true;
    }

    private boolean areBirdsNotTheSame(List<Bird> comparingBirds, int i) {
        return !comparingBirds.get(i).equals(birds.get(i));
    }

    private boolean dimensionsNotEquals(Sky sky) {
        return !this.width.equals(sky.width) || !this.height.equals(sky.height);
    }

    public void refreshSkyPicture() {
        skyFramePicture.paintBirds(this.birds);
    }
}
