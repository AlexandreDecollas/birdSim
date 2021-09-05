package viewer;

import model.Bird;

import javax.swing.*;
import java.util.List;

public class SkyViewer {

    private final SkyFramePicture skyFrame;

    public SkyViewer(Integer width, Integer height) {
        JFrame sky = new JFrame("Sky viewer");

        this.skyFrame = new SkyFramePicture();
        sky.add(skyFrame);
        sky.pack();

        sky.setSize(width, height);
        sky.setVisible(true);
        sky.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paintBirds(List<Bird> birds) {
        skyFrame.paintBirds(birds);
    }
}
