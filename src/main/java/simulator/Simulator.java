package simulator;

import model.Sky;
import viewer.SkyViewer;

public class Simulator {
    public static void main(String[] args) throws InterruptedException {

        Integer WIDTH = 500;
        Integer HEIGHT = 500;
        int NB_BIRDS = 1000;

        SkyViewer skyViewer = new SkyViewer(WIDTH, HEIGHT);

        Sky sky = new Sky(HEIGHT, WIDTH);
        for (int i = 0; i < NB_BIRDS; i++)
            sky.addBird(randomPos(HEIGHT), randomPos(WIDTH), randomPos(359));


        while (true) {
            Thread.sleep(1000 / 500);
            sky.move();
            skyViewer.paintBirds(sky.getBirds());
        }

    }

    public static Integer randomPos(Integer max) {
        return (int) (Math.random() * max);
    }
}
