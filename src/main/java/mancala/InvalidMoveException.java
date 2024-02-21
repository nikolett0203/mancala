package mancala;

public class InvalidMoveException extends Exception {

    public InvalidMoveException(){
        super("Error: invalid move.");
    }

    public InvalidMoveException(String message) {
        super(message);
    }
}