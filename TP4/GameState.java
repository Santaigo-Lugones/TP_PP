package ternilapilli;

public abstract class GameState {
	public abstract void putXAt(Position position);
	public abstract void putOAt(Position position);
	public abstract void slideXTo(Position actualPos, Position newPos);
	public abstract void slideOTo(Position actualPos, Position newPos);
}