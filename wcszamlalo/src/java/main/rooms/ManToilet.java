package main.rooms;

public class ManToilet {
	private int toilet, maxToilet;
	private int urine, maxUrine;
	private boolean cleaning=false;
	
	public ManToilet(int t, int u) {
		toilet = maxToilet = t;
		urine = maxUrine = u;
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

	public int getUrine() {
		return urine;
	}
	
	public void incUrine() {
		if (urine < maxUrine)
			urine++;
	}
	
	public void decUrine() {
		if (urine > 0)
			urine--;
	}
	
	public void changeCleaning(){
		if(cleaning){
			urine=maxUrine;
			toilet=maxToilet;
			cleaning=false;
		}
		else {
			urine=0;
			toilet=0;
			cleaning=true;
		}
	}
	
	public boolean getCleaning(){
		return cleaning;
	}
}
