
package mancala;

public class Pit {

    private int stoneCount;

    public Pit(){
        stoneCount = 0;
    }

    public int getStoneCount(){
        return stoneCount;
    }

    void addStone(){
        stoneCount++;
    }
    
    int removeStones(){
        int byeStones = stoneCount;
        stoneCount = 0;
        return byeStones;
    }

    public String toString(){
            return "Current number of stones in pit: " + stoneCount;
    }

}