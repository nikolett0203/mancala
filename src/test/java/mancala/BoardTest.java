package mancala;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setup(){
        board = new Board();
        board.setUpPits();
        board.setUpStores();
        board.initializeBoard();
    }

    @Test
    @DisplayName("Test getNumStones()")
    public void testGetNumStones(){
        ArrayList<Pit> pits = board.getPits();
        try{ // pit number too small
            board.getNumStones(0);
        }catch(PitNotFoundException e){
            assertEquals("Invalid pit number. Please enter a number between 1 and 12.", e.getMessage());
        }
        try{ // pit number too big
            board.getNumStones(12);
        }catch(PitNotFoundException e){
            assertEquals("Invalid pit number. Please enter a number between 1 and 12.", e.getMessage());
        }
        try{ // valid pit number
            pits.get(0).addStone();
            assertEquals(5, board.getNumStones(1));
        }catch(PitNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{ // empty pit
            pits.get(11).removeStones();
            assertEquals(0, board.getNumStones(12));
        }catch(PitNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Test resetBoard()")
    public void resetBoard(){
        ArrayList<Pit> pits = board.getPits();
        ArrayList<Store> stores = board.getStores();
        for(int i = 0; i<12; i++){
            pits.get(i).removeStones();
        }
        stores.get(0).addStones(15);
        stores.get(1).addStones(31);

        board.resetBoard();  // test for empty pits, filled stores
    
        for(int i = 1; i<13; i++){
            try{
                assertEquals(4, board.getNumStones(i));
            }catch(PitNotFoundException e){
                System.out.println(e.getMessage());
            }
        }

        assertEquals(0, stores.get(0).getTotalStones());
        assertEquals(0, stores.get(1).getTotalStones());

        for(int i = 0; i<12; i++){
            pits.get(i).addStone();
        }

        board.resetBoard();  // test for filled pits, empty stores

        for(int i = 1; i<13; i++){
            try{
                assertEquals(4, board.getNumStones(i));
            }catch(PitNotFoundException e){
                System.out.println(e.getMessage());
            }
        }

        assertEquals(0, stores.get(0).getTotalStones());
        assertEquals(0, stores.get(1).getTotalStones());

    }

    @Test
    @DisplayName("Test isSideEmpty()")
    public void testIsSideEmpty(){
        ArrayList<Pit> pits = board.getPits();
        try{ // pit number too small
            board.isSideEmpty(0);
        }catch(PitNotFoundException e){
            assertEquals("Invalid pit number. Please enter a number between 1 and 12.", e.getMessage());
        }
        try{ // pit number too big
            board.isSideEmpty(13);
        }catch(PitNotFoundException e){
            assertEquals("Invalid pit number. Please enter a number between 1 and 12.", e.getMessage());
        }
        try{ // side not empty
            assertFalse(board.isSideEmpty(1));
            assertFalse(board.isSideEmpty(12));
        }catch(PitNotFoundException e){
            assertEquals("Invalid pit number. Please enter a number between 1 and 12.", e.getMessage());
        }
        try{ // side empty
            board.clearPits(1);
            board.clearPits(12);
            assertTrue(board.isSideEmpty(6));
            assertTrue(board.isSideEmpty(7));
        }catch(PitNotFoundException e){
            assertEquals("Invalid pit number. Please enter a number between 1 and 12.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test registerPlayers()")
    void testRegisterPlayers(){

        Player one = new Player("Niki");
        Player two = new Player("Jerry");
        ArrayList<Store> stores = board.getStores();

        board.registerPlayers(one, two);

        assertEquals(one, stores.get(0).getOwner());
        assertEquals(two, stores.get(1).getOwner());
        assertEquals(one.getStore(), stores.get(0));
        assertEquals(two.getStore(), stores.get(1));      

    }

    @Test
    @DisplayName("Test captureStones()")
    void testCaptureStones(){
        ArrayList<Pit> pits = board.getPits();
        ArrayList<Store> stores = board.getStores();
        int stonesCaptured = 0;
        try{ // pit number too small
            board.captureStones(0);
        }catch(PitNotFoundException e){
            assertEquals("Invalid pit number. Please enter a number between 1 and 12.", e.getMessage());
        }
        try{ // pit number too big
            board.captureStones(13);
        }catch(PitNotFoundException e){
            assertEquals("Invalid pit number. Please enter a number between 1 and 12.", e.getMessage());
        }
        try{  // valid capture for player 1
            pits.get(0).removeStones();
            pits.get(0).addStone();
            stonesCaptured = board.captureStones(1);
            assertEquals(4, stonesCaptured);
            assertEquals(5, stores.get(0).getTotalStones());
        }catch(PitNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{  // invalid capture for player 1
            stonesCaptured = board.captureStones(2);
            assertEquals(0, stonesCaptured);
            assertEquals(6, stores.get(0).getTotalStones());
        }catch(PitNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{  // valid capture for player 2
            pits.get(6).removeStones();
            pits.get(6).addStone();
            stonesCaptured = board.captureStones(7);
            assertEquals(4, stonesCaptured);
            assertEquals(5, stores.get(1).getTotalStones());
        }catch(PitNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Test distributeStones()")
    void testDistributeStones(){
        ArrayList<Pit> pits = board.getPits();
        ArrayList<Store> stores = board.getStores();
        int stonesMoved = 0;
        try{ // pit number too small
            board.distributeStones(0);
        }catch(PitNotFoundException e){
            assertEquals("Invalid pit number. Please enter a number between 1 and 12.", e.getMessage());
        }
        try{ // pit number too big
            board.distributeStones(13);
        }catch(PitNotFoundException e){
            assertEquals("Invalid pit number. Please enter a number between 1 and 12.", e.getMessage());
        }
        try{ // distribution into player 1's store
            stonesMoved = board.distributeStones(6);
            assertEquals(1, stores.get(0).getTotalStones());
            for(int i = 6; i <9; i++){
                 assertEquals(5, pits.get(i).getStoneCount());
            }
            assertEquals(4, pits.get(9).getStoneCount());
            assertEquals(4, stonesMoved);
        }catch(PitNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{ // distribution into player 2's store
            stonesMoved = board.distributeStones(12);
            assertEquals(1, stores.get(1).getTotalStones());
            for(int i = 0; i <3; i++){
                 assertEquals(5, pits.get(i).getStoneCount());
            }
            assertEquals(4, pits.get(3).getStoneCount());
            assertEquals(4, stonesMoved);
        }catch(PitNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{ // distribution with capture
            stonesMoved = board.distributeStones(1);
            assertEquals(10, stonesMoved);
            assertEquals(7, stores.get(0).getTotalStones());
        }catch(PitNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
