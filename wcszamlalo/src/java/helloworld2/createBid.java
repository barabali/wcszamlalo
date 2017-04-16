// Internal action code for project wcszamlalo

package helloworld2;

import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;

public class createBid extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        // execute the internal action
    	String asd=args[0].toString();
        ts.getAg().getLogger().info(asd);
        
        // everything ok, so returns true
        return true;
    }
}
