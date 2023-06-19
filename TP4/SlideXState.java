package ternilapilli;

public class SlideXState extends GameState {

	public Ternilapilli ternilapilli;
	public String state;

	public SlideXState(Ternilapilli ternilapilli) {
		this.ternilapilli = ternilapilli;
		state = "player X slides and it becomes player Os turn";
	}

	
	public void putXAt(Position position) {
		ternilapilli.errorOfPuttingWhenStateIsWrong(position);
	}

	public void putOAt(Position position) {
		ternilapilli.errorOfPuttingWhenStateIsWrong(position);
	}

	public void slideXTo(Position actualPos, Position newPos) {
		ternilapilli.errorIfOPlaysWhenItsXsTurn(newPos);	
	}

	public void slideOTo(Position actualPos, Position newPos) {
		ternilapilli.errorWhenSpaceAlreadyTakenForX(newPos);
		ternilapilli.errorWhenSpaceAlreadyTakenForO(newPos); 
		ternilapilli.errorWhenSpaceIsNotAdjacentOrOnBoard(actualPos, newPos);
		ternilapilli.slideOInCorrectTurn(actualPos, newPos);
		if (ternilapilli.playerOHasWon()) {
			ternilapilli.isOver();
		}
	}

}