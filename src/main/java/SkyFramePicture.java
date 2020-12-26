import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class SkyFramePicture extends JComponent {

    private List<Position> positions = new ArrayList<Position>();

    @Override
    public void paintComponent(Graphics g) {
        try {
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
            for (Position position : positions) {
                g.drawOval(
                        (int) position.x + SimulatorConstants.LEFT_MARGIN_IN_PX,
                        (int) position.y + SimulatorConstants.TOP_MARGIN_IN_PX,
                        5, 5
                );
                g.drawPolygon(
                        new int[]{(int) position.x + SimulatorConstants.LEFT_MARGIN_IN_PX},
                        new int[]{(int) position.y + SimulatorConstants.TOP_MARGIN_IN_PX},
                        1
                );
            }
        } catch (ConcurrentModificationException e) {
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
        for (Bird bird : birds) {
            this.positions.add(bird.getPosition());
        }
        this.repaint();
    }
}
