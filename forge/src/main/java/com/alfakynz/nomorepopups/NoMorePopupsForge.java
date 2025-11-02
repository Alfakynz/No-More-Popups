package com.alfakynz.nomorepopups;

import com.alfakynz.nomorepopups.config.ModConfigScreen;
import com.alfakynz.nomorepopups.platform.Services;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkConstants;

@Mod(Constants.MOD_ID)
public class NoMorePopupsForge {

    public NoMorePopupsForge() {
        CommonClass.init();

        if (Services.PLATFORM.isModLoaded("cloth_config")) {
            ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
            DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ModConfigScreen::registerModsPage);
        }
    }
}