package ui;

import mancala.MancalaGame;
import mancala.Player;
import mancala.InvalidMoveException;
import mancala.GameNotOverException;
import java.util.Scanner;

public class TextUI{

    private MancalaGame game;
    private Scanner scanner;
    private boolean newGame = false;

    public TextUI(){
        game = new MancalaGame();
        scanner = new Scanner(System.in);
    }

    private void startGame(){

        do{
            while(!game.isGameOver()){
                displayBoard();

                System.out.println(game.getCurrentPlayer().getName() + "'s turn.");

                if(game.getCurrentPlayer() == game.getPlayers().get(0)){
                    System.out.println("Please enter a pit number between 1 and 6: ");
                }else{
                    System.out.println("Please enter a pit number between 7 and 12: ");
                }

                try{
                    game.move(scanner.nextInt());
                }catch(InvalidMoveException e){
                    System.out.println(e.getMessage());
                }
            }

            try{
                if(game.getWinner() != null){
                    displayBoard();
                    System.out.println(game.getWinner().getName() + " wins!");
                }else if(game.isGameOver()){
                    displayBoard();
                    System.out.println("It's a tie!");
                }
            }catch(GameNotOverException e){
                System.out.println(e.getMessage());
            }

            System.out.println("Would you like to play again? (y/n)");
            String input = scanner.next().toLowerCase();
            while(!input.equals("y") && !input.equals("n")){
                System.out.println("Invalid response. Please try again. (y/n)");
                input = scanner.next().toLowerCase();
            }

            if (input.equals("y")) {
                newGame = true;
                game.startNewGame();
            }else{
                newGame = false;
            }            

        } while (newGame);

    }

    private void setUpGame(){

        Player playerOne;
        Player playerTwo;
        newGame = false;

        game.setBoard(game.getBoard());

        System.out.println("Please enter Player One's Name: ");
        playerOne = new Player(scanner.nextLine());
        System.out.println("Please enter Player Two's name: ");
        playerTwo = new Player(scanner.nextLine());

        game.setPlayers(playerOne, playerTwo);

    }

    private void displayBoard() {
        System.out.println(game.toString());
    }

    public static void main(String[] args) {
        
        TextUI textUI = new TextUI();

        System.out.println("Let's play mancala!");
        textUI.setUpGame();
        textUI.startGame();

        System.out.println("Good game!");
    }

}