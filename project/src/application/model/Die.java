/* This set the die and what face the die lands on */
package application.model;

public class Die {

	//Data Fields
	private int sides;
	private int top;
	//Creating the die
	public Die(int sides, int top){
		
		this.sides = sides;
		this.top = top;
	}
	//creating the die dimensions
	public Die() {
		sides = 6;
		top = 0;
	}
	
	//returning top
	public int getTop() {
	
		return top;
	}
	//setting the top
	public void setTop(int top) {
		
	if(top >= 0 && top <= sides) {
		this.top = top;
		
	}	
}
	// setting what is at the top of the dice when rolled
	public void roll() {
		top = 1 +(int)(Math.random() * sides);
		
	}
	/*
	//test on console
	public static void main (String[] args) {
		
		Die die = new Die(6, 0);
		
		System.out.println(die.getTop());
		
		for(int i = 0; i < 10; i++) {
			
		die.roll();
		System.out.println(i + ": " + die.getTop());
		
		}
	}
	*/
}

