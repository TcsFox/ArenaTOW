package io.github.TcFoxy.ArenaTOW.v1_10_R1;

import io.github.TcFoxy.ArenaTOW.API.TOWEntity;
import net.minecraft.server.v1_10_R1.*;

public class MyPathfinderGoalMelee extends PathfinderGoalMeleeAttack {

    boolean e;
    EntityCreature attacker;

    public MyPathfinderGoalMelee(EntityCreature arg0, double speed) {
        super(arg0, speed, false);
        this.e = false;
        this.attacker = arg0;
    }

    @Override
    public boolean b() {
        final EntityLiving goalTarget = this.b.getGoalTarget();
        if (goalTarget == null) {
            return false;
        }
        if (!goalTarget.isAlive()) {
            return false;
        }
        if (!this.e) {
            return !this.b.getNavigation().n();
        }
        if(goalTarget instanceof TOWEntity && ((TOWEntity) this).isSameTeam(goalTarget)) {
            return  false;
        }
        return this.b.f(new BlockPosition(goalTarget)) && (!(goalTarget instanceof EntityHuman) || (!((EntityHuman) goalTarget).isSpectator() && !((EntityHuman) goalTarget).z()));
    }


}
