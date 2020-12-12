import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SkyTest {
    @Test
    @DisplayName("Should fly birds in sky when adding birds in sky")
    void shouldFlyBirdsInSkyAtInit() {
        Sky sky = new Sky("toto");
        sky.addBird();
        Sky witnessSky = new Sky("toto");

        assertNotEquals(witnessSky, sky);
    }

    @Test
    @DisplayName("should update birds position in sky when iterate")
    void shouldRefreshSkyWhenSimulationRuns() {

        Sky sky = new Sky("toto");

        List<Bird> birds = new ArrayList<Bird>();

        birds.add(new Bird());
        birds.add(new Bird());

        sky.flyBirds();

    }

}
