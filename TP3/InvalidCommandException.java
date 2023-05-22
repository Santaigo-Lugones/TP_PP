package MarsRover;

public class InvalidCommandException extends RuntimeException {

    private static final String MESSAGE = "Invalid command!";

    public InvalidCommandException() {
        super(MESSAGE);
    }

}