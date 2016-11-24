package io.github.TcFoxy.ArenaTOW.BattleArena.controllers;


import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import io.github.TcFoxy.ArenaTOW.BattleArena.objects.arenas.Arena;


public class ArenaDebugger {
	static HashMap<Arena,ArenaDebugger> arenas = null;


	public static ArenaDebugger getDebugger(Arena arena){
		if (arenas == null){
			arenas = new HashMap<Arena, ArenaDebugger>();
		}

		ArenaDebugger ad = arenas.get(arena);
		if (ad == null){
			ad = new ArenaDebugger(arena);
			arenas.put(arena, ad);
		}
		return ad;
	}


	public static void removeDebugger(ArenaDebugger ad) {
		arenas.remove(ad.arena);
		if (arenas.isEmpty()){
			arenas = null;
		}
	}

	final Arena arena;
	HashMap<Location, ItemStack> oldBlocks = new HashMap<Location, ItemStack>(); /// Used for debugging with show/hide spawns

	public ArenaDebugger(Arena arena) {
		this.arena = arena;
	}

	static public Location getLocKey(Location l){
		return new Location(l.getWorld(), l.getBlockX(), l.getBlockY(), l.getBlockZ());
	}

//	public void hideSpawns(Player player) {
//		for (Location l : oldBlocks.keySet()){
//			ItemStack is = oldBlocks.get(l);
//			player.sendBlockChange(l, is.getType(), (byte) is.getDurability());
//		}
//		oldBlocks.clear();
//		SpawnController sc = arena.getSpawnController();		
//		if (sc != null){		
//			sc.stop();		
//		}
//	}
//
//	public void showSpawns(Player player) {
//		oldBlocks = new HashMap<Location,ItemStack>();
//		SpawnController sc = arena.getSpawnController();		+
//		if (sc != null){		
//			sc.start();		
//		}
//		List<List<SpawnLocation>> locs = arena.getSpawns();
//		if (locs != null){
//			for (int i=0;i<locs.size();i++){
//				for (SpawnLocation l : locs.get(i)){
//					changeBlocks(player, l.getLocation(), TeamUtil.getTeamHead(i));
//				}
//			}
//		}
//		locs = arena.getWaitroom() != null ? arena.getWaitroom().getSpawns() : null;
//		if (locs != null){
//			for (int i=0;i<locs.size();i++){
//				for (SpawnLocation l : locs.get(i)){
//					changeBlocks(player, l.getLocation(), TeamUtil.getTeamHead(i));
//				}
//			}
//		}
//		locs = arena.getSpectatorRoom() != null ? arena.getSpectatorRoom().getSpawns() : null;
//		if (locs != null){
//			for (int i=0;i<locs.size();i++){
//				for (SpawnLocation l : locs.get(i)){
//					changeBlocks(player, l.getLocation(), TeamUtil.getTeamHead(i));
//				}
//			}
//		}
//	}
//
//	private void changeBlocks(Player player, Location l, ItemStack is) {
//		Location key = getLocKey(l);
//		if (!oldBlocks.containsKey(key)){
//			Block b = l.getBlock();
//			player.sendBlockChange(l, is.getTypeId(), (byte)is.getDurability());
//			oldBlocks.put(key, new ItemStack(b.getType(), b.getData()));
//		}
//	}

}
