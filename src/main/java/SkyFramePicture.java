import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SkyFramePicture extends JComponent {

    private List<Position> positions = new ArrayList<Position>();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Position position : positions) {
            g.drawRect((int) position.x, (int) position.y, 10, 10);
        }
    }

    public SkyFramePicture(List<Position> positions, Integer width, Integer height) {
        super();
        this.positions = positions;
        this.setSize(width, height);

    }

    public SkyFramePicture(Integer width, Integer height) {
        super();
        this.setSize(width, height);
    }

    public void paintBirds(List<Bird> birds) {
        positions = new ArrayList<Position>();
        for (Bird bird: birds) {
            this.positions.add(bird.getPosition());
        }
        this.repaint();
    }
}
