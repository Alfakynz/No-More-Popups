package com.alfakynz.nomorepopups.mixin;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.client.toast.RecipeToast;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToastManager.class)
public class RecipeToastManagerMixin {
    @Inject(method = "add", at = @At("HEAD"), cancellable = true)
    private void blockRecipeToast(Toast toast, CallbackInfo ci) {
        if (toast instanceof RecipeToast && ModConfig.INSTANCE.disableRecipeToasts) {
            ci.cancel();
        }
    }
}