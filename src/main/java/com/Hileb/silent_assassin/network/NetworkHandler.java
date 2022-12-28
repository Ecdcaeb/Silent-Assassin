package com.Hileb.silent_assassin.network;

import com.Hileb.silent_assassin.Contracts.pack.*;
import com.Hileb.silent_assassin.IdlFramework;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    public static final ResourceLocation MSG_RESOURCE = new ResourceLocation(IdlFramework.MODID, "msg");

    public static final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel(IdlFramework.MODID);

    static int id = 0;
    public static void init()
    {
        //C2S
        //just call SendToServer
        channel.registerMessage(ContractsPack.Handler.class, ContractsPack.class, id++, Side.CLIENT);
        channel.registerMessage(ContractsRequest.Handler.class, ContractsRequest.class, id++, Side.SERVER);
        channel.registerMessage(ContractsApply.Handler.class, ContractsApply.class, id++, Side.SERVER);
        channel.registerMessage(ContractsGet.Handler.class, ContractsGet.class, id++, Side.CLIENT);

        channel.registerMessage(ClientMessage.Handler.class, ClientMessage.class, id++, Side.CLIENT);

        channel.registerMessage(CommandMessagePack.Handler.class, CommandMessagePack.class, id++, Side.SERVER);


        //S2C
        //PacketUtil.network.sendTo(new PacketRevenge(cap.isRevengeActive()), (EntityPlayerMP)e.player);
    }

    public static void SendToServer(IMessage packet)
    {
        channel.sendToServer(packet);
    }
    public static void SendToClient(IMessage packet, EntityPlayerMP playerMP)
    {
        channel.sendTo(packet,playerMP);
    }
}
