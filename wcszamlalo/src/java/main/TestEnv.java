package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import main.rooms.ManToilet;

public class TestEnv extends jason.environment.Environment {

	private Logger logger = Logger.getLogger("testenv.mas2j."
			+ TestEnv.class.getName());
	
	Map<String,Integer> toiletmap=new HashMap<String,Integer>();
	View view;
	WorldModel wm;

	/** Called before the MAS execution with the args informed in .mas2j */
	@Override
	public void init(String[] args) {
		wm = new WorldModel();
		
		int count = 1;

		List<ManToilet> manToilets = wm.getManToiletListE();
		
		for (int i = 0; i < manToilets.size(); ++i) {
			ManToilet mt = manToilets.get(i);
			toiletmap.put("IE"+String.valueOf(i-2)+"10",count);
			addPercept("mantoiletsensor"+count, Literal.parseLiteral("available("+mt.getToilet()+","+mt.getUrine()+")"));			
			count++;
		}
		
		manToilets = wm.getManToiletListL();
		
		for (int i = 0; i < manToilets.size(); ++i) {
			ManToilet mt = manToilets.get(i);
			toiletmap.put("IE"+String.valueOf(i-2)+"08",count);
			addPercept("mantoiletsensor"+count, Literal.parseLiteral("available("+mt.getToilet()+","+mt.getUrine()+")"));			
			count++;
		}
		
		manToilets = wm.getManToiletListB();
		
		for (int i = 0; i < manToilets.size(); ++i) {
			ManToilet mt = manToilets.get(i);
			toiletmap.put("IB"+String.valueOf(i-2)+"04",count);
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

	public void ManToiletTaken(String param) {
		int count=toiletmap.get(param);
		clearPercepts("mantoiletsensor"+count);
		addPercept("mantoiletsensor"+count, Literal.parseLiteral("takenWc"));
		
		ManToilet mt;
		if (count <= 7) {
			mt = wm.getManToiletListE().get(count-1);
		} else if (count <= 14) {
			mt = wm.getManToiletListL().get(count-8);
		} else {
			mt = wm.getManToiletListB().get(count-15);
		}
		
		mt.decToilet();
		
		view.setTextOfManToilet(String.valueOf(mt.getToilet()));
	}
	
	public void ManToiletFree(String param) {
		int count=toiletmap.get(param);
		clearPercepts("mantoiletsensor"+count);
		addPercept("mantoiletsensor"+count, Literal.parseLiteral("freeWc"));
		
		ManToilet mt;
		if (count <= 7) {
			mt = wm.getManToiletListE().get(count-1);
		} else if (count <= 14) {
			mt = wm.getManToiletListL().get(count-8);
		} else {
			mt = wm.getManToiletListB().get(count-15);
		}
		
		mt.incToilet();
		
		view.setTextOfManToilet(String.valueOf(mt.getToilet()));
	}
	
	public void ManUrinalTaken(String param) {
		int count=toiletmap.get(param);
		clearPercepts("mantoiletsensor"+count);
		addPercept("mantoiletsensor"+count, Literal.parseLiteral("takenUrin"));
		
		ManToilet mt;
		if (count <= 7) {
			mt = wm.getManToiletListE().get(count-1);
		} else if (count <= 14) {
			mt = wm.getManToiletListL().get(count-8);
		} else {
			mt = wm.getManToiletListB().get(count-15);
		}
		
		mt.decUrine();
		
		view.setTextOfManUrinal(String.valueOf(mt.getUrine()));
	}
	
	public void ManUrinalFree(String param) {
		int count=toiletmap.get(param);
		clearPercepts("mantoiletsensor"+count);
		addPercept("mantoiletsensor"+count, Literal.parseLiteral("freeUrin"));
		
		ManToilet mt;
		if (count <= 7) {
			mt = wm.getManToiletListE().get(count-1);
		} else if (count <= 14) {
			mt = wm.getManToiletListL().get(count-8);
		} else {
			mt = wm.getManToiletListB().get(count-15);
		}
		
		mt.incUrine();
		
		view.setTextOfManUrinal(String.valueOf(mt.getUrine()));
	}
	
	public String getManToilet(String param) {
		int count=toiletmap.get(param);
		ManToilet mt;
		
		if (count <= 7) {
			mt = wm.getManToiletListE().get(count-1);
		} else if (count <= 14) {
			mt = wm.getManToiletListL().get(count-8);
		} else {
			mt = wm.getManToiletListB().get(count-15);
		}
		
		return String.valueOf(mt.getToilet());
	}
	
	public String getManUrine(String param) {
		int count=toiletmap.get(param);
		ManToilet mt;
		
		if (count <= 7) {
			mt = wm.getManToiletListE().get(count-1);
		} else if (count <= 14) {
			mt = wm.getManToiletListL().get(count-8);
		} else {
			mt = wm.getManToiletListB().get(count-15);
		}
		
		return String.valueOf(mt.getUrine());
	}

	/** Called before the end of MAS execution */
	@Override
	public void stop() {
		super.stop();
	}


}