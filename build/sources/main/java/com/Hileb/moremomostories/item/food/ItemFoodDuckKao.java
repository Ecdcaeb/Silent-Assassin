package com.Hileb.moremomostories.item.food;

import com.Hileb.moremomostories.entity.EntityVan;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFoodDuckKao extends ItemFoodBase{
    public ItemFoodDuckKao(String name){
        super(name,6,6,true);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        super.onFoodEaten(stack, worldIn, player);
        if(!worldIn.isRemote){
            EntityVan van=new EntityVan(worldIn);
            worldIn.spawnEntity(van);
            van.setPosition(player.posX,player.posY,player.posZ);
        }
    }
}
