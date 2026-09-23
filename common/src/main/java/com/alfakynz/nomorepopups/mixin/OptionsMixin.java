package com.alfakynz.nomorepopups.mixin;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for removing the multiplayer warning popup and the narrator onboarding prompt.
 */
@Mixin(Options.class)
public abstract class OptionsMixin {

    @Shadow public boolean skipMultiplayerWarning;
    @Shadow public boolean onboardAccessibility;

    @Inject(method = "load*", at = @At("TAIL"), remap = false)
    private void nomorepopups$onLoad(CallbackInfo ci) {
        if (ModConfig.general("multiplayer_warning")) {
            this.skipMultiplayerWarning = true;
        }
        if (ModConfig.general("narrator")) {
            this.onboardAccessibility = false;
        }
    }

    @Inject(method = "save", at = @At("TAIL"), remap = false)
    private void nomorepopups$onSave(CallbackInfo ci) {
        if (ModConfig.general("multiplayer_warning")) {
            this.skipMultiplayerWarning = true;
        }
    }
}