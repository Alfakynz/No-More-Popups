package com.alfakynz.nomorepopups.mixin.compat;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToastManager.class)
public class ModdedToastMixin {

    @Inject(method = "addToast", at = @At("HEAD"), cancellable = true, remap = false)
    private void blockFrozenLibToasts(Toast toast, CallbackInfo ci) {

        // FrozenLib
        if (ModConfig.modded("frozenlib") && toast.getClass().getName().startsWith("net.frozenblock.lib")) {
            ci.cancel();
        }
    }
}