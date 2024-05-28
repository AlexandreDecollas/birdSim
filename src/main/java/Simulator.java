import model.Sky;
import viewer.SkyViewer;

import static java.lang.Thread.sleep;

public class Simulator {
    public static void main(String[] args) throws InterruptedException {

        Double WIDTH = 500.0;
        Double HEIGHT = 500.0;
        int NB_BIRDS = 1;

        SkyViewer skyViewer = new SkyViewer(WIDTH, HEIGHT);

        Sky sky = new Sky(HEIGHT, WIDTH);
        for (int i = 0; i < NB_BIRDS; i++)
            sky.addBird(randomPos(HEIGHT), randomPos(WIDTH), randomAngle());


        while (true) {
            sleep(1000 / 50);
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
