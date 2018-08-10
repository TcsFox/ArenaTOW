package io.github.TcFoxy.ArenaTOW.v1_10_R1;

import io.github.TcFoxy.ArenaTOW.API.MobType;
import io.github.TcFoxy.ArenaTOW.API.TOWEntity;
import net.minecraft.server.v1_10_R1.*;
import org.bukkit.Location;

public abstract class MyEntityGolem extends EntityIronGolem implements TOWEntity {
    Village a;

    public MyEntityGolem(World world) {
        super(world);

        this.fireProof = true;
    }


    @Override
    protected void r() {
        //put pathfinders here.
        this.goalSelector.a(4, new PathfinderGoalGolemFireball(this));
        this.goalSelector.a(5, new PathfinderGoalMeleeAttack(this, 1.3D, false));
        this.targetSelector.a(5, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
    }

    @Override
    public void move(double d0, double d1, double d2) {
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        getAttributeInstance(GenericAttributes.maxHealth).setValue(300.0D);
        getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(7.0D);//sight
    }

    @Override
    public Location getLocation() {
        return new Location(this.world.getWorld(), this.locX, this.locY, this.locZ);
    }

    @Override
    public MobType getMobType() {
        return MobType.TOWER;
    }

}
