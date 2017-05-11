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
		
		for(int i=0;i<5;i++){
			manToiletListB.add(new ManToilet(3, 6));
			manToiletListE.add(new ManToilet(2, 4));
			manToiletListL.add(new ManToilet(2, 4));
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
	
	public List<ManToilet> getMyWingManToilets(String wing){
		switch(wing){
		case "E":
			return manToiletListE;
		case "B":
			return manToiletListB;
		default:
			return manToiletListL;
		}
	}

	public List<WomanToilet> getMyWingWomanToilets(String wing){
		switch(wing){
		case "E":
			return womanToiletListE;
		case "B":
			return womanToiletListB;
		default:
			return womanToiletListL;
		}
	}
}
