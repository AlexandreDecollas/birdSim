import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BirdTest {

    @Test
    @DisplayName("Should have an oscilling trajectory")
    void shouldMoveByOscillations() {
        Bird bird = new Bird(400, 400);
        bird.getOrientation().setValue(0);
        bird.getPosition().x = 200;
        bird.getPosition().y = 200;

        Position originalVariation1 = bird.getPosition();

        bird.move();
        Position originalVariation2 = bird.getPosition();

        bird.move();
        Position originalVariation3 = bird.getPosition();

        bird.move();
        Position originalVariation4 = bird.getPosition();

        bird.move();
        Position originalVariation5 = bird.getPosition();

        assertEquals(204, originalVariation1.x);
        assertEquals(200, originalVariation1.y);
        assertEquals(204, originalVariation2.x);
        assertEquals(200, originalVariation2.y);
        assertEquals(204, originalVariation3.x);
        assertEquals(200, originalVariation3.y);
        assertEquals(204, originalVariation4.x);
        assertEquals(200, originalVariation4.y);
        assertEquals(204, originalVariation5.x);
        assertEquals(200, originalVariation5.y);
    }

    @Test
    @DisplayName("Should have a default speed")
    void shouldSetASpeed() {
        Bird bird = new Bird(500, 500);
        assertEquals(1, bird.speedInMBS);
    }

    @Test
    @DisplayName("Should be able to change speed")
    void shouldBeAbleToChangeSpeed() {
        Bird bird = new Bird(500, 500);
        bird.getPosition().x = 250;
        bird.getPosition().y = 250;
        bird.getOrientation().setValue(0);
        bird.speedInMBS = 2;

        bird.move();

        assertEquals(252, bird.getPosition().x);
    }
}
