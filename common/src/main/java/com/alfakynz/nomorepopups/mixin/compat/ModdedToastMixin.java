package com.alfakynz.nomorepopups.mixin.compat;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToastComponent.class)
public class ModdedToastMixin {

    @Inject(method = "addToast", at = @At("HEAD"), cancellable = true)
    private void blockFrozenLibToasts(Toast toast, CallbackInfo ci) {

        // FrozenLib
        if (ModConfig.modded("frozenlib") && toast.getClass().getName().startsWith("net.frozenblock.lib")) {
            ci.cancel();
        }
    }
}