package com.Hileb.silent_assassin.command;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ModCommands {
    public static void give(EntityPlayer player,ItemStack stack){
        //if (player.getHeldItemMainhand().isEmpty()){
            if (!player.addItemStackToInventory(stack))
            {
                player.dropItem(stack, false);
            }
        //}
        //telse player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
