package MarsRover;

public class MarsRover {
	
	private Position pos;

    public MarsRover(Direction dir, int x, int y) {
        this.pos = new Position(dir, x, y);
    }

    public MarsRover move(char action) {
        switch(action) {
            case 'f': pos.moveForward(); return this;
            case 'b': pos.moveBack(); return this;
            case 'l': pos.rotateLeft(); return this;
            case 'r': pos.rotateRight(); return this;
            default: throw new InvalidCommandException();
        }
    }

    public MarsRover processCommands(String commandsString) {
        char[] commands = commandsString.toCharArray();
        for(char command : commands) {
            move(command);
        }
        return this;
    }
    
    public Position getPosition() {
    	return pos;
    }

}