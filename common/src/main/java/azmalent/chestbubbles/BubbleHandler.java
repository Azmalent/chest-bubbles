package azmalent.chestbubbles;

import azmalent.chestbubbles.platform.Services;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;

import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public final class BubbleHandler {
    public static void spawnSingleChestBubbles(Level level, double x, double y, double z) {
        spawnBubbles((ServerLevel) level, 12, x, y, z, 0.5, 0.5);
    }

    public static void spawnDoubleChestBubbles(Level level, BlockState state, double x, double y, double z) {
        double xRange = 0.5;
        double zRange = 0.5;

        if (state.getValue(ChestBlock.FACING).getAxis() == Direction.Axis.X) {
            zRange += 1;
        } else {
            xRange += 1;
        }

        spawnBubbles((ServerLevel) level, 24, x, y, z, xRange, zRange);
    }

    private static void spawnBubbles(ServerLevel level, int numParticles, double x, double y, double z, double xRange, double zRange) {
        Services.NETWORK.sendBubbleParticles(numParticles, x, y, z, xRange, zRange);

        Random random = level.getRandom();
        level.playSound(null, x, y, z, SoundEvents.BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundSource.BLOCKS, 0.6F + random.nextFloat() * 0.4F, 0.9F + random.nextFloat() * 0.15F);
    }
}
