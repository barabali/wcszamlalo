package jadedf;
	
import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;
import jason.asSemantics.*;
import jason.asSyntax.*;
import jason.infra.jade.JadeAgArch;

import java.util.logging.Logger;
	
/** 
 * Register a service in the jade DF (available only when the JADE infrastructure is used)
 * This internal action does not replace the services of the agent but rather adds a new service.
 * The first argument is the service type and the second is the name (they should be of type String).
 */
public class register extends DefaultInternalAction {

    private Logger logger = Logger.getLogger("JadeDF.mas2j."+register.class.getName());

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        try {
            if (ts.getUserAgArch().getArchInfraTier() instanceof JadeAgArch) {
// get a reference to the JADE agent that represents this Jason agent
                JadeAgArch infra = (JadeAgArch)ts.getUserAgArch().getArchInfraTier();

// 0. get arguments from the AgentSpeak code (type and name of the new service)
                StringTerm type = (StringTerm)args[0];
                StringTerm name = (StringTerm)args[1];
                
// 1. get current services
                DFAgentDescription dfd = new DFAgentDescription();
                dfd.setName(infra.getAID());

                DFAgentDescription list[] = DFService.search( infra, dfd );

// 2. deregister
                if ( list.length > 0 ) { 
                    DFService.deregister(infra);
                    dfd = list[0]; // the first result 
                }

// 3. add a new services
                ServiceDescription sd = new ServiceDescription();
                sd.setType(type.getString());
                sd.setName(name.getString());
                dfd.addServices(sd);
                
// 4. register again
                DFService.register(infra, dfd);
                
                return true;
            } else {
                logger.warning("jadefd.register can be used only with JADE infrastructure.");
            }
        } catch (Exception e) {
            logger.warning("Error in internal action 'jadedf.register'! "+e);
        }
        return false;
    }
}

