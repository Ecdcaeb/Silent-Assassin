package com.Hileb.silent_assassin.Contracts.pack;

import com.Hileb.silent_assassin.Contracts.ContractsBase;
import com.Hileb.silent_assassin.Contracts.ContractsInv;
import com.Hileb.silent_assassin.Contracts.ContractsList;
import com.Hileb.silent_assassin.network.NetworkHandler;
import com.Hileb.silent_assassin.util.Reference;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class CommandEventHandler {
    @SubscribeEvent
    public static void message___(CommandEvent event){
        List<String> cmd=event.string;
        switch (cmd.get(0)){
            case "target":
                if (cmd.size()==2 && cmd.get(1)!=null){
                    ContractsBase c=new ContractsBase(cmd.get(1),new ContractsInv());
                    ContractsRequest r=new ContractsRequest();
                    r.contractsBase=c;
                    NetworkHandler.SendToServer(r);
                    if (ContractsList.isLegal(c))sendMessage("success",event.playerMP);
                }
                return;
            case "help":
                sendMessage("---help-page(1/1)----",event.playerMP);
                sendMessage("help -to get help",event.playerMP);
                sendMessage("target <name> -set a player as a target",event.playerMP);
                sendMessage("target <name> loot -set a loot connect the target",event.playerMP);
                sendMessage("getTargets all -get all targets to know",event.playerMP);
                sendMessage("getTargets get <name> -get a contract of target",event.playerMP);
                sendMessage("getTargets loot <name> -see the return of the target",event.playerMP);

                return;

            case "getTargets":
                if (cmd.size()==2 && cmd.get(1)!=null){
                    if (cmd.get(1).equals("all")){
//                        ContractsApply a=new ContractsApply();
//                        a.player=player.getName();
//                        NetworkHandler.SendToServer(a);
                        for(ContractsBase c: ContractsList.contracts){
                            sendMessage(c.target,event.playerMP);
                        }
                    }
                }
                return;
            default:
                sendMessage("command error!please enter \"help\" to get help",event.playerMP);
                return;
        }
    }
    public static void sendMessage(String s, EntityPlayerMP playerMP){
        ClientMessage clientMessage=new ClientMessage();
        clientMessage.message=s;
        NetworkHandler.SendToClient(clientMessage,playerMP);
    }
}
