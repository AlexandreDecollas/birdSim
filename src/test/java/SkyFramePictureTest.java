import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class SkyFramePictureWrapper extends SkyFramePicture {

    public SkyFramePictureWrapper() {
        super(400, 400);
    }


}
public class SkyFramePictureTest {

    @Test
    @DisplayName("should draw no shapes when init")
    void shouldDrawNoShapesWheninit() {
        SkyFramePicture skyFramePicture = new SkyFramePicture(400, 400);
        Graphics graphicsMock = mock(Graphics.class);

        skyFramePicture.paintComponent(graphicsMock);

        verify(graphicsMock, never()).drawRect(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    @DisplayName("should draw shapes at correct position")
    void shouldDrawShapesAtCorrectPositions() {

        List<Position> positions = new ArrayList<Position>();

        positions.add(new Position(400, 400));
        positions.add(new Position(400, 400));

        SkyFramePicture skyFramePicture = new SkyFramePicture(positions, 400, 400);
        Graphics graphicsMock = mock(Graphics.class);

        skyFramePicture.paintComponent(graphicsMock);

        verify(graphicsMock, times(2))
                .drawRect(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    @DisplayName("should draw one rectangle for one birds when asked")
    void shouldDrawBirdsAtRefresh() {
        SkyFramePictureWrapper skyFramePictureWrapper = new SkyFramePictureWrapper();
        List<Bird> birds = new ArrayList<Bird>();
        birds.add(new Bird());
        birds.add(new Bird());
        birds.add(new Bird());

        skyFramePictureWrapper.paintBirds(birds);
        Graphics graphicsMock = mock(Graphics.class);

        skyFramePictureWrapper.paintComponent(graphicsMock);
        verify(graphicsMock, times(3)).drawRect(anyInt(), anyInt(), anyInt(), anyInt());
    }
}
