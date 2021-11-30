package application.model;

import java.util.Random;

public class TicTacToe {
	//initialize variables for board array that will store the moves made, 2 booleans for checking for the winner, scores, the turn, and 2 player objects 
	char[][] board = new char[3][3];
	boolean p1;
	boolean p2;
	boolean turn;
	int p1score;
	int p2score;
	int ties;
	Person player1;
	Person player2;
	Random rand = new Random();
	//initialize the players, winner status, and the board
	public void initializeGame(Person player1, Person player2) {
		this.player1 = player1;
		this.player2 = player2;
		turn = p1;
		p1score = 0;
		p2score = 0;
		ties = 0;
		p1 = false;
		p2 = false;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++)
				board[i][j] = ' ';
		}
	}
	//randomly chooses who goes first in the game 
	public boolean firstTurn() {
		turn = rand.nextBoolean();
		return turn;
		
	}
	//gets the turn of player1, if false then it is player2's turn
	public boolean getTurn() {
		return turn;
	}
	//alternates the turn 
	public void nextTurn() {
		turn = !turn;
	}
	//returns the player that won the game
	public Person returnWinner() {
		if(p1) 
			return player1;
		else
			return player2;
	}
	//show the score for player1 or player2
	public int showP1Score() {
		return p1score;
	}
	public int showP2Score() {
		return p2score;
	}
	public int showTies() {
		return ties;
	}
	//takes in the row, column, and player letter, then adds a move to the board array
	public void addMove(int r, int c, char letter) {
		if(letter == 'X')
		board[r][c] = 'X';
		else
			board[r][c] = 'O';
	}
	//takes in a row and column as an arguement, and checks that the space is valid and returns true if it is or false if its taken
	public boolean checkValidMove(int r, int c) {
		if(board[r][c] == ' ')
			return true;
		else
			return false;
	}
	//checks if there is a winner by comparing the board coordinates against the 8 possible winning moves, returns the player bool that won
	public boolean checkWinner() {
		//check for horizontal wins
		if(board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X') {
			p1 = true;
			p1score++;
			return p1;
		}
		if(board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X') {
			p1 = true;
			p1score++;
			return p1;
		}
		if(board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X') {
			p1 = true;
			p1score++;
			return p1;
		}
		if(board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O') {
			p2 = true;
			p2score++;
			return p2;
		}
		if(board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O') {
			p2 = true;
			p2score++;
			return p2;
			}
		if(board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O') {
			p2 = true;
			p2score++;
			return p2;
			}
		//check for vertical wins
		if(board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X') {
			p1 = true;
			p1score++;
			return p1;
		}
		if(board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X') {
			p1 = true;
			p1score++;
			return p1;
		}
		if(board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X') {
			p1 = true;
			p1score++;
			return p1;
		}
		if(board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O') {
			p2 = true;
			p2score++;
			return p2;
			}
		if(board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O') {
			p2 = true;
			p2score++;
			return p2;
			}
		if(board[0][2] == 'O' && board[1][2] == 'O' && board[2][2] == 'O') {
			p2 = true;
			p2score++;
			return p2;
			}
		//check for diagonal wins
		if(board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') {
			p1 = true;
			p1score++;
			return p1;
		}
		if(board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') {
			p1 = true;
			p1score++;
			return p1;
		}
		if(board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') {
			p2 = true;
			p2score++;
			return p2;
			}
		if(board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') {
			p2 = true;
			p2score++;
			return p2;
			}
		else
			return false;
		
	}
	//checks each board index for a space that can be filled (tie only happens when all spaces are filled), if a space is found there is no tie and returns false, if not return true
	public boolean checkTie() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++)
				if(board[i][j] == ' ')
					return false;
		}
		ties++;
		return true;
		
	}
}
