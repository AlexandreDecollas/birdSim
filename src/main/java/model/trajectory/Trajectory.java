package model.trajectory;

import model.Bird;
import model.Sky;
import view.Position;

public abstract class Trajectory {

    static Position computeNextPosition(Bird bird, Sky sky) {
        return new Position(sky.getSkyWidth(), sky.getSkyHeight());
    }

}
