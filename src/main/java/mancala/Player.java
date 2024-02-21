package mancala;

public class Player{

    private String player;
    private Store stoneStore;

    public Player(){
        player = null;
        stoneStore = new Store();
    }

    public Player(String name){
        player = name;
        stoneStore = new Store();
    }

    public String getName(){
        return player;
    }

    public Store getStore(){
        return stoneStore;
    }

    public int getStoreCount(){
        return stoneStore.getTotalStones();
    }

    void setName(String name){
        player = name;
    }

    void setStore(Store store){
        stoneStore = store;
    }

    public String toString(){
        return player + "'s store has a stone count of " + stoneStore.getTotalStones();
    }

}