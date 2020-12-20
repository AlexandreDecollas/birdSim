import java.util.List;

public class Simulator {

    private final Sky sky;

    private static final double VERTICAL_IMPACT_FACTOR = 0.25;
    private static final double HORIZONTAL_IMPACT_FACTOR = 0.5;

    public Simulator() {
        this.sky = new Sky("Bird Flight Container");
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
            Position position = getBirdPositionUpdated(bird);
            bird.updatePosition(position);
            sky.refreshSkyPicture();
        }
    }

    private Position getBirdPositionUpdated(Bird bird) {
        Position position = bird.getPosition();
        Orientation orientation = bird.getOrientation();

        if (isHorizontalImpact(position)) {
            computeHorizontalImpact(orientation);
        }
        if (isVerticalImpact(position)) {
            computeVerticalImpact(orientation);
        }

        double rad = Math.toRadians(orientation.getValue() * 360);
        position.x = computeNextXOnSameLine(position.x, rad);
        position.y = computeNextYOnSameLine(position.y, rad);
        return position;
    }

    private void computeVerticalImpact(Orientation orientation) {
        double angle = orientation.getValue();
        double newAngle = computeNewAngleAfterImpact(angle, VERTICAL_IMPACT_FACTOR);
        orientation.setValue(newAngle);
    }

    private void computeHorizontalImpact(Orientation orientation) {
        double angle = orientation.getValue();
        double newAngle = computeNewAngleAfterImpact(angle, HORIZONTAL_IMPACT_FACTOR);
        orientation.setValue(newAngle);
    }

    private double computeNewAngleAfterImpact(double impactAngle, double impactConstant) {
        return  (impactAngle - (impactAngle - impactConstant) * 2 + 0.5) % 1;

    }

    private boolean isHorizontalImpact(Position position) {
        return position.x <= 0 || position.x >= Sky.width;
    }

    private boolean isVerticalImpact(Position position) {
        return position.y <= 0 || position.y >= Sky.height;
    }

    private double computeNextXOnSameLine(double actualXPosition, double angleInRad) {
        return actualXPosition + Math.cos(angleInRad);
    }

    private double computeNextYOnSameLine(double actualYPosition, double angleInRad) {
        return actualYPosition - Math.sin(angleInRad);
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
            Thread.sleep(40);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Simulator sim = new Simulator();
        sim.addBirdsInTheSky(50);
        sim.start();
    }
}
