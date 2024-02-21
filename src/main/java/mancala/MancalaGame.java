package mancala;

import java.util.ArrayList;

public class MancalaGame{

    private Board board;
    private ArrayList<Player> players;
    private ArrayList<Store> stores;
    private boolean gameOver;
    private Player currentPlayer;
    private Player winner;
 
    public MancalaGame(){
        board = new Board(); 
        players = new ArrayList<Player>();
        stores = board.getStores();
        gameOver = false;
        currentPlayer = null;
        winner = null;
    }

    public void setPlayers(Player onePlayer, Player twoPlayer){
        players.add(onePlayer);
        players.add(twoPlayer);
        board.registerPlayers(onePlayer, twoPlayer);
        setCurrentPlayer(onePlayer);
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    void setCurrentPlayer(Player player){
        currentPlayer = player;
    }

    public void setBoard(Board theBoard){
        board.setUpPits();
        board.setUpStores();
        board.initializeBoard();
    }

    public Board getBoard(){
        return board;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException {
        if (pitNum<1 && pitNum>12){
            throw new PitNotFoundException("Invalid pit number. Please enter a number between 1 and 12.");
        }
        return board.getNumStones(pitNum);

    }

    public int move(int startPit) throws InvalidMoveException {

        int stonesMoved;

        try {
            stonesMoved = board.moveStones(startPit, getCurrentPlayer());
        } catch(InvalidMoveException e){
            throw e;
        }
        try{
            if(board.isSideEmpty(startPit) || board.isSideEmpty(12 - startPit + 1)){

                if(board.isSideEmpty(startPit)){
                    players.get(0).getStore().addStones(board.clearPits(1));
                    players.get(1).getStore().addStones(board.clearPits(12));
                }

                if(players.get(0).getStoreCount() == players.get(1).getStoreCount()){
                    winner = null;
                }else if (players.get(0).getStoreCount() > players.get(1).getStoreCount()){
                    winner = players.get(0);
                }else{
                    winner = players.get(1);
                }

                gameOver = true;

            }else if(board.isExtraTurn()){
                board.setExtraTurn(false);
            }else{
                if (currentPlayer == players.get(0)){
                    setCurrentPlayer(players.get(1));
                }else{
                    setCurrentPlayer(players.get(0));
                }
                
            }
        }catch(PitNotFoundException e){
            System.out.println(e.getMessage());
        }

        return stonesMoved;
    }

    public int getStoreCount(Player player) throws NoSuchPlayerException {  

        for(int i=0; i<2; i++){
            if(players.get(i) == player){
                return stores.get(i).getTotalStones();
            }
        }
        throw new NoSuchPlayerException("Invalid player.");            
    }

    public Player getWinner() throws GameNotOverException {
        if(!isGameOver()){
            throw new GameNotOverException();
        }
        return winner;    
    }

    public boolean isGameOver(){
        return gameOver;
    }
    
    public void startNewGame(){
        board.resetBoard();
        winner = null;
        gameOver = false;
    }

    public String toString(){

        StringBuilder sb = new StringBuilder();

        String name = players.get(1).getName();

        String formatted = String.format("\nPlayer Two: %s", name);

        sb.append(formatted);
        sb.append("\n");

        sb.append(board.toString());

        name = players.get(0).getName();

        formatted = String.format("Player One: %s\n", name);

        sb.append(formatted);
        sb.append("\n\n");

        String result = sb.toString();

        return result;

    }

}
