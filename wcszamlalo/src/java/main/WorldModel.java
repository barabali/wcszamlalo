package main;

import jason.environment.grid.GridWorldModel;

import java.util.ArrayList;
import java.util.List;

import main.rooms.DisabledToilet;
import main.rooms.ManToilet;
import main.rooms.WomanToilet;

public class WorldModel extends GridWorldModel{
	
	List<ManToilet> manToiletListE;
	List<ManToilet> manToiletListB;
	List<ManToilet> manToiletListL;
	List<WomanToilet> womanToiletListE;
	List<WomanToilet> womanToiletListB;
	List<WomanToilet> womanToiletListL;
	List<DisabledToilet> disabledToiletList;
	
	// singleton pattern
    protected static WorldModel model = null;
    
    synchronized public static WorldModel create() {
        if (model == null) {
            model = new WorldModel();
        }
        return model;
    }
    
    public static WorldModel get() {
        return model;
    }
	
	protected WorldModel() {
		super(1,1,1);
		
		manToiletListB=new ArrayList<ManToilet>();
		manToiletListE=new ArrayList<ManToilet>();
		manToiletListL=new ArrayList<ManToilet>();
		womanToiletListB=new ArrayList<WomanToilet>();
		womanToiletListL=new ArrayList<WomanToilet>();
		womanToiletListE=new ArrayList<WomanToilet>();
		
		for(int i=0;i<7;i++){
			manToiletListB.add(new ManToilet(3, 7));
			manToiletListE.add(new ManToilet(2, 3));
			manToiletListL.add(new ManToilet(2, 3));
			womanToiletListB.add(new WomanToilet(5));
			womanToiletListE.add(new WomanToilet(3));
			womanToiletListL.add(new WomanToilet(3));
		}
	}

	public List<ManToilet> getManToiletListE() {
		return manToiletListE;
	}

	public List<ManToilet> getManToiletListB() {
		return manToiletListB;
	}

	public List<ManToilet> getManToiletListL() {
		return manToiletListL;
	}

	public List<WomanToilet> getWomanToiletListE() {
		return womanToiletListE;
	}

	public List<WomanToilet> getWomanToiletListB() {
		return womanToiletListB;
	}

	public List<WomanToilet> getWomanToiletListL() {
		return womanToiletListL;
	}

	public List<DisabledToilet> getDisabledToiletList() {
		return disabledToiletList;
	}
	
	

	
}
