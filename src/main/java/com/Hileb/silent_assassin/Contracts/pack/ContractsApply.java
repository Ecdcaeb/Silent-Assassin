package com.Hileb.silent_assassin.Contracts.pack;

import com.Hileb.silent_assassin.Contracts.ContractsList;
import com.Hileb.silent_assassin.network.NetworkHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ContractsApply implements IMessage {
    public String player;
    public void fromBytes(ByteBuf buf){
        player=ByteBufUtils.readUTF8String(buf);
        //player= FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(ByteBufUtils.readUTF8String(buf));
    }
    public void toBytes(ByteBuf buf){
        ByteBufUtils.writeUTF8String(buf,player);
    }
    public EntityPlayerMP getPlayer(){
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(player);
    }
    public static class Handler implements IMessageHandler<ContractsApply, ContractsApply> {
        @Override
        public ContractsApply onMessage(ContractsApply message, MessageContext ctx) {
            ContractsGet g=new ContractsGet();
            g.contracts= ContractsList.contracts;
            NetworkHandler.SendToClient(g,message.getPlayer());
            return message;
        }
    }
}
