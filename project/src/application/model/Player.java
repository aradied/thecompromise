/*This class creates the player and stores the current player score and total score of the die. 
 * This also updates the player score*/
package application.model;

public class Player {
	
	private String name;
	private int turnscore;
	private int totalscore;
	//Gets player name
	public Player(String name) {
		
		this.name = name;
		turnscore = 0;
		totalscore = 0;
	}
	//returns

	public String getName() {
		return name;
	}
	//returns

	public int getTotalScore() {
		return totalscore;
	}
	//returns
	public int getTurnScore() {
		
		return turnscore;
	}
	//setting score to 0
	public void resetTurnScore() {
		turnscore = 0;
	}
	//Updating what value the roll was to turnscore
	public void updateTurn(int roll) {
		turnscore += roll;
	}
	//Save the score by adding the die up
	public void saveScore() {
		totalscore += turnscore;
		resetTurnScore();
		
	}
	/*
	//console test
	public static void main(String[] args) {
		
		Die die = new Die(6, 1);
		Player player = new Player("Adrian");
		System.out.println("Turn score " + player.getTurnScore());
		System.out.println("Turn score " + player.getTotalScore());
		System.out.println("Rolling");
		for(int i = 0; i < 10; i++) {
			die.roll();
			player.updateTurn(die.getTop());
			System.out.println("Turn score " + player.getTurnScore());
			System.out.println("Turn score " + player.getTotalScore());
			
		}
		player.saveScore();
		System.out.println("Saving");
		System.out.println("Turn score " + player.getTurnScore());
		System.out.println("Turn score " + player.getTotalScore());
	}
*/
}
