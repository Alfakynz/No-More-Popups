package com.alfakynz.nomorepopups.mixin;

import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.alfakynz.nomorepopups.config.ModConfig;

@Mixin(GameOptions.class)
public class MultiplayerMixin {

    @Inject(method = "load", at = @At("TAIL"))
    private void onLoad(CallbackInfo ci) {
        GameOptions options = (GameOptions) (Object) this;
        options.skipMultiplayerWarning = ModConfig.INSTANCE.disableMultiplayerWarning;
    }
}