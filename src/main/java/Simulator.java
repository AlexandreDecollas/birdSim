import java.util.List;
import java.util.Objects;

public class Simulator {

    private final Sky sky;

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
    }

    public List<Bird> getBirds() {
        return this.sky.getBirds();
    }

    public void iterateTime(Integer nbIterations) {
        for (int i = 0; i < nbIterations; i++) {
            List<Bird> birds = this.getBirds();
            for (Bird bird : birds) {
                Position position = bird.getPosition();
                Orientation orientation = bird.getOrientation();

                if (isOutOfTheSky(position)) {
                    orientation.setValue(invertAngle(orientation));
                }

                double rad = Math.toRadians(orientation.getValue() * 360);
                position.x = computeNextXOnSameLine(position.x, rad);
                position.y = computeNextYOnSameLine(position.y, rad);


                bird.updatePosition(position);
            }
        }
    }

    private boolean isOutOfTheSky(Position position) {
        return position.x >= Sky.width || position.x <= 0 || position.y >= Sky.height || position.y <= 0;
    }

    private double computeNextXOnSameLine(double actualXPosition, double angleInRad) {
        return Math.round(actualXPosition + Math.cos(angleInRad));
    }

    private double computeNextYOnSameLine(double actualYPosition, double angleInRad) {
        return Math.round(actualYPosition - Math.sin(angleInRad));
    }

    private double invertAngle(Orientation orientation) {
        return (orientation.getValue() + 0.5) % 1;
    }

    public void start(Integer nbSeconds) {
        this.iterateTime(nbSeconds * 25);
    }
}
