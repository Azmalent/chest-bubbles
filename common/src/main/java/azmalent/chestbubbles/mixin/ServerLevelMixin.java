package azmalent.chestbubbles.mixin;

import azmalent.chestbubbles.BubbleHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
    @SuppressWarnings("ConstantConditions")
    @Inject(method = "playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V", at = @At("TAIL"))
    private void makeBubbleParticles(@Nullable Player player, double x, double y, double z, SoundEvent event, SoundSource source, float volume, float pitch, CallbackInfo ci) {
        if (event != SoundEvents.ENDER_CHEST_OPEN) {
            return;
        }

        ServerLevel self = (ServerLevel) (Object) this;
        BubbleHandler.spawnSingleChestBubbles(self, x, y, z);
    }
}
