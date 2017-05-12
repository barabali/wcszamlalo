// Internal action code for project wcszamlalo

package helloworld2;

import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;

public class winner extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
    	
    	//Leveszi a szélső zárójeleket, és a liciteket elválasztja, első 'b'-t levágja
    	String substring=args[0].toString().substring(2,args[0].toString().length()-1);
    	    	
    	String[] licits=substring.split(",b");
    	
    	//Licittermek tömbbe rendezése
    	String[] licitrooms=new String[3];
    	
    	//A belső zárójeleket leveszi,a termet kiválasztja
    	for(int i=0;i<3;i++){
    		licitrooms[i]=licits[i].substring(1,licits[i].length()-1).split(",")[0];
    	}
    	
    	//licit értékek
    	int[] licitvalues=new int[3];
    	
    	//A belső zárójeleket leveszi, és a licitet kiválasztja, integerré parszol
    	for(int i=0;i<3;i++){
    		licitvalues[i]=Integer.parseInt(licits[i].substring(1,licits[i].length()-1).split(",")[1]);
    	}
    	    	
    	int index=findBest(licitvalues);
    	
        return un.unifies(args[1], new Atom(licitrooms[index] + ""));
    }

	private int findBest(int[] licitvalues) {
		int min=licitvalues[0];
		int index=0;
		for(int i=1;i<licitvalues.length;i++){
			if(licitvalues[i]<min){
				min=licitvalues[i];
				index=i;
			}
		}
		
		
		return index;
	}

}
