package com.Hileb.silent_assassin.events;

import com.Hileb.silent_assassin.IdlFramework;
import com.Hileb.silent_assassin.gui.ModGuiElementLoader;
import com.Hileb.silent_assassin.util.Reference;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModEventsListener {
//    @SubscribeEvent
//    public static void onPlayerSleep(PlayerSleepInBedEvent event){
//        event.getEntityPlayer().openGui(IdlFramework.instance, ModGuiElementLoader.GUI_SENT,event.getEntityPlayer().world,0,0,0);
//    }
    @SubscribeEvent
    public static void onPlayerOpenGui(PlayerInteractEvent.RightClickBlock event){
        event.getEntityPlayer().openGui(IdlFramework.instance, ModGuiElementLoader.GUI_SENT,event.getEntityPlayer().world,0,0,0);
    }
}
