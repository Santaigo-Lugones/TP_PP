package MarsRover;

public class West extends Direction {

    @Override
    public void moveForward(Position pos) {
        pos.backX();
    }

    @Override
    public void moveBack(Position pos) {
        pos.advanceX();
    }

    @Override
    public void rotateLeft(Position pos) {
        pos.setDir(new South());
    }

    @Override
    public void rotateRight(Position pos) {
        pos.setDir(new North());
    }

    @Override
    public String toString() {
        return "West";
    }
}