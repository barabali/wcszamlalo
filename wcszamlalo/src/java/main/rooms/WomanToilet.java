package main.rooms;

public class WomanToilet {
	private int toilet, maxToilet;
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
}
