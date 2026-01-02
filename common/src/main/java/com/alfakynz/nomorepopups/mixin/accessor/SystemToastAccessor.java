package com.alfakynz.nomorepopups.mixin.accessor;

import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(SystemToast.class)
public interface SystemToastAccessor {

    @Accessor("title")
    Component getTitle();

    @Accessor("messageLines")
    List<FormattedCharSequence> getMessageLines();
}