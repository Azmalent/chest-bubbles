package azmalent.chestbubbles.platform;

import azmalent.chestbubbles.ChestBubbles;
import azmalent.chestbubbles.network.BubblesMessage;
import azmalent.chestbubbles.platform.services.INetworkHelper;
import net.minecraftforge.network.PacketDistributor;

public class ForgeNetworkHelper implements INetworkHelper {
    @Override
    public void sendBubbleParticles(int numBubbles, double x, double y, double z, double xRange, double zRange) {
        BubblesMessage msg = new BubblesMessage(numBubbles, x, y, z, xRange, zRange);
        ChestBubbles.CHANNEL.send(PacketDistributor.ALL.noArg(), msg);
    }
}
