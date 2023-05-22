package MarsRover;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarsRoverTest {
	
	@Test
	public void initialPositionTest() {
		assertEquals(myRover().getPosition(), new Position(new North(),0,0));
	}
	
	@Test
	public void invalidCommand() {
		assertThrows(InvalidCommandException.class, () -> myRover().move('x'));
	}
	
	@Test
	public void forwardTest() {
		assertEquals(myRover().move('f').getPosition(), new Position(new North(),0,1));
	}
	
	@Test
	public void backwardsTest() {
		assertEquals(myRover().move('b').getPosition(), new Position(new North(),0,-1));
	}
	
	@Test
	public void leftRotationTest() {
		assertEquals(myRover().move('l').getPosition(), new Position(new West(),0,0));
	}
	
	@Test
	public void rightRotationTest() {
		assertEquals(myRover().move('r').getPosition(),new Position(new East(),0,0));
	}
	
	@Test
	public void manyRotationsTest() {
		String rotations = "llrrrlllrlr";
		assertEquals(myRover().processCommands(rotations).getPosition(), new Position(new West(),0,0));
	}
	
	@Test
	public void manyMovementsTest() {
		String movements = "fffbffbf";
		assertEquals(myRover().processCommands(movements).getPosition(), new Position(new North(),0,4));
	}
	
	@Test
	public void mixedCommandsTest() {
		String leftPlusForward = "lff";
		String rightPlusBackwards = "rbb";
		assertEquals(myRover().processCommands(leftPlusForward).getPosition(),new Position(new West(),-2,0));
		assertEquals(myRover().processCommands(rightPlusBackwards).getPosition(),new Position(new East(),-2,0));
		assertEquals(myRover().processCommands(leftPlusForward + leftPlusForward).getPosition(),new Position(new South(),-2,-2));
		assertEquals(myRover().processCommands(rightPlusBackwards + rightPlusBackwards).getPosition(),new Position(new South(),-2,2));
		
	}
	
	private MarsRover myRover() {
		return new MarsRover(new North(), 0, 0);
	}
	
}