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

public class ContractsPack implements IMessage {
    public ContractsBase contractsBase;
    public void fromBytes(ByteBuf buf){
        String name=ByteBufUtils.readUTF8String(buf);
        ContractsInv inv=new ContractsInv();
        int size=ByteBufUtils.readVarShort(buf);
        for(int i=0;i<size;i++){
            inv.add(ByteBufUtils.readItemStack(buf));
        }
        contractsBase=new ContractsBase(name,inv);
    }
    public void toBytes(ByteBuf buf){
        ByteBufUtils.writeUTF8String(buf,contractsBase.target);
        ByteBufUtils.writeVarShort(buf,contractsBase.money.getSizeInventory());
        for(int i=0;i<contractsBase.money.getSizeInventory();i++){
            ByteBufUtils.writeItemStack(buf,contractsBase.money.getStackInSlot(i).copy());
        }
    }
    public static class Handler implements IMessageHandler<ContractsPack,ContractsPack> {
        public ContractsPack onMessage(ContractsPack message, MessageContext ctx){
            MinecraftForge.EVENT_BUS.post(new ContractsEvent.Send(message.contractsBase));
            return message;
        }
    }
}
