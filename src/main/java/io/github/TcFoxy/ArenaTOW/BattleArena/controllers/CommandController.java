package io.github.TcFoxy.ArenaTOW.BattleArena.controllers;


import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;


public class CommandController {

//    public static CommandMap getCommandMap() {
//        Version version = Util.getCraftBukkitVersion();
//        final Class<?> clazz;
//        try {
//            if (version.compareTo("0") == 0 || version.getVersion().equalsIgnoreCase("craftbukkit")) {
//                clazz = Class.forName("org.bukkit.craftbukkit.CraftServer");
//            } else {
//                clazz = Class.forName("org.bukkit.craftbukkit." + version.getVersion() + ".CraftServer");
//            }
//        } catch (ClassNotFoundException e) {
//            Log.printStackTrace(e);
//            return null;
//        }
//        return getCommandMapFromServer(clazz);
//    }
	
    public static CommandMap getCommandMap() {
        return null;
    }
//
//    private static CommandMap getCommandMapFromServer(Class<?> serverClass) {
//        try {
//            if (serverClass.isAssignableFrom(Bukkit.getServer().getClass())) {
//                final Field f = serverClass.getDeclaredField("commandMap");
//                f.setAccessible(true);
//                return (CommandMap) f.get(Bukkit.getServer());
//            }
//        } catch (final SecurityException e) {
//            System.out.println("You will need to disable the security manager to use dynamic commands");
//        } catch (final Exception e) {
//            Log.printStackTrace(e);
//        }
//        return null;
//    }

    public static void registerCommand(final Command command) {
        CommandMap commandMap = getCommandMap();
        if (commandMap != null) {
            commandMap.register("/", command);
        }
    }
}
