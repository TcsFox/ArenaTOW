package io.github.TcFoxy.ArenaTOW.v1_13_R1;


import io.github.TcFoxy.ArenaTOW.API.TOWEntity;
import net.minecraft.server.v1_13_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_13_R1.World;

import java.awt.*;

class MyBlueGolem extends MyEntityGolem implements TOWEntity {

	public MyBlueGolem(World world) {
		super(world);
	    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<MyRedZombie>(this, MyRedZombie.class, true));
	}

	@Override
	public Color getTeam() {
		return Color.blue;
	}

}