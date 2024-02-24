package azmalent.chestbubbles.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.Random;

public class BubblesMessage {
    private final int numBubbles;
    private final double x;
    private final double y;
    private final double z;
    private final double xRange;
    private final double zRange;

    public BubblesMessage(int numBubbles, double x, double y, double z, double xRange, double zRange) {
        this.numBubbles = numBubbles;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xRange = xRange;
        this.zRange = zRange;
    }

    public void onReceive(NetworkEvent.Context context) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null) {
            Random random = level.getRandom();

            for (int i = 0; i < this.numBubbles; i++) {
                double xPos = x + (random.nextDouble() - 0.5) * xRange;
                double yPos = y + random.nextDouble();
                double zPos = z + (random.nextDouble() - 0.5) * zRange;
                double speed = 0.2 + random.nextDouble() * 0.6;

                level.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, xPos, yPos, zPos, 0, speed, 0);
            }
        }
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.numBubbles);
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeDouble(this.xRange);
        buf.writeDouble(this.zRange);
    }

    public static BubblesMessage decode(FriendlyByteBuf buf) {
        int numBubbles = buf.readInt();
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        double xRange = buf.readDouble();
        double zRange = buf.readDouble();

        return new BubblesMessage(numBubbles, x, y, z, xRange, zRange);
    }
}
