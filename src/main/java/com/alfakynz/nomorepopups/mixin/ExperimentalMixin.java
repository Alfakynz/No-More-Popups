package com.alfakynz.nomorepopups.mixin;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.server.integrated.IntegratedServerLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(IntegratedServerLoader.class)
public class ExperimentalMixin {
    
    @ModifyVariable(method = "tryLoad", at = @At("HEAD"))
    private static boolean removeExperimentalWarning(boolean original) {
        return ModConfig.INSTANCE.disableExperimentalWarning || original;
    }
}