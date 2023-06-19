package ternilapilli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Test;

public class TernilapilliTest {
	@Test
	void test00GameStartsWithNoTokens() {
		Ternilapilli game = emptyGame();
				
		assertTrue(game.tokensX().isEmpty());
		assertTrue(game.tokensO().isEmpty());
	}

	@Test
	void test01GameStartsWithX() {
		Ternilapilli game = emptyGame();
		
		assertThrowsLike(Ternilapilli.WRONG_TURN, () -> game.putOAt(new Position(1,1)));
	}
	
	@Test
	void test02GameWithOneTokenOfEachPlayer() {
		Ternilapilli game = gameWithXandO();
		
		assertEquals(1, game.tokensX().size());
		assertEquals(1, game.tokensO().size());
		assertTrue(game.tokensX().contains(new Position(1, 1)));
		assertTrue(game.tokensO().contains(new Position(1, 2)));
	}
	
	@Test
	void test03CannotPlayTwoConsecutiveTimesX() {
		Ternilapilli game = emptyGame();
		game.putXAt(new Position(1, 1));
		
		assertThrowsLike(Ternilapilli.WRONG_TURN, () -> game.putXAt(new Position(2,2)));
		assertEquals(1, game.tokensX().size());
	}
	
	@Test
	void test04CannotPutTwoConsecutiveTimesO() {
		Ternilapilli game = gameWithXandO();
		
		assertThrowsLike(Ternilapilli.WRONG_TURN, () -> game.putOAt(new Position(2,2)));
		assertEquals(1, game.tokensO().size());
	}
	
	@Test
	void test05CannotPutXInOccupiedPosition() {
		Ternilapilli game = gameWithXandO();
		
		assertThrowsLike(Ternilapilli.INVALID_POSITION, () -> game.putXAt(new Position(1,1)));
		assertThrowsLike(Ternilapilli.INVALID_POSITION, () -> game.putXAt(new Position(1,2)));
		assertEquals(1, game.tokensX().size());
	}
	
	@Test
	void test06CannotPutOInOccupiedPosition() {
		Ternilapilli game = gameWithXandO();
		game.putXAt(new Position(3, 3));
		
		assertThrowsLike(Ternilapilli.INVALID_POSITION, () -> game.putOAt(new Position(1,2)));
		assertThrowsLike(Ternilapilli.INVALID_POSITION, () -> game.putOAt(new Position(1,1)));
		assertEquals(1, game.tokensO().size());
	}
	
	@Test
	void test07CannotPutTokensOutsideOfTheBoard() {
		Ternilapilli game = emptyGame();
		
		assertThrowsLike(Ternilapilli.INVALID_POSITION, () -> game.putXAt(new Position(10,10)));
		assertTrue(game.tokensX().isEmpty());
	}
	
	@Test
	void test08CannotPutFourTokens() {
		Ternilapilli game = fullGame();
		
		assertThrowsLike(Ternilapilli.WRONG_STATE, () -> game.putXAt(new Position(3, 1)));
		assertEquals(3, game.tokensX().size());
	}
	
	@Test
	void test09GameWithNoWinner() {
		Ternilapilli game = gameWithXandO();
		
		assertFalse(game.playerXHasWon());
		assertFalse(game.playerOHasWon());
	}
	
	@Test
	void test10GameWithWinnerByRow() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 3));
		
		assertTrue(game.playerXHasWon());
		assertFalse(game.playerOHasWon());
	}
	
	@Test
	void test11GameWithWinnerByColumn() {
		Ternilapilli game = gameWithXandO();
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 3));
		game.putOAt(new Position(3, 2));
		
		assertTrue(game.playerOHasWon());
		assertFalse(game.playerXHasWon());
	}
	
	@Test
	void test12GameWithWinnerByDiagonal() {
		Ternilapilli game = gameWithXandO();
		game.putXAt(new Position(2, 2));
		game.putOAt(new Position(1, 3));
		game.putXAt(new Position(3, 3));
		
		assertTrue(game.playerXHasWon());
		assertFalse(game.playerOHasWon());
	}
	
	@Test
	void test13CannotPlayWhenGameIsOver() {
		Ternilapilli game = gameWithXandO();
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(3, 1));
		
		assertThrowsLike(Ternilapilli.GAME_IS_OVER, () -> game.putOAt(new Position(3, 3)));
		assertEquals(2, game.tokensO().size());
	}
	
	@Test
	void test14PlayersCanSlideAToken() {
		Ternilapilli game = fullGame();
		game.slideXTo(new Position(3, 2), new Position(3, 3));
		game.slideOTo(new Position(2, 3), new Position(1, 3));
		
		assertEquals(3, game.tokensX().size());
		assertTrue(game.tokensX().contains(new Position(3, 3)));
		assertFalse(game.tokensX().contains(new Position(3, 2)));
		assertEquals(3, game.tokensO().size());
		assertTrue(game.tokensO().contains(new Position(1, 3)));
		assertFalse(game.tokensO().contains(new Position(2, 3)));
	}
	
	@Test
	void test15CannotSlideWithoutFullGame() {
		Ternilapilli game = gameWithXandO();
		
		assertThrowsLike( Ternilapilli.WRONG_STATE, () -> game.slideXTo(new Position(1, 2), new Position(2, 1)));
	}
	
	
	@Test
	void test16CannotSlideTwoConsecutiveTimesX() {
		Ternilapilli game = fullGame();
		game.slideXTo(new Position(3, 2), new Position(3, 3));
		
		assertThrowsLike( Ternilapilli.WRONG_TURN, () -> game.slideXTo(new Position(2, 1), new Position(3, 1)));
	}
	
	@Test
	void test17CannotSlideTwoConsecutiveTimesO() {
		Ternilapilli game = fullGame();
		game.slideXTo(new Position(3, 2), new Position(3, 3));
		game.slideOTo(new Position(2, 3), new Position(1, 3));
		
		assertThrowsLike( Ternilapilli.WRONG_TURN, () -> game.slideOTo(new Position(2, 2), new Position(3, 2)));
	}
	
	@Test
	void test18CannotSlideXToOccupiedPosition() {
		Ternilapilli game = fullGame();
		
		assertThrowsLike( Ternilapilli.INVALID_POSITION, () -> game.slideXTo(new Position(3, 2), new Position(2, 2)));
		assertThrowsLike( Ternilapilli.INVALID_POSITION, () -> game.slideXTo(new Position(1, 1), new Position(2, 1)));
	}
	
	@Test
	void test19CannotSlideOToOccupiedPosition() {
		Ternilapilli game = fullGame();
		game.slideXTo(new Position(3, 2), new Position(3, 3));
		
		assertThrowsLike( Ternilapilli.INVALID_POSITION, () -> game.slideOTo(new Position(1, 2), new Position(1, 1)));
		assertThrowsLike( Ternilapilli.INVALID_POSITION, () -> game.slideOTo(new Position(2, 1), new Position(2, 3)));
	
	}
	
	@Test
	void test20CannotSlideNonExistentToken() {
		Ternilapilli game = fullGame();
		
		assertThrowsLike( Ternilapilli.INVALID_POSITION, () -> game.slideXTo(new Position(3, 1), new Position(2, 1)));
	}
	@Test
	void test21CannotSlideToAnIllegalPosition() {
		Ternilapilli game = fullGame();
		
		assertThrowsLike( Ternilapilli.INVALID_POSITION, () -> game.slideXTo(new Position(3, 2), new Position(1, 3)));
		assertThrowsLike( Ternilapilli.INVALID_POSITION, () -> game.slideXTo(new Position(3, 2), new Position(4, 2)));
	}
	
	@Test
	void test22GameWithXWinnerBySliding() {
		Ternilapilli game = fullGame();
		game.slideXTo(new Position(3, 2), new Position(3, 1));
		
		assertTrue(game.playerXHasWon());
		assertFalse(game.playerOHasWon());
	}
	
	@Test
	void test23GameWithOWinnerBySliding() {
		Ternilapilli game = fullGame();
		game.slideXTo(new Position(2, 1), new Position(3, 1));
		game.slideOTo(new Position(2, 2), new Position(2, 1));
		game.slideXTo(new Position(3, 2), new Position(3, 3));
		game.slideOTo(new Position(1, 2), new Position(2, 2));
		
		assertTrue(game.playerOHasWon());
		assertFalse(game.playerXHasWon());
	}
	
	@Test
	void test24CannotSlideWhenGameIsOver() {
		Ternilapilli game = fullGame();
		game.slideXTo(new Position(3, 2), new Position(3, 1));
		
		assertThrowsLike( Ternilapilli.GAME_IS_OVER, () -> game.slideOTo(new Position(1, 2), new Position(1, 3)));
	}
	
	private Ternilapilli emptyGame() {
		return new Ternilapilli();
	}
	
	private Ternilapilli gameWithXandO() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(1, 2));
		return game;
	}
	
	private Ternilapilli fullGame() {
		Ternilapilli game = gameWithXandO();
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(2, 3));
		return game;
	}
	
	private void assertThrowsLike(String msg, Executable executable) {
		assertEquals( msg, assertThrows( Exception.class, executable).getMessage() );
	}
}