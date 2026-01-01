package com.alfakynz.nomorepopups.config;

import com.alfakynz.nomorepopups.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ModConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("config/no-more-popups.json");

    public Map<String, Boolean> general = new HashMap<>();
    public Map<String, Boolean> modded = new HashMap<>();

    public static ModConfig INSTANCE = createDefault();

    private static ModConfig createDefault() {
        ModConfig config = new ModConfig();

        config.general.put("advancements.messages", false);
        config.general.put("advancements.toasts", true);
        config.general.put("recipes.toasts", true);
        config.general.put("tutorials", true);
        config.general.put("system_toasts", false);
        config.general.put("experimental_warning", true);
        config.general.put("multiplayer_warning", true);
        config.general.put("resource_pack_warnings", true);

        config.modded.put("messages", false);
        config.modded.put("nether_weather", false);

        return config;
    }

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (Reader reader = new FileReader(CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(reader, ModConfig.class);
            } catch (IOException e) {
                Constants.LOG.error("Failed to load NMP config, using default values.", e);
                INSTANCE = createDefault();
            }
        } else {
            INSTANCE = createDefault();
            save();
        }
    }

    public static void save() {
        try {
            // Create the config dir if needed
            File parent = CONFIG_FILE.getParentFile();
            if (parent != null && !parent.exists()) {
                if (!parent.mkdirs()) {
                    Constants.LOG.warn("Failed to create config directory: {}", parent.getAbsolutePath());
                }
            }

            try (Writer writer = new FileWriter(CONFIG_FILE)) {
                GSON.toJson(INSTANCE, writer);
            }
        } catch (IOException e) {
            Constants.LOG.error("Failed to save NMP config.", e);
        }
    }

    public static boolean general(String key) {
        return INSTANCE.general.getOrDefault(key, true);
    }

    public static boolean modded(String key) {
        return INSTANCE.modded.getOrDefault(key, true);
    }
}