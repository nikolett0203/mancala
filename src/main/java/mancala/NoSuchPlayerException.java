package mancala;

public class NoSuchPlayerException extends Exception {

    public NoSuchPlayerException(){
        super("Error: player not found.");
    }

    public NoSuchPlayerException(String message) {
        super(message);
    }
}