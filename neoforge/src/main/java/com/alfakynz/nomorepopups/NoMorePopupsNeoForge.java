package com.alfakynz.nomorepopups;

import com.alfakynz.nomorepopups.config.ModConfigScreen;
import com.alfakynz.nomorepopups.platform.Services;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;

@Mod(Constants.MOD_ID)
public class NoMorePopupsNeoForge {

    public NoMorePopupsNeoForge() {
        NoMorePopups.init();

        if (Services.PLATFORM.isModLoaded("cloth_config")) {
            if (FMLLoader.getCurrent().getDist() == Dist.CLIENT) {
                ModConfigScreen.registerModsPage();
            }
        }
    }
}