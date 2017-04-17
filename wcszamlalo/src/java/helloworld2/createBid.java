// Internal action code for project wcszamlalo

package helloworld2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
    	WorldModel wm=WorldModel.get();
    	
    	String wing=args[0].toString().substring(1,2);
    	String type=args[2].toString().substring(1,args[2].toString().lastIndexOf("\""));
    	String myWing=args[3].toString().substring(1,2);
    
    	String levelstring=args[1].toString().substring(1,args[1].toString().lastIndexOf("\""));
    	int level=Integer.parseInt(levelstring);
    	
    	int answer = 0;
    	
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
    			answer = 2;
    		}
    	} else if (myWing.equals("B")) {
    		if (wing.equals("E")) {
    			answer = 3;
    		} else if (wing.equals("L")) {
    			answer = 2;
    		} else if (wing.equals("B")) {
    			answer = 1;
    		}
    	}
    	
    	int[] toiletmap = null;
    	
    	if(type.equals("manToilet")){
    		List<ManToilet> toilets=wm.getMyWingManToilets(myWing);	
    		
    		toiletmap = new int[toilets.size()];
    		
    		int i = 0;
    		for (ManToilet mt : toilets) {
    			toiletmap[i++] = mt.getToilet(); 
    		}
    	} else if (type.equals("manUrinal")) {
    		List<ManToilet> toilets=wm.getMyWingManToilets(myWing);	
    		
    		toiletmap = new int[toilets.size()];
    		
    		int i = 0;
    		for (ManToilet mt : toilets) {
    			toiletmap[i++] = mt.getUrine(); 
    		}
    	} else if (type.equals("womanToilet")) {
    		answer=new Random().nextInt(100);
    	} else if (type.equals("disabledToilet")) {
    		answer=new Random().nextInt(100);
    	}
    	
    	int index = findClosestFree(toiletmap, level+2);
    	int a = index - 2;
    	answer += Math.abs(a - level);
    	
    	System.out.println("A(z) "+ myWing + " szarnyon levo agens valasztottja: "+myWing+a);
    	
    	return un.unifies(args[4],new NumberTermImpl(answer));
    }

	private int findClosestFree(int[] toiletmap, int level) {
		if (toiletmap[level] > 0)
			return level;
		
		int indexInc = level, indexDec = level;
		
		boolean over = false;
		while (!over) {
			indexDec--;
			indexInc++;
			
			if (toiletmap[indexInc] != 0 && toiletmap[indexDec] != 0) {
				if (toiletmap[indexInc] >= toiletmap[indexDec])
					return indexInc;
				else
					return indexDec;
			}
			
			if (indexDec == -1 && indexInc == toiletmap.length)
				return -1;
		}
		
		return -1;
	}
}
