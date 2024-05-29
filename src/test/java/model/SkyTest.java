package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
