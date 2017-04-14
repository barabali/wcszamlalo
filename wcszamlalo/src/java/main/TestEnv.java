package main;

import jason.asSyntax.*;
import jason.environment.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.*;

public class TestEnv extends jason.environment.Environment {

	private Logger logger = Logger.getLogger("testenv.mas2j."
			+ TestEnv.class.getName());
	List<String> asd = new ArrayList<String>();
	View view;

	/** Called before the MAS execution with the args informed in .mas2j */
	@Override
	public void init(String[] args) {
//		addPercept("mantoiletsensor1", Literal.parseLiteral("available(2,3)"));
		addPercept("mantoiletsensor2", Literal.parseLiteral("available(2,3)"));
		addPercept("mantoiletsensor3", Literal.parseLiteral("available(2,3)"));
		addPercept("mantoiletsensor4", Literal.parseLiteral("available(2,3)"));
		addPercept("mantoiletsensor5", Literal.parseLiteral("available(2,3)"));
		addPercept("mantoiletsensor1",
				Literal.parseLiteral("position(\"I E 010\")"));
		addPercept("mantoiletsensor2",
				Literal.parseLiteral("position(\"I E 110\")"));
		addPercept("mantoiletsensor3",
				Literal.parseLiteral("position(\"I E 210\")"));
		addPercept("mantoiletsensor4",
				Literal.parseLiteral("position(\"I E 310\")"));
		addPercept("mantoiletsensor5",
				Literal.parseLiteral("position(\"I E 410\")"));

		WorldModel wm = new WorldModel();
		view = new View(wm, "WCCounter", 350);
		view.setEnv(this);
	}

	@Override
	public boolean executeAction(String agName, Structure action) {
		if (action.getFunctor().equals("burn")) {
			addPercept("person", Literal.parseLiteral("change"));
			return true;
		}

		if (action.getFunctor().equals("sensordetect")) {
			addPercept("mantoiletsensor1", Literal.parseLiteral("takenWc"));
			return true;
		}

		return false;
	}

	public void ManToiletTaken() {
		clearPercepts("mantoiletsensor1");
		addPercept("mantoiletsensor1", Literal.parseLiteral("takenWc"));
	}
	
	public void ManToiletFree() {
		clearPercepts("mantoiletsensor1");
		addPercept("mantoiletsensor1", Literal.parseLiteral("freeWc"));
	}
	
	public void ManUrinalTaken() {
		clearPercepts("mantoiletsensor1");
		addPercept("mantoiletsensor1", Literal.parseLiteral("takenUrin"));
	}
	
	public void ManUrinalFree() {
		clearPercepts("mantoiletsensor1");
		addPercept("mantoiletsensor1", Literal.parseLiteral("freeUrin"));
	}


	/** Called before the end of MAS execution */
	@Override
	public void stop() {
		super.stop();
	}


}