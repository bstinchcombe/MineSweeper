package minesweeper;


public class MineField {
	// MineField - Underlying array, holds fillers which may be mines and numbers (contact indicators) 
	private Filler[][] field;
	
	public MineField(Filler[][] field) {
		this.field = field;
		
	}
	
	
	
	public Filler[][] getField() {
		return field;
	}
	
	public Filler getFiller(int y, int x){
		return field[y][x];
	}


	public void setField(Filler[][] field) {
		this.field = field;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
