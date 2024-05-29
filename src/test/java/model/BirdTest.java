package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static constants.SimulatorConstants.ANGLE_TOWARDING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Bird")
class BirdTest {

    private final Double HEIGHT_LIMIT = 100.0;
    private final Double WIDTH_LIMIT = 200.0;

    @Nested
    @DisplayName("When the bird is alone")
    class SoloBird {


        @Test
        @DisplayName("should have an initial given position and angle")
        void shouldHaveInitialGivenPositionAndAngle() {
            Bird bird = new Bird(10.0, 20.0, 0, WIDTH_LIMIT, HEIGHT_LIMIT);

            assertEquals(bird.getX(), 10.0);
            assertEquals(bird.getY(), 20.0);
            assertEquals(bird.getAngle(), 0);
        }

        @Test
        @DisplayName("should be given the sky size at creation ")
        void shouldBeGivenSkySizeAtCreation() {
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

        @Test
        @DisplayName("should move bird at each iteration")
        void shouldMoveBirdsAtEachIteration() {
            Bird bird = new Bird(60.0, 50.0, 0, WIDTH_LIMIT, HEIGHT_LIMIT);

            bird.move(new ArrayList<>());

            assertNotEquals(50.0 + 60.0, bird.getX() + bird.getY());
        }

        @Test
        @DisplayName("A bounced angle that equal to 360 should always be converted to 0")
        void convert360BouncedAngleTo0() {
            Bird bird = new Bird(0.0, HEIGHT_LIMIT / 2, 180, WIDTH_LIMIT, HEIGHT_LIMIT);

            bird.move(new ArrayList<>());

            assertEquals(0, bird.getAngle());
        }
    }

    @Nested
    @DisplayName("When birds are in group")
    class MultipleBirds {

        @Test
        @DisplayName("bird should not interact with themselves when they are in the other bird list")
        void convert360BouncedAngleTo0() {
            Bird bird = new Bird(10.0, 10.0, 0, WIDTH_LIMIT, HEIGHT_LIMIT);
            List<Bird> birds = new ArrayList<>();
            birds.add(bird);

            bird.move(birds);

            assertEquals(0, bird.getAngle());
        }

        @Nested
        @DisplayName("When The distance is > 10")
        class BirdAreDistant {

            @DisplayName("should orient its angle toward other birds when other bird")
            @ParameterizedTest
            @MethodSource("provideBirdsOrientationData")
            void shouldIncreaseAngleWhenOtherBirdIsIntItBottomLeft(
                    Double birdX,
                    Double birdY,
                    Integer birdAngle,
                    Double otherBirdX,
                    Double otherBirdY,
                    Integer expectedAngle
            ) {
                Bird bird = new Bird(birdX, birdY, birdAngle, WIDTH_LIMIT, HEIGHT_LIMIT);
                Bird otherBird = new Bird(otherBirdX, otherBirdY, 0, WIDTH_LIMIT, HEIGHT_LIMIT);
                List<Bird> otherBirds = new ArrayList<>();
                otherBirds.add(otherBird);

                bird.move(otherBirds);

                assertEquals(expectedAngle, bird.getAngle());
            }

            static Stream<Arguments> provideBirdsOrientationData() {
                return Stream.of(
                        Arguments.of(25.0, 25.0, 0, 40.0, 40.0, ANGLE_TOWARDING),
                        Arguments.of(25.0, 25.0, 0, 10.0, 10.0, 360 - ANGLE_TOWARDING),
                        Arguments.of(25.0, 25.0, 0, 40.0, 10.0, 360 - ANGLE_TOWARDING),
                        Arguments.of(25.0, 25.0, 0, 10.0, 40.0, ANGLE_TOWARDING)
                );
            }
        }
    }
}
