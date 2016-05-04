package minesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/* Game 		- Where the game is built and run
 * MineField 	- Underlying array, holds mines and numbers (contact indicators) 
 * Diagram		- What the Player sees, originally displays identifying numbers
 * 				  which change to contact indicators when 'stepped on'
 * Filler		- Stored in the MineField is either a mine or a contact indicator
 * 
 * 
 * 
*/
public class Game {

	public static void main(String[] args) {
		//calls a new game
		Game game1 = new Game();
		
	}	
	public Game(){ 
		//Construction and game play split into methods
		MineField field1 = buildMineField();
		Diagram diagram1 = buildDiagram(field1);
		populateMineField(field1);
		
		System.out.println("Minefield is shown below, cells are designated by a two digit number." +
							"\nTo select or 'step on' a cell enter the two digit number when prompted,"+
							"\nthe two digit number will be replaced with the number of mines it touches.");
		
		diagramPrinter(diagram1);
		turn(diagram1,field1);
	}	
	
	
	public MineField buildMineField(){
		//construction of a new MineField containing a 2D array using MineField constructor
		Filler[][] hidden1 = new Filler [5][5];
		MineField field1 = new MineField(hidden1);
		return field1;
	}
	
	
	public Diagram buildDiagram(MineField field1){
		//creation of a new Diagram containing a 2D array to mirror the MineField array
		int label = 11;
		int[][] seen1 = new int[field1.getField().length][field1.getField().length];
		for(int vertical = 0; vertical<seen1.length; vertical ++){
			for(int horizontal = 0; horizontal <seen1.length; horizontal++){
				seen1[vertical][horizontal] = label;
				label++;
			}
		}
		Diagram diagram1 = new Diagram(seen1);
		return diagram1;
	}
	
	
	public void populateMineField(MineField field1){
		/* Places Fillers in every cell of the MineField
		 * Randomly assigns a set number of these as mines
		 * Assigns the rest as numbers to indicate the number of mines that cell is contact with
		 */
		
		//Places Fillers in every cell of the MineField
		int cycler = 1;
		for(int i =0; i < field1.getField().length; i++){
			for(int j =0; j<field1.getField().length; j++){
				int id=cycler;
				boolean mine = false;
				field1.getField()[i][j]=new Filler(mine);
				cycler++;
			}
		}
		//Populates MineField with 5 mines in random locations and places a counter in each cell around the mine
		//number of mines - if change change turn counter
		int counterMines = 5;	
		//Do while loop ensures 5 mines are placed, uses if statement to make sure only one mine in one location
		do{
			int ry = (int)(Math.random()*5);
			int rx = (int)(Math.random()*5);
			if(!field1.getField()[ry][rx].isMine()){
				field1.getField()[ry][rx].setMine(true);
				counterMines--;
				//for loops add one to the proximity counter of all cells connected to the mine being placed
				for(int y = ry-1; y < ry+2; y++){
					for(int x= rx-1; x < rx+2; x++){
						if(-1<y && y<5){
							if(-1<x && x<5){
								field1.getField()[y][x].setContacts(field1.getField()[y][x].getContacts()+1);
							}
						}
					}
				}
			}
		}while(counterMines != 0);
	}
		
		
	
	
	
	public void diagramPrinter(Diagram diagram1){
		//Prints the Diagram of the game cell by cell to make a visual grid
		for(int vertical1 = 0; vertical1<diagram1.getSeen().length; vertical1 ++){
		  	for(int horizontal1 = 0; horizontal1 <diagram1.getSeen().length; horizontal1++){
		  		System.out.print(diagram1.getSeen()[vertical1][horizontal1]);
				System.out.print("    ");
			}
		  	System.out.println("\n");
		}				
	}
	
	
	public void mineFieldPrinter(MineField field1){
		//Prints the MineField of the game cell by cell to make a visual grid
		for(int vertical1 = 0; vertical1<field1.getField().length; vertical1 ++){
		  	for(int horizontal1 = 0; horizontal1 <field1.getField().length; horizontal1++){
		  		if(field1.getField()[vertical1][horizontal1].isMine()){
		  			System.out.print("x");
		  			System.out.print("     ");
		  		}
		  		if(!field1.getField()[vertical1][horizontal1].isMine()){
		  			System.out.print(field1.getField()[vertical1][horizontal1].getContacts());
		  			System.out.print("     ");
		  		}
			}
		  	System.out.println("\n ");
		}				
	}
	
	
	public Diagram turn(Diagram diagram1, MineField field1){
		/* - Plays a game turn including selecting a Diagram cell, updating its contents from
		 *   the MineField and printing the result
		 * - When the game is a mine is hit game over message printed and mineFieldPrinter called to reveal mines
		 */
		
		int selected;
		int turn = 1;
		//whole turn mechanism in outer do while loop gives 20 turns to find five mines in a 5x5 grid
		do{
			try{
				System.out.println("\nPlease enter a cell to 'step on'");
				//inner do while loop ensures selected cell is within range
				do{Scanner selectionscanner= new Scanner(System.in);
					selected = selectionscanner.nextInt();
				
					if(selected<11 || selected>35){
						System.out.println("Cell selected is ount of range. "
								+ "\nPlease eneter another cell");
					}
				
				}while(selected<11 || selected>35 );
			
			
				System.out.println("Cell selected is " + selected);
				//nested for loops scan board to find selected cell by use of outer if statement
				for(int vertical2 = 0; vertical2<diagram1.getSeen().length; vertical2 ++){
					for(int horizontal2 = 0; horizontal2 <diagram1.getSeen().length; horizontal2++){
						if(selected == diagram1.getSeen()[vertical2][horizontal2]){
							//inner if statement checks selected isn't a mine and reveals contactCounter to player
							if(!field1.getFiller(vertical2,horizontal2).isMine()){
								diagram1.setSeen(vertical2, horizontal2, field1.getFiller(vertical2, horizontal2).getContacts());
								diagramPrinter(diagram1);
							}
							//if selected cell is a mine else statement ends the game
							else{
								System.out.println("Game over");
								mineFieldPrinter(field1);
								System.exit(0);
							}
						}
					}		  	
				}	
				turn++;
			}catch(InputMismatchException e){
				System.out.println("Please enter a two digit number only");
			}
		}while(turn<21);
		System.out.println("Winner");
		mineFieldPrinter(field1);
		return diagram1;
	}

	
		
	
}
