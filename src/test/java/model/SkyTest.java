package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SkyTest {

    private final Integer height = 100;
    private final Integer width = 200;
    private Sky sky;

    @BeforeEach
    void beforeEach() {
        this.sky = new Sky(height, width);
    }

    @Test
    @DisplayName("should be created with a height and a width")
    void shouldHaveTwoDimensions() {
        assertEquals(sky.getHeight(), this.height);
        assertEquals(sky.getWidth(), this.width);
    }

    @Test
    @DisplayName("should add a bird in the sky at a given position")
    void shouldAddABirdInTheSky() {
        sky.addBird(50, 60, 0);

        assertEquals(50, sky.getBirds().get(0).getY());
        assertEquals(60, sky.getBirds().get(0).getX());
    }

    @Test
    @DisplayName("should be able to add multiple birds in the sky")
    void shouldAddMultipleBirdsInTheSky() {
        sky.addBird(50, 60, 0);
        sky.addBird(50, 60, 0);
        sky.addBird(50, 60, 0);
        sky.addBird(50, 60, 0);

        assertEquals(4, sky.getBirds().size());
    }

    @Test
    @DisplayName("should move bird at each iteration")
    void shouldMoveBirdsAtEachIteration() {
        sky.addBird(50, 60, 0);

        sky.move();

        Double x = sky.getBirds().get(0).getX();
        Double y = sky.getBirds().get(0).getY();

        assertNotEquals(50 + 60, x + y);
    }

    @Test
    @DisplayName("bird should own a linear trajectory type by default")
    void birdsShouldOwnATrajectoryType() {
        sky.addBird(50, 60, 0);
        Bird bird = sky.getBirds().get(0);

        assertEquals(Trajectory.LINEAR, bird.getTrajectory());
    }

    @Test
    @DisplayName("birds should have a angle, set at creation")
    void birdsShouldHaveAngleSetAtCreation() {
        sky.addBird(50, 60, 90);
        Integer birdAngle = sky.getBirds().get(0).getAngle();

        assertEquals(90, birdAngle);
    }

    @Test
    @DisplayName("birds should go straight ahead when trajectory is linear and angle 0")
    void birdsShouldGoStraightAheadWhenTrajectoryIsLinearAndAngle0() {
        sky.addBird(50, 60, 0);

        sky.move();

        Double x = sky.getBirds().get(0).getX();
        Double y = sky.getBirds().get(0).getY();

        assertEquals(61, x);
        assertEquals(50, y);
    }

    @Test
    @DisplayName("birds should go straight ahead when trajectory is linear and angle 90")
    void birdsShouldGoStraightAheadWhenTrajectoryIsLinearAndAngle90() {
        sky.addBird(50, 60, 90);

        sky.move();

        Double x = sky.getBirds().get(0).getX();
        Double y = sky.getBirds().get(0).getY();

        assertEquals(60, x);
        assertEquals(51, y);
    }

    @Test
    @DisplayName("birds should go straight ahead when trajectory is linear and angle 180")
    void birdsShouldGoStraightAheadWhenTrajectoryIsLinearAndAngle180() {
        sky.addBird(50, 60, 180);

        sky.move();

        Double x = sky.getBirds().get(0).getX();
        Double y = sky.getBirds().get(0).getY();

        assertEquals(59, x);
        assertEquals(50, y);
    }

    @Test
    @DisplayName("birds should go straight ahead when trajectory is linear and angle 270")
    void birdsShouldGoStraightAheadWhenTrajectoryIsLinearAndAngle270() {
        sky.addBird(50, 60, 270);

        sky.move();

        Double x = sky.getBirds().get(0).getX();
        Double y = sky.getBirds().get(0).getY();

        assertEquals(60, x);
        assertEquals(49, y);
    }

    @Test
    @DisplayName("birds should bounce on the bottom border of the sky")
    void birdsShouldBounceOnTheBottomBorderOfTheSky() {
        sky.addBird(0, width / 2, 270);

        sky.move();

        Double y = sky.getBirds().get(0).getY();

        assertEquals(1, y);
    }

    @Test
    @DisplayName("birds should bounce angle on left border of the sky, angle 135")
    void birdsShouldBounceOnTheLeftBorderOfTheSkyAndAngle135() {
        sky.addBird(height / 2, 0, 135);

        sky.move();

        Integer angle = sky.getBirds().get(0).getAngle();

        assertEquals(45, angle);
    }

    @Test
    @DisplayName("birds should bounce angle on left border of the sky, angle 190")
    void birdsShouldBounceOnTheLeftBorderOfTheSkyAndAngle190() {
        sky.addBird(height / 2, 0, 190);

        sky.move();

        Integer angle = sky.getBirds().get(0).getAngle();

        assertEquals(350, angle);
    }

    @Test
    @DisplayName("birds should bounce angle on right border of the sky, angle 30")
    void birdsShouldBounceOnTheRightBorderOfTheSkyAndAngle30() {
        sky.addBird(height / 2, width, 30);

        sky.move();

        Integer angle = sky.getBirds().get(0).getAngle();

        assertEquals(150, angle);
    }

    @Test
    @DisplayName("birds should bounce angle on right border of the sky, angle 350")
    void birdsShouldBounceOnTheRightBorderOfTheSkyAndAngle350() {
        sky.addBird(height / 2, width, 350);

        sky.move();

        Integer angle = sky.getBirds().get(0).getAngle();

        assertEquals(190, angle);
    }

    @Test
    @DisplayName("birds should bounce angle on bottom border of the sky, angle 350")
    void birdsShouldBounceOnTheBottomBorderOfTheSkyAndAngle350() {
        sky.addBird(0, width / 2, 350);

        sky.move();

        Integer angle = sky.getBirds().get(0).getAngle();

        assertEquals(10, angle);
    }

    @Test
    @DisplayName("birds should bounce angle on bottom border of the sky, angle 200")
    void birdsShouldBounceOnTheBottomBorderOfTheSkyAndAngle200() {
        sky.addBird(0, width / 2, 182);

        sky.move();

        Integer angle = sky.getBirds().get(0).getAngle();

        assertEquals(178, angle);
    }

    @Test
    @DisplayName("birds should bounce angle on top border of the sky, angle 10")
    void birdsShouldBounceOnTheTopBorderOfTheSkyAndAngle10() {
        sky.addBird(height, width / 2, 10);

        sky.move();

        Integer angle = sky.getBirds().get(0).getAngle();

        assertEquals(350, angle);
    }

    @Test
    @DisplayName("birds should bounce angle on top border of the sky, angle 100")
    void birdsShouldBounceOnTheTopBorderOfTheSkyAndAngle100() {
        sky.addBird(height, width / 2, 100);

        sky.move();

        Integer angle = sky.getBirds().get(0).getAngle();

        assertEquals(260, angle);
    }
    
    @Test
    @DisplayName("A bounced angle should equal to 360 should always be converted to 0")
    void convert360BouncedAngleTo0() {
        sky.addBird(height/2, 0, 180);

        sky.move();

        Integer angle = sky.getBirds().get(0).getAngle();

        assertEquals(0, angle);
    }

}
