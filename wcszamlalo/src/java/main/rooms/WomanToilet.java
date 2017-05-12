package main.rooms;

public class WomanToilet {
	private int toilet, maxToilet;
	private boolean cleaning=false;
	
	public WomanToilet(int t) {
		toilet = maxToilet = t;
	}

	public int getToilet() {
		return toilet;
	}
	
	public void incToilet() {
		if (toilet < maxToilet)
			toilet++;
	}
	
	public void decToilet() {
		if (toilet > 0)
			toilet--;
	}
	
	public void changeCleaning(){
		if(cleaning){
			toilet=maxToilet;
			cleaning=false;
		}
		else {
			toilet=0;
			cleaning=true;
		}
	}
	
	public boolean getCleaning(){
		return cleaning;
	}
}
