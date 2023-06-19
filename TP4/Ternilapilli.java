package ternilapilli;

import java.util.Set;
import java.util.HashSet;

public class Ternilapilli {
	public static String INVALID_POSITION = "Position is unavailable or nonexistent";
	public static String GAME_IS_OVER = "The game has finished";
	public static String WRONG_STATE = "The state is not correct";
	public static String WRONG_TURN = "Not your turn to play";
	
	public static String X = "X";
	public static String O = "O";
	public Set<Position> allX;
	public Set<Position> allO;
	public String winner = "null";
	public GameState state = new PutOState(this);

	public Ternilapilli() {
		allX = new HashSet<>();
		allO = new HashSet<>();
	}

	public Set<Position> tokensX() {
		return allX;
	}

	public Set<Position> tokensO() {
		return allO;
	}

	public void putXAt(Position position) {
		state.putXAt(position);
	}

	public void putOAt(Position position) {
		state.putOAt(position);
	}

	public void slideXTo(Position actualPos, Position newPos) {
		state.slideXTo(actualPos, newPos);
	}

	public void slideOTo(Position actualPos, Position newPos) {
		state.slideOTo(actualPos, newPos);
	}

	public boolean playerXHasWon() {
		return hasCompletedRowOrColumn(allX) || hasCompletedLeftDiagonal(allX) || hasCompletedRightDiagonal(allX);
	}
	
	public boolean playerOHasWon() {
		return hasCompletedRowOrColumn(allO) || hasCompletedLeftDiagonal(allO) || hasCompletedRightDiagonal(allO);
	}
	
	public void putXInCorrectTurn(Position position) {
		allX.add(position);
		state = new PutXState(this);
	}
	
	public void putOInCorrectTurn(Position position) {
		allO.add(position); 	
		state = new PutOState(this);
	}

	public void slideXInCorrectTurn(Position actualPos, Position newPos) {
		allX.remove(actualPos);
		allX.add(newPos); 
		state = new SlideXState(this);
	}
	
	public void slideOInCorrectTurn(Position actualPos, Position newPos) {
		allO.remove(actualPos);
		allO.add(newPos);
		state = new SlideOState(this);
	}
	
	public void transitionFromPuttingToSliding(Position position) {
		if (allX.size() == 3) { 
			allX.remove(position); 
			state = new SlideOState(this);;
		}
	}
	
	public void errorIfXPlaysWhenItsOsTurn(Position position) {
		throw new RuntimeException(WRONG_TURN);
	}
	
	public void errorIfOPlaysWhenItsXsTurn(Position position) {
		throw new RuntimeException(WRONG_TURN);
	}
	
	public void errorOfPuttingWhenStateIsWrong(Position position) {
		throw new RuntimeException(WRONG_STATE);
	}

	public void errorOfSlidingWhenStateIsWrong(Position actualPos) {
		throw new RuntimeException(WRONG_STATE);		
	}
	
	public void errorWhenSpaceAlreadyTakenForX(Position position) {
		if (allX.contains(position)) {
			throw new RuntimeException(INVALID_POSITION);
		}
	}

	public void errorWhenSpaceAlreadyTakenForO(Position position) {
		if (allO.contains(position)) {
			throw new RuntimeException(INVALID_POSITION);
		}
	}
	
	public void errorWhenSpaceIsNotAdjacentOrOnBoard(Position actualPos, Position newPos) {
		if (!moveLegalFrom(actualPos, newPos)) {
			throw new RuntimeException(INVALID_POSITION);
		}
	}
	
	public void errorIfPlayerPutsOutsideOfBoard(Position position) {
		if (position.getRow() < 1 || position.getRow() > 3 || position.getColumn() < 1 || position.getColumn() > 3) {
			throw new RuntimeException(INVALID_POSITION);
		}
	}
	
	public GameState isOver() {
		return state = new GameOver(this);
	}
	
	private boolean hasCompletedRowOrColumn(Set<Position> list) {
		for (int i = 0; i <= 3; i++) {
			int rowConsidered = i;
			int colConsidered = i;
			int rowCounter = (int) list.stream().filter(p -> p.getRow() == rowConsidered).count();
			int colCounter = (int) list.stream().filter(p -> p.getColumn() == colConsidered).count();
			if (rowCounter == 3 || colCounter == 3) {
				return true;
			}
		}
		return false;
	}

	private boolean hasCompletedRightDiagonal(Set<Position> list) {
		for (int i = 1; i <= 3; i++) {
			if (!list.contains(new Position(i, 4 - i))) {
				return false;
			}
		}
		return true; 
	}

	private boolean hasCompletedLeftDiagonal(Set<Position> list) {
		for (int i = 1; i <= 3; i++) {
			if (!list.contains(new Position(i, i))) {
				return false;
			}
		}
		return true;
	}

	private boolean moveLegalFrom(Position actualPos, Position newPos) {
		return Math.abs(newPos.getRow() - actualPos.getRow()) < 2 && Math.abs(newPos.getColumn() - actualPos.getColumn()) < 2
				&& 0 < newPos.getColumn() && newPos.getColumn() < 4 && 0 < actualPos.getColumn() && actualPos.getColumn() < 4
				&& 0 < newPos.getRow() && newPos.getRow() < 4 && 0 < actualPos.getRow() && actualPos.getRow() < 4;
	}
}