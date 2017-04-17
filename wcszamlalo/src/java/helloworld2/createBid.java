// Internal action code for project wcszamlalo

package helloworld2;

import java.util.List;
import java.util.Random;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Term;
import main.WorldModel;
import main.rooms.ManToilet;

public class createBid extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
    	WorldModel wm=WorldModel.get();
    	
    	String wing=args[0].toString();
    	String type=args[2].toString();
    	String myPos=args[3].toString();
    	
    	int level=Integer.parseInt(args[1].toString());
    	
    	if(type.equals("manToilet") || type.equals("manUrine")){
    		List<ManToilet> toilets=wm.getMyWingManToilets(wing);	
    	} else if (type.equals("womanToilet")) {
    		
    	} else if (type.equals("disabledToilet")) {
    		
    	}
    	
    	int answer=new Random().nextInt(100);
    	
    	return un.unifies(args[4],new NumberTermImpl(answer));
    }
}
