package io.github.TcFoxy.ArenaTOW.Plugin.Serializable;

import io.github.TcFoxy.ArenaTOW.API.MobType;
import io.github.TcFoxy.ArenaTOW.API.TOWEntity;
import io.github.TcFoxy.ArenaTOW.Plugin.ArenaTOW;
import org.bukkit.*;

public class Nexus extends PersistInfo {

    public Nexus(String key, Color teamColor, Location loc, String info) {
        super(key, teamColor, loc, info);
    }


    @Override
    public TOWEntity spawnMob() {
        Location spawn = getSpawnLoc();
        setMob(ArenaTOW.getEntityHandler().spawnMob(MobType.NEXUS, getTeamColor(),
                spawn.getWorld(), spawn.getX(), spawn.getY(), spawn.getZ()));
        Bukkit.broadcastMessage("entity from " + this.getKey() + "was spawned");
        return getMob();
    }

    public Integer getHealth() {
        return getMob().getIntHealth();
    }

}