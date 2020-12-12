import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimulatorTest {

    Simulator sc;

    @BeforeEach
    void init() {
        this.sc = new Simulator();
    }

    @Test
    @DisplayName("Should init simulator")
    void shouldInitSimulator() {
        assertNotNull(this.sc);
    }

    @Test
    @DisplayName("Should provide a sky")
    void shouldProvideASky() {
        Sky sky = this.sc.getSky();
        assertTrue(Sky.height > 0);
        assertTrue(Sky.width > 0);
    }

    @Test
    @DisplayName("Should add birds")
    void shouldAddWantedBirdSize() {
        this.sc.addBirdsInTheSky(7);
        Integer birdPoolSize = this.sc.getNbBirds();

        assertEquals(birdPoolSize, 7);
    }

    @Test
    @DisplayName("Should add birds on sky random orientation at birth")
    void shouldMoveBirdOnCanevasWithTime() {
        this.sc.addBirdsInTheSky(100);
        List<Bird> birds = this.sc.getBirds();
        Orientation firstBirdOrientation = birds.get(0).getOrientation();
        boolean allBirdStartWithSameOrientation = true;

        for (Bird bird : birds) {
            Orientation orientation = bird.getOrientation();
            if (orientation.getValue() != firstBirdOrientation.getValue()) {
                allBirdStartWithSameOrientation = false;
            }
        }

        assertFalse(allBirdStartWithSameOrientation);
    }

    @Test
    @DisplayName("Should add each bird at a random position in the sky at init")
    void shouldInitRandomBirdPositionInSkyAtInit() {
        this.sc.addBirdsInTheSky(2);
        Position position1 = this.sc.getBirds().get(0).getPosition();
        Position position2 = this.sc.getBirds().get(1).getPosition();
        assertTrue(position1.x != position2.x && position1.y != position2.y);
        assertEquals(this.sc.getSky().getFlyingBirdsCount(), 2);
    }

    @Test
    @DisplayName("Should update bird position with time")
    void shouldUpdateBirdPositionWithTime() {
        this.sc.addBirdsInTheSky(1);
        Position positionBefore = this.sc.getBirds().get(0).getPosition();

        double xBefore = positionBefore.x;
        double yBefore = positionBefore.y;

        this.sc.iterateTime(1);
        Position positionAfter = this.sc.getBirds().get(0).getPosition();

        double xAfter = positionAfter.x;
        double yAfter = positionAfter.y;

        assertTrue(xBefore != xAfter || yBefore != yAfter);
    }

    @Test
    @DisplayName("Should move bird on a line")
    void shouldMoveBirdOnALine() {
        this.sc.addBirdsInTheSky(1);
        Bird bird = this.sc.getBirds().get(0);
        Position positionBefore = bird.getPosition();
        positionBefore.x = 200;
        positionBefore.y = 200;
        Orientation orientation = bird.getOrientation();
        orientation.setValue(0.3);
        double rad = Math.toRadians(orientation.getValue() * 360);

        double xWithUpdate = Math.round(positionBefore.x + Math.cos(rad));
        double yWithUpdate = Math.round(positionBefore.y - Math.sin(rad));

        this.sc.iterateTime(1);
        Position positionAfter = this.sc.getBirds().get(0).getPosition();

        double xAfter = positionAfter.x;
        double yAfter = positionAfter.y;

        assertEquals(xWithUpdate, xAfter);
        assertEquals(yWithUpdate, yAfter);
    }

    @Test
    @DisplayName("every birds should fly in line until it meets the right border")
    void shouldFlyInLineUntilRightBorder() {
        this.sc.addBirdsInTheSky(1);
        Bird bird = this.sc.getBirds().get(0);

        bird.getOrientation().setValue(0);

        bird.getPosition().x = 400;
        bird.getPosition().y = 200;

        this.sc.iterateTime(1);

        assertEquals(399, bird.getPosition().x);
        assertEquals(200, bird.getPosition().y);
    }

    @Test
    @DisplayName("every birds should fly in line until the meet the left border")
    void shouldFlyInLineUntilLeftBorder() {
        this.sc.addBirdsInTheSky(1);
        Bird bird = this.sc.getBirds().get(0);

        bird.getOrientation().setValue(0.5);

        bird.getPosition().x = 0;
        bird.getPosition().y = 200;

        this.sc.iterateTime(1);

        assertEquals(1, bird.getPosition().x);
        assertEquals(200, bird.getPosition().y);
        assertEquals(0, bird.getOrientation().getValue());
    }

    @Test
    @DisplayName("every birds should fly in line until the meet the bottom border")
    void shouldFlyInLineUntilBottomBorder() {
        this.sc.addBirdsInTheSky(1);
        Bird bird = this.sc.getBirds().get(0);

        bird.getOrientation().setValue(0.75);

        bird.getPosition().x = 200;
        bird.getPosition().y = 400;

        this.sc.iterateTime(1);

        assertEquals(200, bird.getPosition().x);
        assertEquals(399, bird.getPosition().y);
        assertEquals(0.25, bird.getOrientation().getValue());
    }

    @Test
    @DisplayName("should go back on the same line when meet top border in the vertical direction")
    void shouldReboundOnSameLine() {
        this.sc.addBirdsInTheSky(1);
        Bird bird = this.sc.getBirds().get(0);

        bird.getOrientation().setValue(0.25);

        bird.getPosition().x = 200;
        bird.getPosition().y = 0;

        this.sc.iterateTime(1);

        assertEquals(1, bird.getPosition().y );
        assertEquals(200, bird.getPosition().x);
        assertEquals(0.75, bird.getOrientation().getValue());
    }

    @Test
    @DisplayName("Should iter 25 times per second when simulation runs")
    void shouldIter25TimesWhenSimulationRuns() {
        Integer ONE_SECONDE = 1;
        Simulator scWitness = new Simulator();

        sc.addBirdsInTheSky(1);
        scWitness.addBirdsInTheSky(1);
        Bird witnessBird = scWitness.getBirds().get(0);
        Bird bird = sc.getBirds().get(0);

        witnessBird.getPosition().x = bird.getPosition().x;
        witnessBird.getPosition().y = bird.getPosition().y;
        witnessBird.getOrientation().setValue(bird.getOrientation().getValue());

        sc.start(ONE_SECONDE);
        scWitness.iterateTime(25);

        assertTrue(scWitness.getSky().equals(sc.getSky()));
    }
}
