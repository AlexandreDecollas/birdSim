import java.util.List;

public class Simulator {

    private final Sky sky;


    public Simulator() {
        this.sky = new Sky("Bird Flight Container");
    }

    public Simulator(Integer width, Integer height) {
        this.sky = new Sky("Bird Flight Container", width, height);

    }

    public Sky getSky() {
        return this.sky;
    }

    public Integer getNbBirds() {
        return this.sky.getFlyingBirdsCount();
    }

    public void addBirdsInTheSky(Integer nbBirds) {
        for (int i = 0; i < nbBirds; i++) {
            this.sky.addBird();
        }
        this.sky.refreshSkyPicture();
    }

    public void addBirdsInTheSky(Integer nbBirds, double speed) {
        for (int i = 0; i < nbBirds; i++) {
            Bird bird = new Bird(this.sky.getSkyWidth(), this.sky.getSkyHeight());
            bird.speedInMBS = speed;
            this.sky.addBird(bird);
        }
        this.sky.refreshSkyPicture();
    }

    public List<Bird> getBirds() {
        return this.sky.getBirds();
    }

    public void iterateTime(Integer nbIterations) {
        for (int i = 0; i < nbIterations; i++) {
            updateBirdsPositionInTheSky();
        }
    }

    private void updateBirdsPositionInTheSky() {
        List<Bird> birds = this.getBirds();
        for (Bird bird : birds) {
            bird.move();
        }
        sky.refreshSkyPicture();
    }

    public void start(Integer nbSeconds) throws InterruptedException {
        for (int i = 0; i < nbSeconds * 25; i++) {
            this.iterateTime(1);
            Thread.sleep(40);
        }
    }

    public void start() throws InterruptedException {
        while (true) {
            this.iterateTime(1);
            Thread.sleep(1000/25);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Simulator sim = new Simulator(1000, 1000);
        sim.addBirdsInTheSky(100, 4);
        sim.start();
    }
}
