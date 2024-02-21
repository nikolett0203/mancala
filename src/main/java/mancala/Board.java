package mancala;

import java.util.ArrayList;

public class Board{

    private ArrayList<Pit> pits;
    private ArrayList<Store> stores;
    private boolean extraTurn;

    public Board(){
        pits = new ArrayList<Pit>();
        stores = new ArrayList<Store>();
        extraTurn = false;
    }

    void setUpPits(){
        for (int i = 0; i < 12; i++){
            pits.add(new Pit());
        }
    }

    public ArrayList<Pit> getPits(){
        return pits;
    }

    public ArrayList<Store> getStores(){
        return stores;
    }

    void setUpStores(){
        for (int i=0; i<2; i++){
            stores.add(new Store());
        }
    }

    void initializeBoard(){
        for (Pit aPit : pits){
            for (int i=0; i<4; i++){
                aPit.addStone();
            }
        }
    }

    void resetBoard(){
        int byeStones;
        for (Pit aPit : pits){
            byeStones = aPit.removeStones();
            for (int i=0; i<4; i++){
                    aPit.addStone();
            }
        }   
        for (Store aStore : stores){
            byeStones = aStore.emptyStore();
        }
    }

    void registerPlayers(Player one, Player two){
        stores.get(0).setOwner(one);
        stores.get(1).setOwner(two); 
        one.setStore(stores.get(0));
        two.setStore(stores.get(1));
    }

    int moveStones(int startPit, Player player) throws InvalidMoveException{

        int stonesMoved = 0;

        if(startPit >=1 && startPit <=6){
            if(pits.get(startPit-1).getStoneCount()==0){
                throw new InvalidMoveException("Invalid move: pit empty. Please try again.");
            }else if(stores.get(0).getOwner() == player){
                try{
                    stonesMoved = distributeStones(startPit);
                }catch(PitNotFoundException e){
                    System.out.println(e.getMessage());
                }
            }else{
                throw new InvalidMoveException("Invalid move: opposite player's pit. Please try again.");
            }
        }else if(startPit >=7 && startPit <=12){
            if(pits.get(startPit-1).getStoneCount()==0){
                throw new InvalidMoveException("Invalid move: pit empty. Please try again.");
            }else if(stores.get(1).getOwner() == player){
                try{
                    stonesMoved = distributeStones(startPit);
                }catch(PitNotFoundException e){
                    System.out.println(e.getMessage());
                }
            }else{
                throw new InvalidMoveException("Invalid move: opposite player's pit. Please try again.");
            }
        }else{
            throw new InvalidMoveException("Invalid move: pit number out-of-bounds. Please try again.");
        }
    
        return stonesMoved;

    }

    int distributeStones(int startingPoint) throws PitNotFoundException {
        if (!isValidPit(startingPoint)){
            throw new PitNotFoundException("Invalid pit number. Please enter a number between 1 and 12.");
        }
        int currentPit = (startingPoint-1);
        int stonesToDistribute = pits.get(currentPit).removeStones();
        int totalStones = stonesToDistribute;
        currentPit = (currentPit+1)%12;
        int stonesCaptured = 0;
        if(startingPoint==12 && stonesToDistribute !=0){
            stores.get(1).addStones(1);
            stonesToDistribute--;
            if (stonesToDistribute == 0){
                setExtraTurn(true);
            }
        }else if(startingPoint==6 && stonesToDistribute !=0){
            stores.get(0).addStones(1);
            stonesToDistribute--;
            if (stonesToDistribute == 0){
                setExtraTurn(true);  
            } 
        }
        while (stonesToDistribute != 0){
            pits.get(currentPit).addStone();
            stonesToDistribute--;
            if(isCaptureValid(startingPoint, currentPit, stonesToDistribute)){
                try{
                    stonesCaptured = captureStones(currentPit+1);
                }catch(PitNotFoundException e){
                    System.out.println(e.getMessage());
                } 
                return totalStones + stonesCaptured;
            }
            if (startingPoint >= 1 && startingPoint <= 6){
                if(currentPit%12 == 5 && stonesToDistribute != 0){
                    stores.get(0).addStones(1);
                    stonesToDistribute--;
                    if (stonesToDistribute == 0){
                        setExtraTurn(true);
                    }
                }
            }else if (startingPoint >= 7 && startingPoint <= 12){
                if(currentPit%12 == 11 && stonesToDistribute != 0){
                    stores.get(1).addStones(1);
                    stonesToDistribute--;
                    if (stonesToDistribute == 0){
                        setExtraTurn(true);
                    }
                }
            } 
            currentPit = (currentPit+1)%12;
        }
        return totalStones;
    }

    private boolean isCaptureValid(int startingPoint, int currentPit, int stonesToDistribute){
        try{
            if(stonesToDistribute==0 && getNumStones(currentPit+1)==1){
                if(startingPoint>=1 && startingPoint <=6){
                    if(currentPit>=0 && currentPit<=5){
                        return true;
                    }

                }else if(startingPoint>=7 && startingPoint <=11){
                    if(currentPit>=6 && currentPit<=11){
                        return true;
                    }

                }
            }
        }catch(PitNotFoundException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    int captureStones(int stoppingPoint) throws PitNotFoundException{
        if (!isValidPit(stoppingPoint)){
            throw new PitNotFoundException("Invalid pit number. Please enter a number between 1 and 12.");
        }

        int stonesCaptured = 0;
        int oppositePit = 11;

        for (int i = 0; i < 12; i++){
            if (i == stoppingPoint-1){
                if (pits.get(i).getStoneCount()==1){    // pit has to be empty to capture the stones
                    pits.get(i).removeStones();
                    stonesCaptured = pits.get(oppositePit).removeStones();  
                }                       
            }
            oppositePit--;
        }

        if ((stoppingPoint)>=0 && (stoppingPoint)<=6){
            stores.get(0).addStones(stonesCaptured + 1);
        }else{
            stores.get(1).addStones(stonesCaptured + 1);
        }

        return stonesCaptured;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException {
        if (!isValidPit(pitNum)){
            throw new PitNotFoundException("Invalid pit number. Please enter a number between 1 and 12.");
        }
        return pits.get(pitNum-1).getStoneCount();
    }

    public boolean isSideEmpty(int pitNum) throws PitNotFoundException {
        if (!isValidPit(pitNum)){
            throw new PitNotFoundException("Invalid pit number. Please enter a number between 1 and 12.");
        } else if (pitNum >=1 && pitNum <=6){
            for (int i = 1; i <= 6; i++){
                if (pits.get(i-1).getStoneCount() != 0){
                    return false;
                }
            }
        } else{
            for (int i = 7; i <= 12; i++){
                if (pits.get(i-1).getStoneCount() != 0){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidPit(int pitNum) {
        return pitNum >= 1 && pitNum <= 12;
    }

    boolean isExtraTurn(){
        return extraTurn;
    }

    void setExtraTurn(boolean anotherTurn){
        extraTurn = anotherTurn;
    }

    int clearPits(int pitNum){

        int stonesCleared = 0;

        if (pitNum >=1 && pitNum <=6){
            for (int i = 1; i <= 6; i++){
                stonesCleared += pits.get(i-1).removeStones();
            }
        }else if (pitNum >=7 && pitNum <=12){
            for (int i = 7; i <= 12; i++){
                stonesCleared += pits.get(i-1).removeStones();
            }
        }
        return stonesCleared;
    }

    public String toString(){
        
        StringBuilder sb = new StringBuilder();

        int pitNum = 11;
        String formatted = String.format("%s: %d   ", "Stones in Player 2's store", stores.get(1).getTotalStones());

        sb.append(formatted);
        sb.append("\n");
        sb.append(" ------ ------ ------ ------ ------ ------ \n");
        for(int i = 11; i > 5; i--){
            formatted = String.format("|%s%2d%s", "  ", pits.get(pitNum).getStoneCount(), "  ");
            sb.append(formatted);
            pitNum--;
        }
        sb.append("|\n");
        sb.append(" ------ ------ ------ ------ ------ ------ \n");
        pitNum = 0;
        for(int i = 0; i < 6; i++){
            formatted = String.format("|%s%2d%s", "  ", pits.get(pitNum).getStoneCount(), "  ");
            sb.append(formatted);
            pitNum++;
        }
        sb.append("|\n");
        sb.append(" ------ ------ ------ ------ ------ ------ \n");

        formatted = String.format("%s: %d   ", "Stones in Player 1's store", stores.get(0).getTotalStones());
        sb.append(formatted);
        sb.append("\n");
        String result = sb.toString();

        return result;
    }

}