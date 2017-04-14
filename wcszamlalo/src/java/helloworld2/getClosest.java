// Internal action code for project helloworld2

package helloworld2;

import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;

public class getClosest extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        // execute the internal action
//        ts.getAg().getLogger().info("executing internal action 'helloworld2.GetClosest'");
//        if (true) { // just to show how to throw another kind of exception
//            throw new JasonException("not implemented!");
//        }
        String place=args[0].toString();
        ts.getAg().getLogger().info(place);
        // everything ok, so returns true
        return true;
    }
}
