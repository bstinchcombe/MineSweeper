package minesweeper;

public class Filler {
	//Filler - Stored in the MineField is either a mine or a contact indicator
	private boolean mine;
    private int contacts;//number of mines filler is in contact with
    
    public Filler(boolean mine){
		
		this.mine=mine;
		
	}
	
	public int getContacts() {
		return contacts;
	}

	public void setContacts(int contacts) {
		this.contacts = contacts;
	}
	
	public void setMine(boolean mine) {
		this.mine = mine;
	}
	
	public boolean isMine() {
		return mine;
	}
	
	


	
	



	
}
