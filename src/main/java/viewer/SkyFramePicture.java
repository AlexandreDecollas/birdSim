package viewer;

import model.Bird;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SkyFramePicture extends JComponent {

    private List<Bird> birds = new ArrayList<>();

    @Override
    public void paintComponent(Graphics g) {
        for (Bird bird : birds) {
            this.drawTriangle(g, bird);
        }
    }

    public void drawTriangle(Graphics g, Bird bird) {
        int xHead = (int) Math.round(bird.getX());
        int yHead = (int) Math.round(bird.getY());
        g.drawRect(xHead - 3, yHead - 3, 6, 6);
    }

    public void paintBirds(List<Bird> birds) {
        this.birds = birds;
        this.repaint();
    }
}
