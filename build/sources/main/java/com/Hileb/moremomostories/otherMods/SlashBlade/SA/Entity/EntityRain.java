package com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityRain extends EntityThrowable {
    public final AxisAlignedBB attackAABB=new AxisAlignedBB(new BlockPos(0,0,0),new BlockPos(0.2,0.2,0.2));
    protected EntityPlayer thrower;
    protected ItemStack blade;
    public EntityRain(World par1World,ItemStack blade,EntityPlayer thrower) {
        super(par1World);
        this.blade = blade;
    }
    public EntityRain(World worldIn)
    {
        super(worldIn);
    }

    public EntityRain(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityRain(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit!=thrower){
            result.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(thrower),1);
        }
    }
}
