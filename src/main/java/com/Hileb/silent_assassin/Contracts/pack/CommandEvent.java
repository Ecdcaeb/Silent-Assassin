package com.Hileb.silent_assassin.Contracts.pack;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.List;

public class CommandEvent extends Event {
    public List<String> string;
    public EntityPlayerMP playerMP;
    public CommandEvent(List<String> s,EntityPlayerMP mpIn){
        string=s;
        playerMP=mpIn;
    }

    @Override
    public boolean isCancelable() {
        return false;
    }
}
