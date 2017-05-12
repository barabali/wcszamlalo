// Internal action code for project wcszamlalo

package main.defaultinternalactions;

import java.util.List;
import java.util.Random;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Atom;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Term;
import main.WorldModel;
import main.rooms.DisabledToilet;
import main.rooms.ManToilet;
import main.rooms.WomanToilet;

public class createBid extends DefaultInternalAction {

	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] args)
			throws Exception {
		WorldModel wm = WorldModel.get();

		String wing = args[0].toString().substring(1, 2);
		String type = args[2].toString().substring(1,
				args[2].toString().lastIndexOf("\""));
		String myWing = args[3].toString().substring(1, 2);

		String levelstring = args[1].toString().substring(1,
				args[1].toString().lastIndexOf("\""));
		int level = Integer.parseInt(levelstring);

		int answer = 0;

		// Meghatározom hogy milyen messze vagyok a választott szárnytól
		answer = calculateWingDistance(wing, myWing, answer);

		// a szárnyon lévő szabad helyek száma emeletenként
		int[] toiletmap = null;

		// A választott wc fajtája alapján elkéri a szárny szabad wc-it
		
		//Férfi wc
		if (type.equals("manToilet")) {
			List<ManToilet> toilets = wm.getMyWingManToilets(myWing);

			toiletmap = new int[toilets.size()];

			int i = 0;
			for (ManToilet mt : toilets) {
				toiletmap[i++] = mt.getToilet();
			}
		} 
		//Férfi piszóár
		else if (type.equals("manUrinal")) {
			List<ManToilet> toilets = wm.getMyWingManToilets(myWing);

			toiletmap = new int[toilets.size()];

			int i = 0;
			for (ManToilet mt : toilets) {
				toiletmap[i++] = mt.getUrine();
			}
		} 
		//Női wc
		else if (type.equals("womanToilet")) {
			List<WomanToilet> toilets = wm.getMyWingWomanToilets(myWing);

			toiletmap = new int[toilets.size()];

			int i = 0;
			for (WomanToilet mt : toilets) {
				toiletmap[i++] = mt.getToilet();
			}
		} 
		//Ha mozgáskorlátozott akkor is végigszámolja a dolgokat, de nem számít a végén
		if (type.equals("disabledToilet")) {
			List<DisabledToilet> toilets = wm.getMyWingDisabledToilets(myWing);

			toiletmap = new int[toilets.size()];

			int i = 0;
			for (DisabledToilet mt : toilets) {
				toiletmap[i++] = mt.getToilet();
			}
		}

		//Ha minusz emeleten lennénk, az olyan mint a földszint
		if(level<0){
			level=0;
		}
		
		int index = findClosestFree(toiletmap, level);		
		int foundLevel = index ;
		
		//Ha a keresett és talált wc is a földszinten van, a az átjárás miatt bónuszt kap
		if(level==0 && foundLevel==0){
			answer-=2;
		}
		
		//A szárnyak távolságához hozzáadja az emelet különbséget
		answer += Math.abs(foundLevel-level);

		System.out.println("A(z) " + myWing
				+ " szarnyon levo agens valasztottja: " + myWing + foundLevel);
		
		System.out.println("A(z) " + myWing
				+ " szarnyon levo agens licitértéke: " + answer);

		//Ha mozgáskorlátozott akkor csak 1 terem lehet a vége
		if (type.equals("disabledToilet")) {
			answer=0;
			myWing="B";
		}
		
		
		//Visszatér a talált emelet-szárny-típus kombinációval
		boolean result = un.unifies(args[4], new Atom(myWing + foundLevel+":"+type + ""));
		//És visszatér a licit értékével
		return result && un.unifies(args[5], new NumberTermImpl(answer));
	}

	/**
	 * Meghatározom hogy milyen messze vagyok a választott szárnytól
	 * 1-saját 2-azonos oldal 3-másik oldal
	 * @param wing
	 * @param myWing
	 * @param answer
	 * @return
	 */
	private int calculateWingDistance(String wing, String myWing, int answer) {
		if (myWing.equals("E")) {
			if (wing.equals("E")) {
				answer = 1;
			} else if (wing.equals("L")) {
				answer = 2;
			} else if (wing.equals("B")) {
				answer = 3;
			}
		} else if (myWing.equals("L")) {
			if (wing.equals("E")) {
				answer = 2;
			} else if (wing.equals("L")) {
				answer = 1;
			} else if (wing.equals("B")) {
				answer = 3;
			}
		} else if (myWing.equals("B")) {
			if (wing.equals("E")) {
				answer = 3;
			} else if (wing.equals("L")) {
				answer = 3;
			} else if (wing.equals("B")) {
				answer = 1;
			}
		}
		return answer;
	}

	/**
	 * Leközelebbi szabad wc megtalálása a szárnyon
	 * 
	 * @param toiletmap
	 * @param level
	 * @return
	 */
	private int findClosestFree(int[] toiletmap, int level) {
		//Ha az emeleten ahol a felhasználó van nincs wc ez nem fut le
		if(toiletmap.length>=level){			
		// Ha a saját emeleten van szabad hely, rögtön visszatér vele
		if (toiletmap[level] > 0)
			return level;
		}
		

		int indexInc = level, indexDec = level;

		boolean over = false;

		// Felfele-lefele lépked
		while (!over) {
			indexDec--;
			indexInc++;

			// Nem indexelhet minuszba
			if (indexDec == -1)
				indexDec = 0;
			
			//Minimum sem mehet a maximum fölé
			if(indexDec >=toiletmap.length-1)
				indexDec=0;

			// Nem mehet a tömb mérete fölé
			if (indexInc >= toiletmap.length - 1)
				indexInc = toiletmap.length - 1;

			// Ha az egyikben több hely van, akkor azzal tér vissza
			//Lefele preferáltabb, arra elvégre könnyebb haladni :D
			if (toiletmap[indexInc] != 0 || toiletmap[indexDec] != 0) {
				if (toiletmap[indexDec] >= toiletmap[indexInc])
					return indexDec;
				else
					return indexInc;
			}
			
			//Ha mindkét irányban tele, akkor 
			if (indexDec == 0 && indexInc == toiletmap.length-1)
				return 99;		//Ilyen teremszám nincs, így ezzel tér vissza
		}

		return -1;
	}
}
