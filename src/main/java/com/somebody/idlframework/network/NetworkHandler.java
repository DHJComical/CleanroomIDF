package com.somebody.idlframework.network;

import com.somebody.idlframework.Main;
import com.somebody.idlframework.network.protocols.PacketTest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    public static final ResourceLocation MSG_RESOURCE = new ResourceLocation(Main.MODID, "msg");

    public static final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel(Main.MODID);

    static int id = 0;
    public static void init()
    {
        //C2S
        channel.registerMessage(PacketTest.Handler.class, PacketTest.class, id++, Side.SERVER);
        //just call SendToServer


        //S2C
        //PacketUtil.network.sendTo(new PacketRevenge(cap.isRevengeActive()), (EntityPlayerMP)e.player);
    }

    public static void SendToServer(IMessage packet)
    {
        channel.sendToServer(packet);
    }
}
