package mancala;

public class GameNotOverException extends Exception {

    public GameNotOverException(){
        super("Error: game is not yet over.");
    }

    public GameNotOverException(String message) {
        super(message);
    }
}