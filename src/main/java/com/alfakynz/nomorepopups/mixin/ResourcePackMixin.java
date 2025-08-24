package com.alfakynz.nomorepopups.mixin;

import com.alfakynz.nomorepopups.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.resource.ResourcePackCompatibility;

@Mixin(ResourcePackCompatibility.class)
public class ResourcePackMixin {

    @Inject(method = "isCompatible", at = @At("HEAD"), cancellable = true)
    private void alwaysCompatible(CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.INSTANCE.disableResourcePackWarnings) {
            cir.setReturnValue(true);
        }
    }
}