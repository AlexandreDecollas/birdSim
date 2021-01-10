import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class SkyFramePictureWrapper extends SkyFramePicture {

    public SkyFramePictureWrapper() {
        super();
    }

    public Integer getTopMargin() {
        return SimulatorConstants.TOP_MARGIN_IN_PX;
    }

    public Integer getLeftMargin() {
        return SimulatorConstants.LEFT_MARGIN_IN_PX;
    }

    public Integer getBottomMargin() {
        return SimulatorConstants.BOTTOM_MARGIN_IN_PX;
    }

    public Integer getRightMargin() {
        return SimulatorConstants.RIGHT_MARGIN_IN_PX;
    }
}

public class SkyFramePictureTest {

    @Test
    @DisplayName("should draw only sky limit shape when init")
    void shouldDrawNoShapesWhenInit() {
        SkyFramePicture skyFramePicture = new SkyFramePicture();
        Graphics graphicsMock = mock(Graphics.class);

        skyFramePicture.paintComponent(graphicsMock);

        verify(graphicsMock, times(1)).drawRect(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    @DisplayName("should draw shapes at correct position")
    void shouldDrawShapesAtCorrectPositions() {
        List<Bird> birds = new ArrayList<Bird>();

        Bird bird1 = new Bird(400, 400);
        Bird bird2 = new Bird(400, 400);

        bird1.updatePosition(new Position(400, 400));
        bird2.updatePosition(new Position(400, 400));

        birds.add(bird1);
        birds.add(bird2);

        SkyFramePicture skyFramePicture = new SkyFramePicture(birds);
        Graphics graphicsMock = mock(Graphics.class);

        skyFramePicture.paintComponent(graphicsMock);

        verify(graphicsMock, times(2))
                .drawPolygon(Matchers.<int[]>any(), Matchers.<int[]>any(), anyInt());
    }

    @Test
    @DisplayName("should draw one rectangle for one birds when asked")
    void shouldDrawBirdsAtRefresh() {
        SkyFramePictureWrapper skyFramePictureWrapper = new SkyFramePictureWrapper();
        List<Bird> birds = new ArrayList<Bird>();
        birds.add(new Bird(400, 400));
        birds.add(new Bird(400, 400));
        birds.add(new Bird(400, 400));

        skyFramePictureWrapper.paintBirds(birds);
        Graphics graphicsMock = mock(Graphics.class);

        skyFramePictureWrapper.paintComponent(graphicsMock);
        verify(graphicsMock, times(3)).drawPolygon(Matchers.<int[]>any(), Matchers.<int[]>any(), anyInt());
    }

    @Test
    @DisplayName("should show one triangle for each bird, with head at bird position")
    void shouldShowTriangleAtBirdPosition() {
        SkyFramePictureWrapper skyFramePictureWrapper = new SkyFramePictureWrapper();
        List<Bird> birds = new ArrayList<Bird>();
        Bird bird = new Bird(400, 400);

        bird.getPosition().x = 200;
        bird.getPosition().y = 200;
        bird.getOrientation().setValue(0);

        birds.add(bird);

        skyFramePictureWrapper.paintBirds(birds);
        Graphics graphicsMock = mock(Graphics.class);

        skyFramePictureWrapper.paintComponent(graphicsMock);
        verify(graphicsMock, times(1))
                .drawPolygon(Matchers.<int[]>any(), Matchers.<int[]>any(), anyInt());
    }

    @Test
    @DisplayName("should draw a rectangle for sky delimitation")
    void shouldDrawSkyDelimitation() {
        SkyFramePictureWrapper skyFramePictureWrapper = new SkyFramePictureWrapper();
        List<Bird> birds = new ArrayList<Bird>();
        skyFramePictureWrapper.paintBirds(birds);
        Graphics graphicsMock = mock(Graphics.class);

        skyFramePictureWrapper.paintComponent(graphicsMock);
        verify(graphicsMock, times(1))
                .drawRect(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    @DisplayName("should draw a rectangle for sky delimitation with margins")
    void shouldDrawSkyDelimitationWithMargins() {
        SkyFramePictureWrapper skyFramePictureWrapper = new SkyFramePictureWrapper();
        List<Bird> birds = new ArrayList<Bird>();
        skyFramePictureWrapper.paintBirds(birds);
        Graphics graphicsMock = mock(Graphics.class);

        skyFramePictureWrapper.paintComponent(graphicsMock);
        verify(graphicsMock, times(1))
                .drawRect(
                        skyFramePictureWrapper.getTopMargin(),
                        skyFramePictureWrapper.getLeftMargin(),
                        skyFramePictureWrapper.getSize().width
                                - skyFramePictureWrapper.getLeftMargin()
                                - skyFramePictureWrapper.getRightMargin(),
                        skyFramePictureWrapper.getSize().height
                                - skyFramePictureWrapper.getTopMargin()
                                - skyFramePictureWrapper.getBottomMargin()
                );
    }

    @Test
    @DisplayName("Should draw birds only in the sky delimitation")
    void shouldDrawBirdsOnlyInSkyDelimitation() {
        SkyFramePictureWrapper skyFramePictureWrapper = new SkyFramePictureWrapper();
        skyFramePictureWrapper.setSize(100, 100);
        List<Bird> birds = new ArrayList<Bird>();
        Bird bird1 = new Bird(100, 100);
        bird1.getPosition().x = 0;
        bird1.getPosition().y = 0;
        bird1.getOrientation().setValue(0);

        Bird bird2 = new Bird(100, 100);
        bird2.getPosition().x = 100 - skyFramePictureWrapper.getLeftMargin() - skyFramePictureWrapper.getRightMargin();
        bird2.getPosition().y = 100 - skyFramePictureWrapper.getTopMargin() - skyFramePictureWrapper.getBottomMargin();
        bird2.getOrientation().setValue(0);

        birds.add(bird2);

        skyFramePictureWrapper.paintBirds(birds);
        Graphics graphicsMock = mock(Graphics.class);

        skyFramePictureWrapper.paintComponent(graphicsMock);
        verify(graphicsMock, times(1))
                .drawPolygon(
                        new int[]{anyInt(), skyFramePictureWrapper.getTopMargin()},
                        new int[]{anyInt(), skyFramePictureWrapper.getLeftMargin()},
                        anyInt());
        verify(graphicsMock, times(1))
                .drawPolygon(
                        new int[]{
                                anyInt(),
                                100 - skyFramePictureWrapper.getLeftMargin() - skyFramePictureWrapper.getRightMargin()
                        },
                        new int[]{
                                anyInt(),
                                100 - skyFramePictureWrapper.getTopMargin() - skyFramePictureWrapper.getBottomMargin()
                        },
                        anyInt());
    }

    @Test
    @DisplayName("should draw a triangle per bird, horizontal right orientation")
    void shouldDrawTrianglePerBird() {
        SkyFramePictureWrapper skyFramePictureWrapper = new SkyFramePictureWrapper();
        skyFramePictureWrapper.setSize(100, 100);
        List<Bird> birds = new ArrayList<Bird>();
        Bird bird = new Bird(100, 100);
        bird.getPosition().x = 50;
        bird.getPosition().y = 50;
        bird.getOrientation().setValue(0);

        birds.add(bird);

        skyFramePictureWrapper.paintBirds(birds);
        Graphics graphicsMock = mock(Graphics.class);

        skyFramePictureWrapper.paintComponent(graphicsMock);
        double angleInRad1 = Math.toRadians((bird.getOrientation().getValue() - 0.05) * 360);
        double angleInRad2 = Math.toRadians((bird.getOrientation().getValue() + 0.05) * 360);
        double x1 = bird.getPosition().x - Math.cos(angleInRad1) * 10 + SimulatorConstants.LEFT_MARGIN_IN_PX;
        double x2 = bird.getPosition().x + SimulatorConstants.LEFT_MARGIN_IN_PX;
        double y1 = bird.getPosition().y + Math.sin(angleInRad1) * 10 + SimulatorConstants.TOP_MARGIN_IN_PX;
        double y2 = bird.getPosition().y + SimulatorConstants.TOP_MARGIN_IN_PX;
        double x3 = bird.getPosition().x - Math.cos(angleInRad2) * 10 + SimulatorConstants.LEFT_MARGIN_IN_PX;
        double x4 = bird.getPosition().x + SimulatorConstants.LEFT_MARGIN_IN_PX;
        double y3 = bird.getPosition().y + Math.sin(angleInRad2) * 10 + SimulatorConstants.TOP_MARGIN_IN_PX;
        double y4 = bird.getPosition().y + SimulatorConstants.TOP_MARGIN_IN_PX;

        verify(graphicsMock, times(1))
                .drawPolygon(
                        new int[]{(int) x1, (int) x2, (int) x3, (int) x4},
                        new int[]{(int) y1, (int) y2, (int) y3, (int) y4},
                        3);
    }
}
