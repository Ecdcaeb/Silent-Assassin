package com.Hileb.silent_assassin.Contracts.pack;

import com.Hileb.silent_assassin.Contracts.ContractsEvent;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientMessage implements IMessage {
    public String message;
    public void fromBytes(ByteBuf buf){
        message= ByteBufUtils.readUTF8String(buf);
    }
    public void toBytes(ByteBuf buf){
        ByteBufUtils.writeUTF8String(buf,message);
    }
    public static class Handler implements IMessageHandler<ClientMessage, ClientMessage> {
        public ClientMessage onMessage(ClientMessage message, MessageContext ctx){
            MinecraftForge.EVENT_BUS.post(new ContractsEvent.Message(message.message));
            return message;
        }
    }
}
