import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class SkyFramePicture extends JComponent {

    protected static final Integer TOP_MARGIN = 5;
    protected static final Integer LEFT_MARGIN = 5;
    protected static final Integer BOTTOM_MARGIN = 5;
    protected static final Integer RIGHT_MARGIN = 5;

    private List<Position> positions = new ArrayList<Position>();

    @Override
    public void paintComponent(Graphics g) {
        try {
            g.drawRect(
                    LEFT_MARGIN,
                    TOP_MARGIN,
                    getSize().width - LEFT_MARGIN - RIGHT_MARGIN,
                    getSize().height - TOP_MARGIN - BOTTOM_MARGIN
            );
            for (Position position : positions) {
                g.drawOval((int)position.x + LEFT_MARGIN, (int)position.y + TOP_MARGIN, 5, 5);
                g.drawPolygon(
                        new int[] {(int)position.x + LEFT_MARGIN},
                        new int[] {(int)position.y + TOP_MARGIN},
                        1
                );
            }
        } catch (ConcurrentModificationException e ) {
            // do nothing
        }
    }

    public SkyFramePicture(List<Position> positions) {
        super();
        this.positions = positions;

    }

    public SkyFramePicture() {
        super();
    }

    public void paintBirds(List<Bird> birds) {
        positions = new ArrayList<Position>();
        for (Bird bird: birds) {
            this.positions.add(bird.getPosition());
        }
        this.repaint();
    }
}
