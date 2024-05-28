package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Sky")
class SkyTest {

    private final Double height = 100.0;
    private final Double width = 200.0;
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
        sky.addBird(50.0, 60.0, 0);

        assertEquals(50.0, sky.getBirds().get(0).getY());
        assertEquals(60.0, sky.getBirds().get(0).getX());
    }

    @Test
    @DisplayName("should be able to add multiple birds in the sky")
    void shouldAddMultipleBirdsInTheSky() {
        sky.addBird(50.0, 60.0, 0);
        sky.addBird(50.0, 60.0, 0);
        sky.addBird(50.0, 60.0, 0);
        sky.addBird(50.0, 60.0, 0);

        assertEquals(4, sky.getBirds().size());
    }

    @Test
    @DisplayName("should move bird at each iteration")
    void shouldMoveBirdsAtEachIteration() {
        sky.addBird(50.0, 60.0, 0);

        sky.move();

        Double x = sky.getBirds().get(0).getX();
        Double y = sky.getBirds().get(0).getY();

        assertNotEquals(50 + 60.0, x + y);
    }


    @Test
    @DisplayName("A bounced angle that equal to 360 should always be converted to 0")
    void convert360BouncedAngleTo0() {
        sky.addBird(height / 2, 0.0, 180);

        sky.move();

        Integer angle = sky.getBirds().get(0).getAngle();

        assertEquals(0, angle);
    }

    @Test
    @DisplayName("birds should aim to orient itself toward the closest bird when distance > 10")
    void birdsShouldAimToorientItselfTowardClosestBird() {
        sky.addBird(50.0, 10.0, 180);
        sky.addBird(61.0, 10.0, 180);

        sky.move();

        Integer angleBird1 = sky.getBirds().get(0).getAngle();

        assertEquals(177, angleBird1);
    }

    @Test
    @DisplayName("birds should aim to orient itself toward the closest bird when distance > 10 with inverted values")
    void birdsShouldAimToorientItselfTowardClosestBirdWithInvertedValues() {
        sky.addBird(50.0, 100.0, 180);
        sky.addBird(61.0, 100.0, 180);

        sky.move();

        Integer angleBird1 = sky.getBirds().get(1).getAngle();

        assertEquals(177, angleBird1);
    }

    @Test
    @DisplayName("birds should aim to orient itself backward the closest bird when distance <= 10")
    void birdsShouldAimToorientItselfBackwardClosestBird() {
        sky.addBird(50.0, 10.0, 180);
        sky.addBird(55.0, 10.0, 180);

        sky.move();

        Integer angleBird1 = sky.getBirds().get(0).getAngle();

        assertEquals(183, angleBird1);
    }

    @Test
    @DisplayName("birds should aim to orient itself backward the closest bird when distance <= 10 with inverted values")
    void birdsShouldAimToorientItselfBackwardClosestBirdWithInvertedValues() {
        sky.addBird(55.0, 10.0, 180);
        sky.addBird(50.0, 10.0, 180);

        sky.move();

        Integer angleBird1 = sky.getBirds().get(1).getAngle();

        assertEquals(183, angleBird1);
    }
}
