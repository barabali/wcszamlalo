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
	 * A map amiben terem - szĂˇm pĂˇrosok vannak, ezzel lehet kezelni 
	 * hogy melyik wc listĂˇt kĂ©rjĂĽk le (5-Ă©rtĂ©kenkĂ©nt mĂˇsikat)
	 */
	private Map<String,Integer> mantoiletmap=new HashMap<String,Integer>();
	private Map<String,Integer> womantoiletmap=new HashMap<String,Integer>();
	private Map<String,Integer> disabledtoiletmap=new HashMap<String,Integer>();
	
	/**
	 * A wc-k termĂ©nek rendezett listĂˇja, a control panelen hogy sorban legyen itt Ă¶sszegyĹ±jti
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
		
		view = new View(wm, "WCCounter", 450, this);
		
	}

	/**
	 * A toiletMap-ba generĂˇlja le a wc-k terem listĂˇjĂˇt, Ă©s a hozzĂˇ tartozĂł countot
	 * A toiletRoomsInOrder-be teszi sorrendben a wc-ket
	 * @param count
	 */
	private void createToiletListForToiletMap() {
		int count=1;
		//FĂ©rfi wc-kre
		List<ManToilet> manToilets = wm.getManToiletListE();
		
		for (int i = 0; i < manToilets.size(); ++i) {
			mantoiletmap.put("IE"+String.valueOf(i)+"10",count);		//A toiletmapba helyezi
			toiletRoomsInOrder.add("IE"+String.valueOf(i)+"10");	//A rendezett listĂˇba szĂşrja
			count++;
		}
		
		manToilets = wm.getManToiletListL();
		
		for (int i = 0; i < manToilets.size(); ++i) {
			mantoiletmap.put("IL"+String.valueOf(i)+"08",count);
			toiletRoomsInOrder.add("IL"+String.valueOf(i)+"08");
			count++;
		}
		
		manToilets = wm.getManToiletListB();
		
		//A fĂ¶ldszinten eltĂ©r a szĂˇmozĂˇs
		mantoiletmap.put("IB009",count);
		toiletRoomsInOrder.add("IB009");
		count++;
		
		for (int i = 1; i < manToilets.size(); ++i) {
			mantoiletmap.put("IB"+String.valueOf(i)+"04",count);
			toiletRoomsInOrder.add("IB"+String.valueOf(i)+"04");
			count++;
		}
		
		//NĹ‘i wc-kre ugyanez
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
		
		//FĂ¶ldszinten eltĂ©r a szĂˇmozĂˇs
		womantoiletmap.put("IB010",count);
		toiletRoomsInOrder.add("IB010");
		count++;
		
		for (int i = 1; i < womanToilets.size(); ++i) {
			womantoiletmap.put("IB"+String.valueOf(i)+"05",count);
			toiletRoomsInOrder.add("IB"+String.valueOf(i)+"05");
			count++;
		}
		
		//MozgĂˇssĂ©rĂĽltbĹ‘l csak 1 van
		disabledtoiletmap.put("IB011", 0);
		toiletRoomsInOrder.add("IB011");
	}

	/**
	 * Ă�gens parancshĂ­vĂˇs esetĂ©n ez kezeli
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
	 * FĂ©rfivĂ©cĂ©re
	 * A terem neve alajĂˇn generĂˇl szĂˇmot, hogy melyik wc listĂˇbĂłl hĂˇnyas wc referenciĂˇt 
	 * keresse elĹ‘
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
	 * NĹ‘ivĂ©cĂ©re
	 * A terem neve alajĂˇn generĂˇl szĂˇmot, hogy melyik wc listĂˇbĂłl hĂˇnyas wc referenciĂˇt 
	 * keresse elĹ‘
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
	 * MozgĂˇssĂ©rĂĽlt wc-re
	 * A terem neve alajĂˇn generĂˇl szĂˇmot, hogy melyik wc listĂˇbĂłl hĂˇnyas wc referenciĂˇt 
	 * keresse elĹ‘
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
	 * Kezeli a fĂ©rfi wc-kben tĂ¶rtĂ©nĹ‘ foglalĂˇst, felszabadulĂˇst
	 * Ha nincs ilyen wc, vagy takarĂ­tva van akkor nem vĂˇlt
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
				mt.incToilet();		//nĂ¶vel
			else
				mt.decToilet();		//csĂ¶kkent
		}else{						//Ha piszĂłĂˇr
			if(increase)
				mt.incUrine();		//nĂ¶vel
			else
				mt.decUrine();		//csĂ¶kkent
		}
		
		view.setTextOfManToilet(String.valueOf(mt.getToilet()));
		view.setTextOfManUrinal(String.valueOf(mt.getUrine()));
	}

	/**
	 * Kezeli a nĹ‘i wc-kben tĂ¶rtĂ©nĹ‘ foglalĂˇst, felszabadulĂˇst
	 * Ha nincs ilyen wc, vagy takarĂ­tva van akkor nem vĂˇlt
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
				mt.incToilet();		//nĂ¶vel
			else
				mt.decToilet();		//csĂ¶kkent
		
		view.setTextOfWomanToilet(String.valueOf(mt.getToilet()));
	}
	
	/**
	 * Kezeli a mozgĂˇskorlĂˇtozott wc-(k)ben tĂ¶rtĂ©nĹ‘ foglalĂˇst, felszabadulĂˇst
	 * Ha nincs ilyen wc, vagy takarĂ­tva van akkor nem vĂˇlt
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
				mt.incToilet();		//nĂ¶vel
			else
				mt.decToilet();		//csĂ¶kkent
		
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
	 * VisszatĂ©r a paramĂ©terĂĽl kapott wc-ben lĂ©vĹ‘ szabad wc-k szĂˇmĂˇval
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
	 * VisszatĂ©r a paramĂ©terĂĽl kapott wc-ben lĂ©vĹ‘ szabad piszĂłĂˇrok szĂˇmĂˇval
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
	 * VisszatĂ©r a paramĂ©terĂĽl kapott nĹ‘i wc-ben lĂ©vĹ‘ szabad wc-k szĂˇmĂˇval
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
	 * VisszatĂ©r a paramĂ©terĂĽl kapott mozgĂˇssĂ©rĂĽlt wc-ben lĂ©vĹ‘ szabad wc-k szĂˇmĂˇval
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
	 * A control fĂĽlre valĂł termek listĂˇjĂˇt adja vissza
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
	 * Visszaadja a vĂ©geredmĂ©ny teremszĂˇmot a kapott
	 * emelet-szĂˇrny-tĂ­pus paramĂ©terek alapjĂˇn
	 * @param res
	 * @return
	 */
	public String getRoomNumber(String res) {
		//ElsĹ‘ paramĂˇter: szĂˇrny, elemet
		//MĂˇsodik: tĂ­pus
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
			Set<String> s = disabledtoiletmap.keySet();
			
			for (String a : s) {
				if (a.contains(parameters[0])) {
					return a;
				}
			}
		}
		Set<String> s = mantoiletmap.keySet();
		
		for (String a : s) {
			if (a.contains(parameters[0])) {
				return a;
			}
		}		
		return "";
	}

}