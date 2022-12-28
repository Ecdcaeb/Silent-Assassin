package com.Hileb.silent_assassin.Contracts.pack;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;
import java.util.List;

public class CommandMessagePack implements IMessage {
    public List<String> message;
    public String player;
    public void fromBytes(ByteBuf buf){
        int i=ByteBufUtils.readVarShort(buf);
        message=new ArrayList<>();
        for(int o=0;o<i;o++){
            message.add(ByteBufUtils.readUTF8String(buf));
        }
        player=ByteBufUtils.readUTF8String(buf);
    }
    public void toBytes(ByteBuf buf){
        ByteBufUtils.writeVarShort(buf,message.size());
        for(int o=0;o<message.size();o++){
            ByteBufUtils.writeUTF8String(buf,message.get(o));
        }
        ByteBufUtils.writeUTF8String(buf,player);
    }
    public EntityPlayerMP getPlayer(){
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(player);
    }
    public static class Handler implements IMessageHandler<CommandMessagePack, CommandMessagePack> {
        public CommandMessagePack onMessage(CommandMessagePack message, MessageContext ctx){
            MinecraftForge.EVENT_BUS.post(new CommandEvent(message.message,message.getPlayer()));
            return message;
        }
    }
}
