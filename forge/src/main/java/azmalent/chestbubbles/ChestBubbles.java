package azmalent.chestbubbles;

import azmalent.chestbubbles.network.BubblesMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod(ChestBubbles.MODID)
public class ChestBubbles {
    public static final String MODID = "chestbubbles";

    public static final SimpleChannel CHANNEL;

    static {
        ResourceLocation channelName = new ResourceLocation(MODID, "channel");
        String protocol = "1";
        CHANNEL = NetworkRegistry.newSimpleChannel(channelName, () -> protocol, protocol::equals, protocol::equals);

        CHANNEL.registerMessage(0, BubblesMessage.class, BubblesMessage::encode, BubblesMessage::decode, (msg, sup) -> {
            NetworkEvent.Context context = sup.get();
            if (context.getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                context.enqueueWork(() -> msg.onReceive(context));
            }

            context.setPacketHandled(true);
        });
    }
}
