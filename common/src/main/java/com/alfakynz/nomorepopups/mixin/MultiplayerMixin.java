package com.alfakynz.nomorepopups.mixin;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for removing multiplayer warning.
 */
@Mixin(Options.class)
public class MultiplayerMixin {

    @Inject(method = "load", at = @At("TAIL"))
    private void onLoad(CallbackInfo ci) {
        Options options = (Options) (Object) this;
        if (ModConfig.general("multiplayer_warning")) {
            options.skipMultiplayerWarning = true;
        }
    }

    @Inject(method = "save", at = @At("TAIL"))
    private void onWrite(CallbackInfo ci) {
        Options options = (Options) (Object) this;
        if (ModConfig.general("multiplayer_warning")) {
            options.skipMultiplayerWarning = true;
        }
    }
}