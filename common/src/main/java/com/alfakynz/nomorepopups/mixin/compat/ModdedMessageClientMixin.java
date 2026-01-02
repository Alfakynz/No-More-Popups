package com.alfakynz.nomorepopups.mixin.compat;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for removing modded messages. (Client-side)
 */
@Mixin(LocalPlayer.class)
public class ModdedMessageClientMixin {

    @Unique
    private static boolean No_More_Popups$blockingChunksFadeInUpdate = false;

    @Inject(
            method = "displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onDisplayClientMessage(Component message, boolean overlay, CallbackInfo ci) {

        String plainText = message.getString();

        if (plainText.contains("New version of")
                && plainText.contains("Chunks Fade In")
                && ModConfig.modded("chunks_fade_in")) {

            No_More_Popups$blockingChunksFadeInUpdate = true;
            ci.cancel();
            return;
        }

        if (No_More_Popups$blockingChunksFadeInUpdate) {
            ci.cancel();

            if (plainText.contains("Click to download")) {
                No_More_Popups$blockingChunksFadeInUpdate = false;
            }
        }
    }
}