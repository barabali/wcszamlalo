package main.rooms;

public class DisabledToilet {
	private int toilet, maxToilet;
	public DisabledToilet(int t) {
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
