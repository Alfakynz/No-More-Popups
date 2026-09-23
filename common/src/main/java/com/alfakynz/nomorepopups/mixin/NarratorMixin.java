package com.alfakynz.nomorepopups.mixin;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.client.NarratorStatus;
import net.minecraft.client.GameNarrator;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameNarrator.class)
public class NarratorMixin {

    @Inject(method = "sayNow(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true, remap = false)
    private void noNarrationString(String message, CallbackInfo ci) {
        if (ModConfig.general("narrator")) {
            ci.cancel();
        }
    }

    @Inject(method = "sayNow(Lnet/minecraft/network/chat/Component;)V", at = @At("HEAD"), cancellable = true, remap = false)
    private void noNarrationComponent(Component message, CallbackInfo ci) {
        if (ModConfig.general("narrator")) {
            ci.cancel();
        }
    }

    @Inject(method = "updateNarratorStatus", at = @At("HEAD"), cancellable = true, remap = false)
    private void noStatusAnnounce(NarratorStatus status, CallbackInfo ci) {
        if (ModConfig.general("narrator")) {
            ci.cancel();
        }
    }

    @Inject(method = "isActive", at = @At("HEAD"), cancellable = true, remap = false)
    private void forceInactive(CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.general("narrator")) {
            cir.setReturnValue(false);
        }
    }
}