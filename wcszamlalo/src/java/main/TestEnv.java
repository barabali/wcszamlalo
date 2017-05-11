package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
		WorldModel.create();
		wm = WorldModel.get();
		
		int count = 1;

		List<ManToilet> manToilets = wm.getManToiletListE();
		
		for (int i = 0; i < manToilets.size(); ++i) {
			toiletmap.put("IE"+String.valueOf(i)+"10",count);
			count++;
		}
		
		manToilets = wm.getManToiletListL();
		
		for (int i = 0; i < manToilets.size(); ++i) {
			toiletmap.put("IL"+String.valueOf(i)+"08",count);
			count++;
		}
		
		manToilets = wm.getManToiletListB();
		
		for (int i = 0; i < manToilets.size(); ++i) {
			toiletmap.put("IB"+String.valueOf(i)+"04",count);
			count++;
		}
		
		addPercept("foglaltszamlalo1",Literal.parseLiteral("szarny(\"E\")"));
		addPercept("foglaltszamlalo2",Literal.parseLiteral("szarny(\"L\")"));
		addPercept("foglaltszamlalo3",Literal.parseLiteral("szarny(\"B\")"));
		
		view = new View(wm, "WCCounter", 350, this);
		
	}

	@Override
	public boolean executeAction(String agName, Structure action) {
		if (action.getFunctor().equals("burn")) {
			addPercept("person", Literal.parseLiteral("change"));
			return true;
		}
		if(action.getFunctor().equals("printResult")){
			String command=action.toString();
			String res=command.substring(command.indexOf("(")+1,command.indexOf(")"));
			view.showResult(res);
			return true;
		}

		return false;
	}
	
	private ManToilet getManToiletByParam(String param) {
		int count=toiletmap.get(param);
		
		if (count <= 5) {
			return wm.getManToiletListE().get(count-1);
		} else if (count <= 10) {
			return wm.getManToiletListL().get(count-6);
		} else {
			return wm.getManToiletListB().get(count-11);
		}
	}

	public void ManToiletTaken(String param) {
		ManToilet mt = getManToiletByParam(param);
		
		mt.decToilet();
		
		view.setTextOfManToilet(String.valueOf(mt.getToilet()));
	}
	
	public void ManToiletFree(String param) {
		ManToilet mt = getManToiletByParam(param);
		
		mt.incToilet();
		
		view.setTextOfManToilet(String.valueOf(mt.getToilet()));
	}
	
	public void ManUrinalTaken(String param) {
		ManToilet mt = getManToiletByParam(param);
		
		mt.decUrine();
		
		view.setTextOfManUrinal(String.valueOf(mt.getUrine()));
	}
	
	public void ManUrinalFree(String param) {
		ManToilet mt = getManToiletByParam(param);
		
		mt.incUrine();
		
		view.setTextOfManUrinal(String.valueOf(mt.getUrine()));
	}

	public String getManToilet(String param) {
		ManToilet mt = getManToiletByParam(param);
		
		return String.valueOf(mt.getToilet());
	}
	
	public String getManUrine(String param) {
		ManToilet mt = getManToiletByParam(param);
		
		return String.valueOf(mt.getUrine());
	}
	
	public String[] getRooms() {
		Set<String> s = toiletmap.keySet();
		
		String[] rooms = new String[s.size()];
		int i = 0;
		
		for (String a : s) {
			rooms[i++] = a;
		}
		
		return rooms;
	}
	
	public void findWc(String pos,String level, String type){
		clearPercepts("utbaigazito");
		addPercept("utbaigazito",Literal.parseLiteral("getWc("+
				"\""+pos+"\""+
				","+
				"\""+level+"\""+
				","+
				"\""+type+"\""+
				")")
		);
	}

	/** Called before the end of MAS execution */
	@Override
	public void stop() {
		super.stop();
	}

	public String getRoomNumber(String res) {
		Set<String> s = toiletmap.keySet();
		
		for (String a : s) {
			if (a.contains(res)) {
				return a;
			}
		}		
		return null;
	}


}