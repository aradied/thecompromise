/*This class is the brain of the game. It sets the player and the current player. This also rolls the die*/
package application.model;

public class Game{
	//setting need resources and maxscore = 100
	private Die die;
	private Player player1;
	private Player player2;
	private Player current;
	public static final int maxscore = 100;
	//Seting player name
	public Game(String player1Name, String player2Name) {
		
		die = new Die(6,1);
		player1 = new Player(player1Name);
		player2 = new Player(player2Name);
		
		current = player1;
	}
	
	public Die getDie() {
		return die;
	}
	public Player getCurrent() {
		return current;
	}
	public Player getPlayer1() {
		return player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	//Setting the max score until game is over.
	public boolean GameOver() {
		
		return current.getTotalScore() >= maxscore;
	}
	// return true if player one is currently playing 
	public boolean player1Turn() {
		return current == player1;
	}
	//switching between player 1 and 2
	public void switchPlayer() {
		if(player1Turn()) {
			current = player2;
		}
		else {
			current = player1;
		}
		
	}
	//used to get the dieroll
	public void roll() {
		die.roll();
		int t = die.getTop();
		current.updateTurn(t);
	}
	//Creating function to save what was rolled on the die
	public void hold() {
		current.saveScore();
		switchPlayer();
		die.setTop(0);
		
	}
	/*//Test on console
	public static void main(String[] args) {
		Game game = new Game("", "");
		for( int i = 0 ; i < 10; i++) {
	
		game.roll();
		game.hold();
		System.out.println("Die Rolled " + game.getDie().getTop());
		System.out.println("Player 1 Turn: " + game.getPlayer1().getTurnScore());
		System.out.println("Player 1 Total: " + game.getPlayer1().getTotalScore());
		System.out.println("Player 2 Turn: " + game.getPlayer2().getTurnScore());
		System.out.println("Player 2 Total: " + game.getPlayer2().getTotalScore());
		}
		
	}*/
	
}
