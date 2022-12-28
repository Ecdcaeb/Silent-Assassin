package com.Hileb.silent_assassin.Contracts;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ContractsInv{
    public NonNullList<ItemStack> itemStacks=NonNullList.create();
    public ContractsInv(){
    }
    public int getSizeInventory(){
        return itemStacks.size();
    }
    public boolean isEmpty(){
        return getSizeInventory()==0;
    }

    public ItemStack getStackInSlot(int index) {
        return itemStacks.get(index);
    }
    public String getName() {
        return String.format("%d",itemStacks.hashCode());
    }
    public void clear() {
        itemStacks=NonNullList.create();
    }
    public ItemStack setStackInSlot(int index,ItemStack stack) {
        return itemStacks.set(index,stack);
    }
    public void add(ItemStack stack){
        itemStacks.add(stack);
    }

}
