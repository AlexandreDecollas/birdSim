public class Position {

    public double x;
    public double y;

    public Position(Integer containerWidth, Integer containerHeight) {
        this.x = Math.round(Math.random() * containerWidth);
        this.y = Math.round(Math.random() * containerHeight);
    }

    public Boolean equals(Position position) {
        return x == position.x && y == position.y;
    }
}
