package application.model;

import java.util.ArrayList;

public class Rounds {

	ArrayList<Person> playerList;
	Person player1, player2;
	
	public Rounds(ArrayList<Person> playerList) {
		
		this.playerList = new ArrayList<Person>();
    	
    	for(Person person: playerList)
    		this.playerList.add(person);

		player1 = playerList.get((int)(Math.random() * playerList.size()));
		
		do
			player2 = playerList.get((int)(Math.random() * playerList.size()));
		while(player2 == player1);
	}
	
	public boolean checkWinner() {
		
		return (this.playerList.size() == 1)? true:false;
	}
	
	public Person getWinner() {
		
		return playerList.get(0);
	}
	
	public String getPlayer1() {
		
		return player1.getName();
	}
	
	public String getPlayer2() {
		
		return player2.getName();
	}
	
	public boolean nextRound(String loserName) {
		
		Person loser = (loserName.equals(this.player1.getName()))? this.player1:this.player2;
		
		playerList.remove(playerList.indexOf(loser));
		if(checkWinner()) return true;
		
		
		this.player1 = playerList.get((int)(Math.random() * playerList.size()));
		
		do
			this.player2 = playerList.get((int)(Math.random() * playerList.size()));
		while(this.player2 == this.player1);
		
		return false;
	}
}
