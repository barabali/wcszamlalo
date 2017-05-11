// Internal action code for project wcszamlalo

package helloworld2;

import java.util.List;
import java.util.Random;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Atom;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Term;
import main.WorldModel;
import main.rooms.ManToilet;

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
		// 1-saját 2-azonos oldal 3-másik oldal
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

		// a szárnyon lévő szabad helyek száma emeletenként
		int[] toiletmap = null;

		// A választott wc fajtája alapján elkéri a szárny wc-it
		if (type.equals("manToilet")) {
			List<ManToilet> toilets = wm.getMyWingManToilets(myWing);

			toiletmap = new int[toilets.size()];

			int i = 0;
			for (ManToilet mt : toilets) {
				toiletmap[i++] = mt.getToilet();
			}
		} else if (type.equals("manUrinal")) {
			List<ManToilet> toilets = wm.getMyWingManToilets(myWing);

			toiletmap = new int[toilets.size()];

			int i = 0;
			for (ManToilet mt : toilets) {
				toiletmap[i++] = mt.getUrine();
			}
		} else if (type.equals("womanToilet")) {
			answer = new Random().nextInt(100);
		} else if (type.equals("disabledToilet")) {
			answer = new Random().nextInt(100);
		}

		//Ha minusz emeleten lennénk, az olyan mint a földszint
		if(level<0){
			level=0;
		}
		int index = findClosestFree(toiletmap, level);		
		int foundLevel = index ;
		answer += Math.abs(foundLevel-level);

		System.out.println("A(z) " + myWing
				+ " szarnyon levo agens valasztottja: " + myWing + foundLevel);
		
		System.out.println("A(z) " + myWing
				+ " szarnyon levo agens licitértéke: " + answer);

		boolean result = un.unifies(args[4], new Atom(myWing + foundLevel + ""));
		return result && un.unifies(args[5], new NumberTermImpl(answer));
	}

	/**
	 * Leközelebbi szabad wc megtalálása a szárnyon
	 * 
	 * @param toiletmap
	 * @param level
	 * @return
	 */
	private int findClosestFree(int[] toiletmap, int level) {
		// Ha a saját emeleten van szabad hely, rögtön visszatér vele
		if (toiletmap[level] > 0)
			return level;

		int indexInc = level, indexDec = level;

		boolean over = false;

		// Felfele-lefele lépked
		while (!over) {
			indexDec--;
			indexInc++;

			// Nem indexelhet minuszba
			if (indexDec == -1)
				indexDec = 0;

			// Nem mehet a tömb mérete fölé
			if (indexInc == toiletmap.length - 1)
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
				return -1;
		}

		return -1;
	}
}
