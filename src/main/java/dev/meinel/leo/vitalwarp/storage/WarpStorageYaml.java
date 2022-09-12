/*
 * File: WarpStorageYaml.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalwarp.storage;

import dev.meinel.leo.vitalwarp.utils.Chat;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpStorageYaml extends WarpStorage {

    private static final String IOEXCEPTION = "VitalWarp encountered an IOException while executing task";
    private static final String WARP = "warp.";
    private static final String WORLD = ".world";
    private final File warpFile;
    private final FileConfiguration warpConf;

    public WarpStorageYaml() {
        warpFile = new File(main.getDataFolder(), "warp.yml");
        warpConf = YamlConfiguration.loadConfiguration(warpFile);
        save();
    }

    @Override
    public Location loadWarp(@NotNull String arg) {
        if (warpConf.getString(WARP + arg + WORLD) == null) {
            return null;
        }
        World world = Bukkit.getWorld(
                Objects.requireNonNull(warpConf.getString(WARP + arg + WORLD)));
        int x = warpConf.getInt(WARP + arg + ".x");
        int y = warpConf.getInt(WARP + arg + ".y");
        int z = warpConf.getInt(WARP + arg + ".z");
        int yaw = warpConf.getInt(WARP + arg + ".yaw");
        int pitch = warpConf.getInt(WARP + arg + ".pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public Set<String> listWarp() {
        Set<String> warps;
        if (warpConf.getString(WARP) == null) {
            return Collections.emptySet();
        }
        warps = Objects
                .requireNonNull(warpConf.getConfigurationSection(WARP))
                .getKeys(false);
        return warps;
    }

    @Override
    public void saveWarp(@NotNull Player player, @NotNull String arg) {
        Location location = player.getLocation();
        Chat.sendMessage(player, "warp-set");
        clear(arg);
        warpConf.set(WARP + arg + WORLD, location.getWorld().getName());
        warpConf.set(WARP + arg + ".x", (int) location.getX());
        warpConf.set(WARP + arg + ".y", (int) location.getY());
        warpConf.set(WARP + arg + ".z", (int) location.getZ());
        warpConf.set(WARP + arg + ".yaw", (int) location.getYaw());
        warpConf.set(WARP + arg + ".pitch", (int) location.getPitch());
        save();
    }

    @Override
    public void clear(@NotNull String arg) {
        if (warpConf.getConfigurationSection(WARP) == null) {
            return;
        }
        for (String key : Objects
                .requireNonNull(warpConf.getConfigurationSection(WARP))
                .getKeys(false)) {
            if (Objects.equals(key, arg)) {
                warpConf.set(WARP + key, null);
            }
        }
    }

    private void save() {
        try {
            warpConf.save(warpFile);
        } catch (IOException ignored) {
            Bukkit.getLogger().info(IOEXCEPTION);
        }
    }
}
