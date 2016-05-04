package minesweeper;

public class Diagram {
	/*Diagram - What the Player sees, originally displays identifying numbers
	 *			which change to contact indicators when 'stepped on'
	 */
	private int [][] seen;
	
	public Diagram(int[][] seen ){
		this.seen = seen;
	}

	public int [][] getSeen() {
		return seen;
	}
	


	public void setSeen(int row, int coloum, int value) {
		this.seen[row][coloum]= value;
	}
	
	
}
