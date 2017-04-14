package main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import main.rooms.ManToilet;

public class TestEnv extends jason.environment.Environment {

	private Logger logger = Logger.getLogger("testenv.mas2j."
			+ TestEnv.class.getName());
	List<String> asd = new ArrayList<String>();
	View view;

	/** Called before the MAS execution with the args informed in .mas2j */
	@Override
	public void init(String[] args) {
		WorldModel wm = new WorldModel();
		
		int count = 0;

		List<ManToilet> manToilets = wm.getManToiletListE();
		
		for (int i = 0; i < manToilets.size(); ++i) {
			ManToilet mt = manToilets.get(i);
			addPercept("mantoiletsensor"+count, Literal.parseLiteral("available("+mt.getToilet()+","+mt.getUrine()+")"));			
			count++;
		}
		
		manToilets = wm.getManToiletListL();
		
		for (int i = 0; i < manToilets.size(); ++i) {
			ManToilet mt = manToilets.get(i);
			addPercept("mantoiletsensor"+count, Literal.parseLiteral("available("+mt.getToilet()+","+mt.getUrine()+")"));			
			count++;
		}
		
		manToilets = wm.getManToiletListB();
		
		for (int i = 0; i < manToilets.size(); ++i) {
			ManToilet mt = manToilets.get(i);
			addPercept("mantoiletsensor"+count, Literal.parseLiteral("available("+mt.getToilet()+","+mt.getUrine()+")"));			
			count++;
		}
		
		view = new View(wm, "WCCounter", 350);
		view.setEnv(this);
	}

	@Override
	public boolean executeAction(String agName, Structure action) {
		if (action.getFunctor().equals("burn")) {
			addPercept("person", Literal.parseLiteral("change"));
			return true;
		}

		if (action.getFunctor().equals("sensordetect")) {
			addPercept("mantoiletsensor1", Literal.parseLiteral("takenWc"));
			return true;
		}

		return false;
	}

	public void ManToiletTaken() {
		clearPercepts("mantoiletsensor1");
		addPercept("mantoiletsensor1", Literal.parseLiteral("takenWc"));
	}
	
	public void ManToiletFree() {
		clearPercepts("mantoiletsensor1");
		addPercept("mantoiletsensor1", Literal.parseLiteral("freeWc"));
	}
	
	public void ManUrinalTaken() {
		clearPercepts("mantoiletsensor1");
		addPercept("mantoiletsensor1", Literal.parseLiteral("takenUrin"));
	}
	
	public void ManUrinalFree() {
		clearPercepts("mantoiletsensor1");
		addPercept("mantoiletsensor1", Literal.parseLiteral("freeUrin"));
	}


	/** Called before the end of MAS execution */
	@Override
	public void stop() {
		super.stop();
	}


}