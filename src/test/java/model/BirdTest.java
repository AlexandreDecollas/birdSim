package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Bird")
class BirdTest {

    private final Double HEIGHT_LIMIT = 100.0;
    private final Double WIDTH_LIMIT = 200.0;

    @BeforeEach
    void beforeEach() {
// ---
    }

    @Test
    @DisplayName("should have an initial given position and angle")
    void shouldHaveInitialGivenPositionAndAngle() {
        Bird bird = new Bird(10.0, 20.0, 0, WIDTH_LIMIT, HEIGHT_LIMIT);

        assertEquals(bird.getX(), 10.0);
        assertEquals(bird.getY(), 20.0);
        assertEquals(bird.getAngle(), 0);
    }

    @Test
    @DisplayName("should be given its univers size at creation ")
    void shouldHaveUniversSizeAtCreation() {
        Bird bird = new Bird(10.0, 20.0, 0, WIDTH_LIMIT, HEIGHT_LIMIT);

        assertEquals(WIDTH_LIMIT, bird.getUniversWidth());
        assertEquals(HEIGHT_LIMIT, bird.getUniversHeight());
    }

    @Test
    @DisplayName("should own a linear trajectory type by default")
    void birdsShouldOwnATrajectoryType() {
        Bird bird = new Bird(10.0, 20.0, 0, WIDTH_LIMIT, HEIGHT_LIMIT);

        assertEquals(Trajectory.LINEAR, bird.getTrajectory());
    }

    @Test
    @DisplayName("should go straight ahead when trajectory is linear and angle 0")
    void birdsShouldGoStraightAheadWhenTrajectoryIsLinearAndAngle0() {
        Bird bird = new Bird(60.0, 50.0, 0, WIDTH_LIMIT, HEIGHT_LIMIT);

        bird.move(new ArrayList<>());

        assertEquals(bird.getX(), 61);
        assertEquals(bird.getY(), 50);
    }

    @Test
    @DisplayName("should go straight ahead when trajectory is linear and angle 90")
    void birdsShouldGoStraightAheadWhenTrajectoryIsLinearAndAngle90() {
        Bird bird = new Bird(60.0, 50.0, 90, WIDTH_LIMIT, HEIGHT_LIMIT);

        bird.move(new ArrayList<>());

        assertEquals(60, bird.getX());
        assertEquals(51, bird.getY());
    }

    @Test
    @DisplayName("should go straight ahead when trajectory is linear and angle 180")
    void birdsShouldGoStraightAheadWhenTrajectoryIsLinearAndAngle180() {
        Bird bird = new Bird(60.0, 50.0, 180, WIDTH_LIMIT, HEIGHT_LIMIT);

        bird.move(new ArrayList<>());

        assertEquals(50, bird.getY());
        assertEquals(59, bird.getX());

    }

    @Test
    @DisplayName("should go straight ahead when trajectory is linear and angle 270")
    void birdsShouldGoStraightAheadWhenTrajectoryIsLinearAndAngle270() {
        Bird bird = new Bird(60.0, 50.0, 270, WIDTH_LIMIT, HEIGHT_LIMIT);

        bird.move(new ArrayList<>());

        assertEquals(60, bird.getX());
        assertEquals(49, bird.getY());
    }

    @Test
    @DisplayName("should bounce on the bottom border of the sky")
    void birdsShouldBounceOnTheBottomBorderOfTheSky() {
        Bird bird = new Bird(WIDTH_LIMIT / 2, 0.0, 270, WIDTH_LIMIT, HEIGHT_LIMIT);

        bird.move(new ArrayList<>());

        Double y = bird.getY();
        assertEquals(1, y);
    }

    @Test
    @DisplayName("should bounce angle on left border of the sky, angle 135")
    void birdsShouldBounceOnTheLeftBorderOfTheSkyAndAngle135() {
        Bird bird = new Bird(0.0, HEIGHT_LIMIT / 2, 135, WIDTH_LIMIT, HEIGHT_LIMIT);

        bird.move(new ArrayList<>());

        assertEquals(45, bird.getAngle());
    }

    @Test
    @DisplayName("should bounce angle on left border of the sky, angle 190")
    void birdsShouldBounceOnTheLeftBorderOfTheSkyAndAngle190() {
        Bird bird = new Bird(0.0, WIDTH_LIMIT / 2, 190, WIDTH_LIMIT, HEIGHT_LIMIT);

        bird.move(new ArrayList<>());

        assertEquals(350, bird.getAngle());
    }

    @Test
    @DisplayName("should bounce angle on right border of the sky, angle 30")
    void birdsShouldBounceOnTheRightBorderOfTheSkyAndAngle30() {
        Bird bird = new Bird(WIDTH_LIMIT, HEIGHT_LIMIT / 2, 30, WIDTH_LIMIT, HEIGHT_LIMIT);

        bird.move(new ArrayList<>());

        assertEquals(150, bird.getAngle());
    }

    @Test
    @DisplayName("should bounce angle on right border of the sky, angle 350")
    void birdsShouldBounceOnTheRightBorderOfTheSkyAndAngle350() {
        Bird bird = new Bird(WIDTH_LIMIT, HEIGHT_LIMIT / 2, 350, WIDTH_LIMIT, HEIGHT_LIMIT);

        bird.move(new ArrayList<>());

        assertEquals(190, bird.getAngle());
    }

    @Test
    @DisplayName("should bounce angle on bottom border of the sky, angle 350")
    void birdsShouldBounceOnTheBottomBorderOfTheSkyAndAngle350() {
        Bird bird = new Bird(WIDTH_LIMIT / 2, 0.0, 350, WIDTH_LIMIT, HEIGHT_LIMIT);

        bird.move(new ArrayList<>());

        assertEquals(10, bird.getAngle());

    }

    @Test
    @DisplayName("should bounce angle on bottom border of the sky, angle 200")
    void birdsShouldBounceOnTheBottomBorderOfTheSkyAndAngle200() {
        Bird bird = new Bird(WIDTH_LIMIT / 2, 0.0, 182, WIDTH_LIMIT, HEIGHT_LIMIT);

        bird.move(new ArrayList<>());

        assertEquals(178, bird.getAngle());
    }

    @Test
    @DisplayName("should bounce angle on top border of the sky, angle 10")
    void birdsShouldBounceOnTheTopBorderOfTheSkyAndAngle10() {
        Bird bird = new Bird(WIDTH_LIMIT / 2, HEIGHT_LIMIT, 10, WIDTH_LIMIT, HEIGHT_LIMIT);

        bird.move(new ArrayList<>());

        assertEquals(350, bird.getAngle());
    }

    @Test
    @DisplayName("should bounce angle on top border of the sky, angle 100")
    void birdsShouldBounceOnTheTopBorderOfTheSkyAndAngle100() {
        Bird bird = new Bird(WIDTH_LIMIT / 2, HEIGHT_LIMIT, 100, WIDTH_LIMIT, HEIGHT_LIMIT);

        bird.move(new ArrayList<>());

        assertEquals(260, bird.getAngle());
    }
}
