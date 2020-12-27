import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class SkyFramePicture extends JComponent {

    private List<Bird> birds = new ArrayList<Bird>();

    @Override
    public void paintComponent(Graphics g) {
        try {
            drawSkyBorders(g);
            for (Bird bird : birds) {
                bird.draw(g);
            }
        } catch (ConcurrentModificationException e) {
            // do nothing
        }
    }

    private void drawSkyBorders(Graphics g) {
        g.drawRect(
                SimulatorConstants.LEFT_MARGIN_IN_PX,
                SimulatorConstants.TOP_MARGIN_IN_PX,
                getSize().width -
                        SimulatorConstants.LEFT_MARGIN_IN_PX -
                        SimulatorConstants.RIGHT_MARGIN_IN_PX,
                getSize().height -
                        SimulatorConstants.TOP_MARGIN_IN_PX -
                        SimulatorConstants.BOTTOM_MARGIN_IN_PX
        );
    }

    public SkyFramePicture(List<Bird> birds) {
        super();
        this.birds = birds;

    }

    public SkyFramePicture() {
        super();
    }

    public void paintBirds(List<Bird> birds) {
        this.birds = birds;
        this.repaint();
    }
}
