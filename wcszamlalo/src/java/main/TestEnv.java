package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import main.rooms.DisabledToilet;
import main.rooms.ManToilet;
import main.rooms.WomanToilet;

public class TestEnv extends jason.environment.Environment {

	private Logger logger = Logger.getLogger("testenv.mas2j."
			+ TestEnv.class.getName());
	
	/**
	 * A map amiben terem - szám párosok vannak, ezzel lehet kezelni 
	 * hogy melyik wc listát kérjük le (5-értékenként másikat)
	 */
	private Map<String,Integer> mantoiletmap=new HashMap<String,Integer>();
	private Map<String,Integer> womantoiletmap=new HashMap<String,Integer>();
	private Map<String,Integer> disabledtoiletmap=new HashMap<String,Integer>();
	
	/**
	 * A wc-k termének rendezett listája, a control panelen hogy sorban legyen itt összegyűjti
	 */
	private List<String> toiletRoomsInOrder=new LinkedList<String>();
	private View view;
	private WorldModel wm;

	/** Called before the MAS execution with the args informed in .mas2j */
	@Override
	public void init(String[] args) {
		WorldModel.create();
		wm = WorldModel.get();
		

		createToiletListForToiletMap();
		
		addPercept("foglaltszamlalo1",Literal.parseLiteral("szarny(\"E\")"));
		addPercept("foglaltszamlalo2",Literal.parseLiteral("szarny(\"L\")"));
		addPercept("foglaltszamlalo3",Literal.parseLiteral("szarny(\"B\")"));
		
		view = new View(wm, "WCCounter", 370, this);
		
	}

	/**
	 * A toiletMap-ba generálja le a wc-k terem listáját, és a hozzá tartozó countot
	 * A toiletRoomsInOrder-be teszi sorrendben a wc-ket
	 * @param count
	 */
	private void createToiletListForToiletMap() {
		int count=1;
		//Férfi wc-kre
		List<ManToilet> manToilets = wm.getManToiletListE();
		
		for (int i = 0; i < manToilets.size(); ++i) {
			mantoiletmap.put("IE"+String.valueOf(i)+"10",count);		//A toiletmapba helyezi
			toiletRoomsInOrder.add("IE"+String.valueOf(i)+"10");	//A rendezett listába szúrja
			count++;
		}
		
		manToilets = wm.getManToiletListL();
		
		for (int i = 0; i < manToilets.size(); ++i) {
			mantoiletmap.put("IL"+String.valueOf(i)+"08",count);
			toiletRoomsInOrder.add("IL"+String.valueOf(i)+"08");
			count++;
		}
		
		manToilets = wm.getManToiletListB();
		
		//A földszinten eltér a számozás
		mantoiletmap.put("IB009",count);
		toiletRoomsInOrder.add("IB009");
		count++;
		
		for (int i = 1; i < manToilets.size(); ++i) {
			mantoiletmap.put("IB"+String.valueOf(i)+"04",count);
			toiletRoomsInOrder.add("IB"+String.valueOf(i)+"04");
			count++;
		}
		
		//Női wc-kre ugyanez
		List<WomanToilet> womanToilets = wm.getWomanToiletListE();
		for (int i = 0; i < womanToilets.size(); ++i) {
			womantoiletmap.put("IE"+String.valueOf(i)+"11",count);		
			toiletRoomsInOrder.add("IE"+String.valueOf(i)+"11");
			count++;
		}
		
		womanToilets = wm.getWomanToiletListL();
		
		for (int i = 0; i < womanToilets.size(); ++i) {
			womantoiletmap.put("IL"+String.valueOf(i)+"09",count);
			toiletRoomsInOrder.add("IL"+String.valueOf(i)+"09");
			count++;
		}
		
		womanToilets = wm.getWomanToiletListB();
		
		//Földszinten eltér a számozás
		womantoiletmap.put("IB010",count);
		toiletRoomsInOrder.add("IB010");
		count++;
		
		for (int i = 1; i < womanToilets.size(); ++i) {
			womantoiletmap.put("IB"+String.valueOf(i)+"05",count);
			toiletRoomsInOrder.add("IB"+String.valueOf(i)+"05");
			count++;
		}
		
		//Mozgássérültből csak 1 van
		disabledtoiletmap.put("IB011", 0);
		toiletRoomsInOrder.add("IB011");
	}

	/**
	 * Ágens parancshívás esetén ez kezeli
	 */
	@Override
	public boolean executeAction(String agName, Structure action) {
		if(action.getFunctor().equals("printResult")){
			String command=action.toString();
			String res=command.substring(command.indexOf("(")+1,command.indexOf(")"));
			view.showResult(res);
			return true;
		}
		return false;
	}
	
	/**
	 * Férfivécére
	 * A terem neve alaján generál számot, hogy melyik wc listából hányas wc referenciát 
	 * keresse elő
	 * @param param
	 * @return ManToilet
	 */
	private ManToilet getManToiletByParam(String param) {
		Integer count=mantoiletmap.get(param);
		if(count==null){
			return null;
		}
		if (count <= 5) {
			return wm.getManToiletListE().get(count-1);
		} else if (count <= 10) {
			return wm.getManToiletListL().get(count-6);
		} else {
			return wm.getManToiletListB().get(count-11);
		}
	}
	
	/**
	 * Nőivécére
	 * A terem neve alaján generál számot, hogy melyik wc listából hányas wc referenciát 
	 * keresse elő
	 * @param param
	 * @return ManToilet
	 */
	private WomanToilet getWomanToiletByParam(String param) {
		Integer count=womantoiletmap.get(param);
		if(count== null){
			return null;
		}
		if (count <= 20) {
			return wm.getWomanToiletListE().get(count-16);
		} else if (count <= 25) {
			return wm.getWomanToiletListL().get(count-21);
		} else {
			return wm.getWomanToiletListB().get(count-26);
		}
	}
	
	/**
	 * Mozgássérült wc-re
	 * A terem neve alaján generál számot, hogy melyik wc listából hányas wc referenciát 
	 * keresse elő
	 * @param param
	 * @return ManToilet
	 */
	private DisabledToilet getDisabledToiletByParam(String param) {
		Integer count=disabledtoiletmap.get(param);
		if(count== null){
			return null;
		}
		return wm.getDisabledToiletList().get(count);
	}
	
	/**
	 * Kezeli a férfi wc-kben történő foglalást, felszabadulást
	 * Ha nincs ilyen wc, vagy takarítva van akkor nem vált
	 * @param param
	 * @param increase Ha igaz, akkor felszabadul hely
	 * @param type
	 */
	public void ManToiletNumberChange(String param,boolean increase,String type){
		ManToilet mt = getManToiletByParam(param);
		if(mt==null)
			return;
		
		if(mt.getCleaning())
			return;
		
		if(type.equals("Toilet")){	//Ha toilet
			if(increase)
				mt.incToilet();		//növel
			else
				mt.decToilet();		//csökkent
		}else{						//Ha piszóár
			if(increase)
				mt.incUrine();		//növel
			else
				mt.decUrine();		//csökkent
		}
		
		view.setTextOfManToilet(String.valueOf(mt.getToilet()));
		view.setTextOfManUrinal(String.valueOf(mt.getUrine()));
	}

	/**
	 * Kezeli a női wc-kben történő foglalást, felszabadulást
	 * Ha nincs ilyen wc, vagy takarítva van akkor nem vált
	 * @param param
	 * @param increase
	 * @param type
	 */
	public void WomanToiletNumberChange(String param,boolean increase){
		WomanToilet mt = getWomanToiletByParam(param);
		if(mt==null)
			return;
		
		if(mt.getCleaning())
			return;
		
			if(increase)
				mt.incToilet();		//növel
			else
				mt.decToilet();		//csökkent
		
		view.setTextOfWomanToilet(String.valueOf(mt.getToilet()));
	}
	
	/**
	 * Kezeli a mozgáskorlátozott wc-(k)ben történő foglalást, felszabadulást
	 * Ha nincs ilyen wc, vagy takarítva van akkor nem vált
	 * @param param
	 * @param increase
	 * @param type
	 */
	public void DisabledToiletNumberChange(String param,boolean increase){
		DisabledToilet mt = getDisabledToiletByParam(param);
		if(mt==null)
			return;
		
		if(mt.getCleaning())
			return;
		
			if(increase)
				mt.incToilet();		//növel
			else
				mt.decToilet();		//csökkent
		
		view.setTextOfDisabledToilet(String.valueOf(mt.getToilet()));
	}
	
	public void ToiletClean(String param){
		ManToilet mt = getManToiletByParam(param);
		WomanToilet wmt = getWomanToiletByParam(param);
		DisabledToilet dt = getDisabledToiletByParam(param);
		
		if(mt!=null){
			mt.changeCleaning();
			view.setTextOfManUrinal(String.valueOf(mt.getUrine()));
			view.setTextOfManToilet(String.valueOf(mt.getToilet()));
		}
		if(wmt!=null){
			wmt.changeCleaning();
			view.setTextOfWomanToilet(String.valueOf(wmt.getToilet()));
		}
		if(dt!=null){
			dt.changeCleaning();
			view.setTextOfDisabledToilet(String.valueOf(dt.getToilet()));
		}
	}
	
	/**
	 * Visszatér a paraméterül kapott wc-ben lévő szabad wc-k számával
	 * Ha nincs ilyen, akkor -
	 * @param param
	 * @return
	 */
	public String getManToilet(String param) {
		ManToilet mt = getManToiletByParam(param);
		if(mt==null){
			return "-";
		}
		return String.valueOf(mt.getToilet());
	}
	
	/**
	 * Visszatér a paraméterül kapott wc-ben lévő szabad piszóárok számával
	 * Ha nincs ilyen, akkor -
	 * @param param
	 * @return
	 */
	public String getManUrine(String param) {
		ManToilet mt = getManToiletByParam(param);
		if(mt==null){
			return "-";
		}
		return String.valueOf(mt.getUrine());
	}
	
	/**
	 * Visszatér a paraméterül kapott női wc-ben lévő szabad wc-k számával
	 * Ha nincs ilyen akkor -
	 * @param param
	 * @return
	 */
	public String getWomanToilet(String param) {
		WomanToilet wmt = getWomanToiletByParam(param);
		if(wmt==null){
			return "-";
		}
		return String.valueOf(wmt.getToilet());
	}
	
	/**
	 * Visszatér a paraméterül kapott mozgássérült wc-ben lévő szabad wc-k számával
	 * Ha nincs ilyen akkor -
	 * @param param
	 * @return
	 */
	public String getDisabledToilet(String param) {
		DisabledToilet wmt = getDisabledToiletByParam(param);
		if(wmt==null){
			return "-";
		}
		return String.valueOf(wmt.getToilet());
	}
	
	/**
	 * A control fülre való termek listáját adja vissza
	 * @return
	 */
	public String[] getRooms() {
		String[] rooms = new String[toiletRoomsInOrder.size()];
		rooms = toiletRoomsInOrder.toArray(rooms);
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

	/**
	 * Visszaadja a végeredmény teremszámot a kapott
	 * emelet-szárny-típus paraméterek alapján
	 * @param res
	 * @return
	 */
	public String getRoomNumber(String res) {
		//Eleje: szárny, elemet; Második: típus
		String[] parameters=res.split(":");
		
		if(parameters[1].equals("womanToilet")){
			Set<String> s = womantoiletmap.keySet();
			
			for (String a : s) {
				if (a.contains(parameters[0])) {
					return a;
				}
			}
		}
		if(parameters[1].equals("disabledToilet")){
			return "IB011";
		}
		Set<String> s = mantoiletmap.keySet();
		
		for (String a : s) {
			if (a.contains(parameters[0])) {
				return a;
			}
		}		
		return null;
	}

}