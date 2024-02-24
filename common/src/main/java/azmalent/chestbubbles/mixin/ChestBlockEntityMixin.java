package azmalent.chestbubbles.mixin;

import azmalent.chestbubbles.BubbleHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ChestBlockEntity.class)
public class ChestBlockEntityMixin {
    @Inject(method = "playSound", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void makeBubbleParticles(Level level, BlockPos pos, BlockState state, SoundEvent event, CallbackInfo ci, ChestType $$4, double $$5, double $$6, double $$7) {
        if (event != SoundEvents.CHEST_OPEN) {
            return;
        }

        if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
            if ($$4 == ChestType.RIGHT) {
                BubbleHandler.spawnDoubleChestBubbles(level, state, $$5, $$6, $$7);
            } else {
                BubbleHandler.spawnSingleChestBubbles(level, $$5, $$6, $$7);
            }
        }
    }
}
