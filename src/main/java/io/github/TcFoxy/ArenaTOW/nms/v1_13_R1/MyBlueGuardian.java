package io.github.TcFoxy.ArenaTOW.nms.v1_13_R1;


import mc.alk.arena.BattleArena;
import mc.alk.arena.objects.ArenaPlayer;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import io.github.TcFoxy.ArenaTOW.Utils;
import io.github.TcFoxy.ArenaTOW.nms.v1_13_R1.interfaces.NMSConstants;
import net.minecraft.server.v1_13_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_13_R1.World;
import net.minecraft.server.v1_13_R1.DamageSource;

public class MyBlueGuardian extends MyEntityGuardian{


	public MyBlueGuardian(World paramWorld)
	{
		super(paramWorld);
		this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<MyRedZombie>(this, MyRedZombie.class, true));
	}
	
	public boolean damageEntity(DamageSource damagesource, float f){
		if(damagesource.getEntity() != null){
			if(damagesource.getEntity().getClass().getName() == NMSConstants.entityPlayer){
				Player p = (Player) damagesource.getEntity().getBukkitEntity();
				ArenaPlayer ap = BattleArena.toArenaPlayer(p);
				String arenateam = ap.getTeam().getDisplayName();
				if(arenateam.equals(Utils.toSimpleColor(Color.BLUE))){
					return false;
				}else{ super.damageEntity(damagesource, f);}
			} else {super.damageEntity(damagesource, f);}
		}super.damageEntity(damagesource, f);
		return true;
	}
}