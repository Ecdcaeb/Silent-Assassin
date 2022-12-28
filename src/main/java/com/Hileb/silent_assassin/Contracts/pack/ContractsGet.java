package com.Hileb.silent_assassin.Contracts.pack;

import com.Hileb.silent_assassin.Contracts.ContractsBase;
import com.Hileb.silent_assassin.Contracts.ContractsEvent;
import com.Hileb.silent_assassin.Contracts.ContractsInv;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;
import java.util.List;

public class ContractsGet implements IMessage {
    public List<ContractsBase> contracts;
    public void fromBytes(ByteBuf buf){
        int size=ByteBufUtils.readVarShort(buf);
        contracts=new ArrayList<>();
        for(int i=0;i<size;i++){
            fromBytesC(buf,i);
        }
    }
    public void toBytes(ByteBuf buf){
        ByteBufUtils.writeVarShort(buf,contracts.size());
        for(int i=0;i<contracts.size();i++){
            toBytesC(buf,i);
        }
    }
    public void fromBytesC(ByteBuf buf,int index){
        String name=ByteBufUtils.readUTF8String(buf);
        ContractsInv inv=new ContractsInv();
        int size=ByteBufUtils.readVarShort(buf);
        for(int i=0;i<size;i++){
            inv.add(ByteBufUtils.readItemStack(buf));
        }
        contracts.set(index,new ContractsBase(name,inv));
    }
    public void toBytesC(ByteBuf buf,int index){
        ByteBufUtils.writeUTF8String(buf,contracts.get(index).target);
        ByteBufUtils.writeVarShort(buf,contracts.get(index).money.getSizeInventory());
        for(int i=0;i<contracts.get(index).money.getSizeInventory();i++){
            ByteBufUtils.writeItemStack(buf,contracts.get(index).money.getStackInSlot(i).copy());
        }
    }
    public static class Handler implements IMessageHandler<ContractsGet, ContractsGet> {
        public ContractsGet onMessage(ContractsGet message, MessageContext ctx){
            MinecraftForge.EVENT_BUS.post(new ContractsEvent.Apply(message.contracts));
            return message;
        }
    }
}
