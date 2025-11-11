package com.alfakynz.nomorepopups.mixin;

import com.alfakynz.nomorepopups.config.ModConfig;
import com.mojang.serialization.Lifecycle;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin for removing the experimental world warning when launching an experimental world
 */
@Mixin(PrimaryLevelData.class)
public class ExperimentalWorldMixin {

    @Inject(method = "worldGenSettingsLifecycle", at = @At("HEAD"), cancellable = true)
    private void disableExperimentalWorldWarning(CallbackInfoReturnable<Lifecycle> cir) {
        if (ModConfig.INSTANCE.disableExperimentalWarning) {
            cir.setReturnValue(Lifecycle.stable());
            cir.cancel();
        }
    }
}