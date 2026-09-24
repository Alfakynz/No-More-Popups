package com.alfakynz.nomorepopups.mixin;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.client.gui.screens.AccessibilityOnboardingScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for removing the narrator onboarding prompt.
 */
@Mixin(AccessibilityOnboardingScreen.class)
public abstract class AccessibilityMixin {

    @Shadow
    public abstract void onClose();

    @Inject(method = "init", at = @At("HEAD"), cancellable = true, remap = false)
    private void nomorepopups$skipOnboarding(CallbackInfo ci) {
        if (ModConfig.general("narrator")) {
            ci.cancel();
            this.onClose();
        }
    }
}