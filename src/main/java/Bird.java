public class Bird {

    private Orientation orientation;
    private Position position;

    public Bird(Position position) {
        this.position = position;
        this.orientation = new Orientation();
    }

    public Bird() {
        this.position = new Position(0, 0);
        this.orientation = new Orientation();
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public Position getPosition() {
        return this.position;
    }
    public Position updatePosition(Position position) {
        return this.position = position;
    }

    public Boolean equals(Bird bird) {
        return this.orientation.equals(bird.orientation) && this.position.equals(bird.position);
    }
}
