package com.alfakynz.nomorepopups.config;

import com.alfakynz.nomorepopups.NoMorePopups;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("config/no-more-popups.json");

    public boolean disableAdvancementsMessages = false;
    public boolean disableAdvancementToasts = true;
    public boolean disableExperimentalWarning = true;
    public boolean disableMultiplayerWarning = true;
    public boolean disableRecipeToasts = true;
    public boolean disableResourcePackWarnings = true;
    public boolean disableTutorialToasts = true;

    public static ModConfig INSTANCE = new ModConfig();

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (Reader reader = new FileReader(CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(reader, ModConfig.class);
            } catch (IOException e) {
                NoMorePopups.LOGGER.error("Failed to load NMP config, using default values.", e);
                INSTANCE = new ModConfig();
            }
        } else {
            save();
        }
    }

    public static void save() {
        try (Writer writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(INSTANCE, writer);
        } catch (IOException e) {
            NoMorePopups.LOGGER.error("Failed to save NMP config.", e);
        }
    }
}