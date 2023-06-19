package ternilapilli;

public class GameOver extends GameState {
	public Ternilapilli ternilapilli;
	public String state;
	public GameOver(Ternilapilli ternilapilli) {
		this.ternilapilli = ternilapilli;
        state = "Game Over";
	}
	
	public void putXAt(Position position) {
		throw new RuntimeException(ternilapilli.GAME_IS_OVER);
	}

	public void putOAt(Position position) {
		throw new RuntimeException(ternilapilli.GAME_IS_OVER);
	} 

	public void slideXTo(Position actualPos, Position newPos) {
		throw new RuntimeException(ternilapilli.GAME_IS_OVER);
	}

	public void slideOTo(Position actualPos, Position newPos) {
		throw new RuntimeException(ternilapilli.GAME_IS_OVER);
	}
}