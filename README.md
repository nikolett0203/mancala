# Mancala

This is a simple program that allows two users to play Mancala through a text-based user interface.


## Description

Mancala is played on a board with two rows, each containing six pits. Each player has a designated "store" located on the right of their respective side of the board. In this program, the pits are numbered as follows:

    Player 2's Side
    12  11  10  9   8   7
    1   2   3   4   5   6
    Player 1's Side

The game begins when a player enters a pit number from their side of the board, collecting all the stones within it. The stones are then distributed counterclockwise, dropping one stone into each consecutive pit until none are left. The rules are as follows:
* When distributing stones, if a player reaches their own store, one stone is added to it. The opponent's store is skipped and stones are distributed continuing from the next pit.
* If the last stone lands in a player's own store, they receive an extra turn.
* When the last stone ends up in an empty pit on the player's own side, they capture that stone along with any stones in the hole directly opposite on the opponent's side.
The game concludes when all the pits on one side of the Mancala board are empty. The player who still has stones on their side at this point captures all those stones. The winner is the player with the most stones in their store.


## Getting Started

### Dependencies

* package mancala
* package ui
* java.util.ArrayList
* java.util.Scanner
* org.junit.jupiter.api.Assertions.assertEquals;
* org.junit.jupiter.api.Assertions.assertTrue;
* org.junit.jupiter.api.Assertions.assertFalse;
* org.junit.jupiter.api.BeforeEach;
* org.junit.jupiter.api.DisplayName;
* org.junit.jupiter.api.Test;


### Executing program

* How to build and run the program

```
* To build: gradle build
* To run:
 * To run the program from jar:
 * java -jar build/libs/Mancala.jar
 * To run the program from class files:
 * java -cp build/classes/java/main ui.TextUI

```
Expected output:

Let's play mancala!
Please enter Player One's Name: 
Name 1
Please enter Player Two's name: 
Name 2

Player Two: Name 2
Stones in Player 2's store: 0   
 ------ ------ ------ ------ ------ ------ 
|   4  |   4  |   4  |   4  |   4  |   4  |
 ------ ------ ------ ------ ------ ------ 
|   4  |   4  |   4  |   4  |   4  |   4  |
 ------ ------ ------ ------ ------ ------ 
Stones in Player 1's store: 0   
Player One: Name 1



Name 1's turn.
Please enter a pit number between 1 and 6: 
1

Player Two: Name 2
Stones in Player 2's store: 0   
 ------ ------ ------ ------ ------ ------ 
|   4  |   4  |   4  |   4  |   4  |   4  |
 ------ ------ ------ ------ ------ ------ 
|   0  |   5  |   5  |   5  |   5  |   4  |
 ------ ------ ------ ------ ------ ------ 
Stones in Player 1's store: 0   
Player One: Name 1


## Limitations

Mancala is a complete project.


## Development History

* 31.10.2023: Final project pushed to github, bugs resolved
* 29.10.2023: MancalaGame and TextUI classes completed with bugs
* 28.10.2023: Board class completed
* 22.10.2023: Method stubs and gradle file complete
* 21.10.2023: Initial project pushed to github with Store, Pit, and Player classes complete


## Acknowledgments

Inspiration, code snippets, etc.

* [Mancala-rules] (https://www.officialgamerules.org/mancala)
* [Simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)
