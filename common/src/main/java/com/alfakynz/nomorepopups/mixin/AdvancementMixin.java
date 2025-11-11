package com.alfakynz.nomorepopups.mixin;

import com.alfakynz.nomorepopups.config.ModConfig;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for removing advancement messages.
 */
@Mixin(ClientPacketListener.class)
public class AdvancementMixin {

    @Inject(method = "handleSystemChat", at = @At("HEAD"), cancellable = true)
    private void onGameMessage(ClientboundSystemChatPacket packet, CallbackInfo ci) {
        if (ModConfig.INSTANCE.disableAdvancementsMessages) {
            Component message = packet.content();

            ComponentContents content = message.getContents();
            if (content instanceof TranslatableContents translatableContent) {
                String key = translatableContent.getKey();
                if (key.startsWith("chat.type.advancement")) {
                    ci.cancel();
                }
            }
        }
    }
}