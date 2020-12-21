import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SkyTest {
    private class SkyWrapper extends Sky {
        public SkyWrapper(JFrame skyFrame) {
            super();
            this.skyFrame = skyFrame;
        }

        public SkyWrapper() {
            super("toto");
        }

        public JFrame getSkyFrame() {
            return skyFrame;
        }

        public SkyFramePicture getSkyFramePicture() {
            return skyFramePicture;
        }
    }

    @Test
    @DisplayName("Should fly birds in sky when adding birds in sky")
    void shouldFlyBirdsInSkyAtInit() {
        Sky sky = new Sky("toto");
        sky.addBird();
        Sky witnessSky = new Sky("toto");

        assertNotEquals(witnessSky, sky);
    }

    @Test
    @DisplayName("should show a sky frame at init")
    void shouldRefreshSkyWhenSimulationRuns() {

        SkyWrapper sky = new SkyWrapper();

        JFrame skyFrame = sky.getSkyFrame();

        assertTrue(skyFrame.isVisible());
    }

    @Test
    @DisplayName("should update sky frame when requested")
    void shouldRefreshSkyWhenRequested() {

        SkyWrapper sky = new SkyWrapper();

        JFrame skyFrameMock = mock(JFrame.class);
        SkyFramePicture skyFramePictureMock = mock(SkyFramePicture.class);
        sky.skyFrame = skyFrameMock;
        sky.skyFramePicture = skyFramePictureMock;

        sky.refreshSkyPicture();

        verify(skyFramePictureMock).paintBirds(sky.getBirds());
    }

    @Test
    @DisplayName("should draw one rectangle for each bird when refresh")
    void shouldDrawARectangleForEachBirdwhenRefresh() {
        SkyWrapper sky = new SkyWrapper();
        sky.addBird();

        SkyFramePicture skyFramePictureMock = mock(SkyFramePicture.class);
        sky.skyFramePicture = skyFramePictureMock;
        try {
            sky.refreshSkyPicture();
        } catch (NullPointerException ignored) {
        } finally {
            verify(skyFramePictureMock).paintBirds(sky.getBirds());
        }
    }

    @Test
    @DisplayName("should termainate program when close simulator windows")
    void shouldTerminateSimulatorWhenClose() {
        SkyWrapper sky = new SkyWrapper();
        assertEquals(sky.getSkyFrame().getDefaultCloseOperation(), EXIT_ON_CLOSE);
    }

    @Test
    @DisplayName("simulator window should have size 400 x 400 at init")
    void shouldSize400x400AtInit() {
        SkyWrapper sky = new SkyWrapper();
        assertEquals(Sky.width + 10, sky.getSkyFrame().getSize().width);
        assertEquals(Sky.height + 10, sky.getSkyFrame().getSize().height);
    }

    @Test
    @DisplayName("Should draw a sky window bigger than the sky size")
    void shouldDrawSkyWindowBiggerThanSkySize() {
        SkyWrapper skyWrapper = new SkyWrapper();
        JFrame skyFrame = skyWrapper.getSkyFrame();

        assertTrue(skyFrame.getWidth() > Sky.width);
        assertTrue(skyFrame.getHeight() > Sky.height);
    }

}
