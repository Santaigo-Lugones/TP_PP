package ternilapilli;

public class SlideOState extends GameState {

	public Ternilapilli ternilapilli;
	public String state;

	public SlideOState(Ternilapilli ternilapilli) {
		this.ternilapilli = ternilapilli;
		state = "player O slides and it becomes player Xs turn";
	}

	
	public void putXAt(Position position) {
		ternilapilli.errorOfPuttingWhenStateIsWrong(position);
	}

	public void putOAt(Position position) {
		ternilapilli.errorOfPuttingWhenStateIsWrong(position);
	}

	public void slideXTo(Position actualPos, Position newPos) {
		ternilapilli.errorWhenSpaceAlreadyTakenForX(newPos);
		ternilapilli.errorWhenSpaceAlreadyTakenForO(newPos);
		ternilapilli.errorWhenSpaceIsNotAdjacentOrOnBoard(actualPos, newPos);
		ternilapilli.slideXInCorrectTurn(actualPos, newPos);
		if (ternilapilli.playerXHasWon()) {
			ternilapilli.isOver(); 
		}
	}

	public void slideOTo(Position actualPos, Position newPos) {
		ternilapilli.errorIfXPlaysWhenItsOsTurn(newPos);		
	}

}