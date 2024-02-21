package mancala;

public class PitNotFoundException extends Exception {

    public PitNotFoundException(){
        super("Error: invalid pit number.");
    }

    public PitNotFoundException(String message) {
        super(message);
    }
}