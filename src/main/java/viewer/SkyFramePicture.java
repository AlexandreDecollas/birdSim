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
        int x2 = Math.abs((int) (20 * Math.cos(Math.toRadians(bird.getAngle() - 20)) - xHead));
        int y2 = (Math.abs((int) (20 * Math.sin(Math.toRadians(bird.getAngle() - 20)) - yHead)));
        int x3 = Math.abs((int) (20 * Math.cos(Math.toRadians(bird.getAngle() + 20)) - xHead));
        int y3 = (Math.abs((int) (20 * Math.sin(Math.toRadians(bird.getAngle() + 20)) - yHead)));
        g.drawPolygon(
                new int[]{xHead, x2, x3},
                new int[]{yHead, y2, y3},
                3
        );
//        g.drawOval(xHead - 3, yHead - 3, 6, 6);
        g.drawString(bird.getAngle().toString() +
                        ", x : " + Math.round(bird.getX()) +
                        ", y : " + Math.round(bird.getY()),
                xHead + 10, yHead + 10);
    }

    public void paintBirds(List<Bird> birds) {
        this.birds = birds;
        this.repaint();
    }
}
