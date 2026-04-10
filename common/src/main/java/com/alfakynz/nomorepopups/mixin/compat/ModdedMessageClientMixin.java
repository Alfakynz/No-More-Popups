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
    private static boolean No_More_Popups$blockingChunksFadeInMessage = false;

    @Inject(
            method = "sendSystemMessage(Lnet/minecraft/network/chat/Component;)V",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void onSendSystemMessage(Component message, CallbackInfo ci) {
        no_More_Popups$handleMessage(message, ci);
    }

    @Inject(
            method = "sendOverlayMessage(Lnet/minecraft/network/chat/Component;)V",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void onSendOverlayMessage(Component message, CallbackInfo ci) {
        no_More_Popups$handleMessage(message, ci);
    }

    @Unique
    private static void no_More_Popups$handleMessage(Component message, CallbackInfo ci) {
        String plainText = message.getString();

        // Chunks Fade In
        if (ModConfig.modded("chunks_fade_in")
                && plainText.contains("New version of")
                && plainText.contains("Chunks Fade In")) {

            No_More_Popups$blockingChunksFadeInMessage = true;
            ci.cancel();
            return;
        }

        if (No_More_Popups$blockingChunksFadeInMessage) {
            ci.cancel();

            if (plainText.contains("Click to download")) {
                No_More_Popups$blockingChunksFadeInMessage = false;
            }
        }
    }
}