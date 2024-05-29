package viewer;

import model.Bird;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SkyFramePicture extends JComponent {

    private static final boolean SHOW_DETAILS = false;
    private List<Bird> birds = new ArrayList<>();

    @Override
    public void paintComponent(Graphics g) {
        for (Bird bird : birds) {
            this.drawBird(g, bird);
        }
    }

    public static double[] calculateSecondPoint(
            double x,
            double y,
            double angle,
            double distance
    ) {
        double angleRadians = Math.toRadians(angle);
        double x2 = x + distance * Math.cos(angleRadians);
        double y2 = y + distance * Math.sin(angleRadians);
        return new double[]{x2, y2};
    }

    public void drawBird(Graphics g, Bird bird) {
        int xHead = (int) Math.round(bird.getX());
        int yHead = (int) Math.round(bird.getY());
        g.drawRoundRect(xHead - 5, yHead - 5, 10, 10, 10, 10);
        if (SHOW_DETAILS) {
            g.drawString(bird.getAngle().toString(), xHead + 5, yHead + 5);
            g.drawString("x = ".concat(bird.getX().toString()), xHead + 5, yHead + 15);
            g.drawString("y = ".concat(bird.getY().toString()), xHead + 5, yHead + 25);
        }

        double[] otherPoint = calculateSecondPoint(xHead, yHead, bird.getAngle(), 10.0);
        g.drawLine(xHead, yHead, (int) otherPoint[0], (int) otherPoint[1]);
    }

    public void paintBirds(List<Bird> birds) {
        this.birds = birds;
        this.repaint();
    }
}
