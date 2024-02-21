package mancala;

public class Store {

    private Player owner;
    private int stones;

    public Store(){
        owner = null;
        stones = 0;
    }

    void setOwner(Player player){
        owner = player;
    }

    public Player getOwner(){
        return owner;
    }

    void addStones(int amount){
        stones += amount;
    }

    public int getTotalStones(){
        return stones;
    }

    int emptyStore(){

        int byeStones = stones;
        stones = 0;
        return byeStones;
    }

    public String toString(){
        return (owner!= null ? owner.getName() : "Unknown player") + "'s stone count is " + stones;
    }

}