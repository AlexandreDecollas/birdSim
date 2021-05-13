package view;

import view.Orientation;
import view.Position;

import java.awt.*;

public interface ILifeEntityShape {
    void draw(Orientation orientation, Position position, Graphics graphic);
}
