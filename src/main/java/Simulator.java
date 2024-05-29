import model.Sky;
import viewer.SkyViewer;

import static constants.SimulatorConstants.*;
import static java.lang.Thread.sleep;

public class Simulator {
    public static void main(String[] args) throws InterruptedException {


        SkyViewer skyViewer = new SkyViewer(SKY_WIDTH, SKY_HEIGHT);

        Sky sky = new Sky(SKY_HEIGHT, SKY_WIDTH);
        for (int i = 0; i < NB_BIRDS; i++)
            sky.addBird(randomPos(SKY_HEIGHT), randomPos(SKY_WIDTH), randomAngle());


        while (true) {
            sleep(SLIP_INTERVAL);
            sky.move();
            skyViewer.paintBirds(sky.getBirds());
        }

    }

    public static Double randomPos(Double max) {
        return (Math.random() * max);
    }

    public static Integer randomAngle() {
        return (int) (Math.random() * 359);
    }
}
