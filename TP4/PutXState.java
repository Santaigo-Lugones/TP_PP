package ternilapilli;

public class PutXState extends GameState {

	public Ternilapilli ternilapilli;
	public String state;

	public PutXState(Ternilapilli ternilapilli) {
		this.ternilapilli = ternilapilli;
		state = "player X puts and it becomes player Os turn";
	}

	public void putXAt(Position position) {
		ternilapilli.errorIfOPlaysWhenItsXsTurn(position);
	}

	public void putOAt(Position position) {
		ternilapilli.errorWhenSpaceAlreadyTakenForX(position);
		ternilapilli.errorWhenSpaceAlreadyTakenForO(position);
		ternilapilli.errorIfPlayerPutsOutsideOfBoard(position);
		ternilapilli.putOInCorrectTurn(position);
		ternilapilli.transitionFromPuttingToSliding(position);
		if (ternilapilli.playerOHasWon()) {
			ternilapilli.isOver();
		} 
	}

	public void slideXTo(Position actualPos, Position newPos) {
		ternilapilli.errorOfSlidingWhenStateIsWrong(newPos);
	}

	public void slideOTo(Position actualPos, Position newPos) {
		ternilapilli.errorOfSlidingWhenStateIsWrong(newPos);
		
	}

}