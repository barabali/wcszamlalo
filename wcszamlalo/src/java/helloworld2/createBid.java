// Internal action code for project wcszamlalo

package helloworld2;

import java.util.List;
import java.util.Random;

import main.WorldModel;
import main.rooms.ManToilet;
import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;

public class createBid extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
    	WorldModel wm=WorldModel.get();
    	String wing=args[0].toString();
//    	int level=Integer.parseInt(args[1].toString());
    	String type=args[2].toString();
    	
    	String myPos=args[3].toString();
    	
    	if(type.equals("manToilet") || type.equals("manUrine")){
    		List<ManToilet> toilets=wm.getMyWingManToilets(wing);
    		
    	}
    	int answer=new Random().nextInt(100);
    	return un.unifies(args[4],new NumberTermImpl(answer));
    }
}
