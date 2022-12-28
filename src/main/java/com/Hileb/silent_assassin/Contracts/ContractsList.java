package com.Hileb.silent_assassin.Contracts;

import com.Hileb.silent_assassin.IdlFramework;
import com.Hileb.silent_assassin.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ContractsList {
    public static List<ContractsBase> contracts=new ArrayList<>();
    public static boolean isLegal(ContractsBase c){
        List<WorldServer> worlds= Arrays.stream(FMLCommonHandler.instance().getMinecraftServerInstance().worlds).collect(Collectors.toList());
        for (World world:worlds){
            if (world.getPlayerEntityByName(c.target)!=null)return true;
        }
        return false;
    }
    public static void onRegister(ContractsEvent.Request r){
        if (isLegal(r.contracts)){
            contracts.add(r.contracts);
            IdlFramework.Log("set target %s successfully",r.contracts.target);
        }
    }
    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent d){
        if (d.isCanceled() || d.getEntityLiving().world.isRemote){
            return;
        }
        if (d.getEntityLiving() instanceof EntityPlayer){
            EntityPlayer player=(EntityPlayer)d.getEntityLiving();
            List<ContractsBase> newList=new ArrayList<>();
            for(ContractsBase c:contracts){
                if (!(player.getName().equals(c.target))){
                    newList.add(c);
                }
            }
            contracts=newList;
        }
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void update(ContractsEvent.Apply a){
        IdlFramework.LogWarning("list had been updated");
        contracts=a.contracts;
    }

}
