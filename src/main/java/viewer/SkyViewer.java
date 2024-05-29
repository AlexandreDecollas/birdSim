package viewer;

import model.Bird;

import javax.swing.*;
import java.util.List;

public class SkyViewer {

    private final SkyFramePicture skyFrame;

    public SkyViewer(Double width, Double height) {
        JFrame sky = new JFrame("Sky viewer");

        this.skyFrame = new SkyFramePicture();
        sky.add(skyFrame);
        sky.pack();

        sky.setSize((int) Math.floor(width), (int) Math.floor(height) + 30);
        sky.setVisible(true);
        sky.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paintBirds(List<Bird> birds) {
        skyFrame.paintBirds(birds);
    }
}
