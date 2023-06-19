package ternilapilli;

public class PutOState extends GameState{

	public Ternilapilli ternilapilli;
	public String state;

	public PutOState(Ternilapilli ternilapilli) {
		this.ternilapilli = ternilapilli;
		state = "player O puts and it becomes player Xs turn";
	}

	public void putXAt(Position position) {
		ternilapilli.errorWhenSpaceAlreadyTakenForX(position);
		ternilapilli.errorWhenSpaceAlreadyTakenForO(position);
		ternilapilli.errorIfPlayerPutsOutsideOfBoard(position);
		ternilapilli.putXInCorrectTurn(position);
		if (ternilapilli.playerXHasWon()) {
			ternilapilli.isOver();
		} 
	}

	public void putOAt(Position position) {
		ternilapilli.errorIfXPlaysWhenItsOsTurn(position);
	} 

	public void slideXTo(Position actualPos, Position newPos) {
		ternilapilli.errorOfSlidingWhenStateIsWrong(newPos);
	}

	public void slideOTo(Position actualPos, Position newPos) {
		ternilapilli.errorOfSlidingWhenStateIsWrong(newPos);
		
	}

}