//package io.github.TcFoxy.ArenaTOW.Serializable;
//
//import java.util.HashMap;
//import java.util.Map.Entry;
//
//import org.bukkit.Bukkit;
//import org.bukkit.Color;
//import org.bukkit.Location;
//
//public class MinionFactory {
//	
//	private HashMap<String, Minion> minionSpawners = new HashMap<String, Minion>();
//	
//	public MinionFactory(HashMap<String, String> data) {
//		if(data != null){
//			loadPersistence(data);
//		}
//	}
//	
//	//Need to be able to add new minion (I create minion objects of minion type)
//	public Minion createMinion(String name) {		
//		return new Minion(minionSpawners.get(name));
//	}
//
//	//Need to be able to add new minion type (I create a new type of minion)
//	public Boolean addMinionSpawner(String name, String team, Location start) {
//		if(minionSpawners.containsKey(name)) {
//			removeMinionSpawner(name);
//		}
//		Minion m = new Minion(name, team, start);
//		minionSpawners.put(name,m);
//		return true;
//	}
//	
//	public void removeMinionSpawner(String name) {
//		if(minionSpawners.containsKey(name)){
//			minionSpawners.remove(name);
//		}else{
//			Bukkit.broadcastMessage("There is no minion type with that name");
//		}
//		
//	}
//	
//	public String addMinionPathPoint(String name, Location loc) {
//		String buf = "";
//		Minion m = minionSpawners.get(name);
//		if (m == null){
//			return buf;
//		}else if (m.findPathLocation(loc)) {
//			buf = "same location";
//			m.addPathLoc(loc);
//			return buf;
//		}else{
//			m.addPathLoc(loc);
//			buf = m.getPathSize() + "";
//			return buf;
//		}
//		
//	}
//	
//	public void removeMinionPath(String name) {
//		Minion m = minionSpawners.get(name);
//		if (m == null)
//			return;
//		m.clearPath();
//	}
//	
//	public void clearSpawners() {
//		// We can use this to clear the spawners and then use loadPersistence after too
//		minionSpawners.clear();
//	}
//	
//	
//	//Generate persistent data
//	public HashMap<String, String> generatePersistence() {
//		
//		HashMap<String, String> persistSpawners = new HashMap<String, String>();
//		
//		for (Entry<String, Minion> e: minionSpawners.entrySet()) {
//			persistSpawners.put(e.getKey(), e.getValue().serializeMinion());
//		}
//		
//		return persistSpawners; 
//	}
//	
//	public void loadPersistence(HashMap<String, String> data) {
//		for (Entry<String, String> e: data.entrySet()) {
//			minionSpawners.put(e.getKey(), new Minion(e.getKey(), e.getValue()));
//		}
//	}
//}
