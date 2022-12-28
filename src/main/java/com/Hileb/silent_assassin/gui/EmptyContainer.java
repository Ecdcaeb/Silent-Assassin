package com.Hileb.silent_assassin.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class EmptyContainer extends Container {
    public EmptyContainer(){}
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
