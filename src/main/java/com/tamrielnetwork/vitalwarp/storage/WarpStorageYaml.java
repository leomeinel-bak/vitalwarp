/*
 * VitalWarp is a Spigot Plugin that gives players the ability to set warps and teleport to them.
 * Copyright © 2022 Leopold Meinel & contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see https://github.com/TamrielNetwork/VitalWarp/blob/main/LICENSE
 */

package com.tamrielnetwork.vitalwarp.storage;

import com.tamrielnetwork.vitalwarp.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class WarpStorageYaml
		extends WarpStorage {

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
		World world = Bukkit.getWorld(Objects.requireNonNull(warpConf.getString(WARP + arg + WORLD)));
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
		warps = Objects.requireNonNull(warpConf.getConfigurationSection(WARP))
		               .getKeys(false);
		return warps;
	}

	@Override
	public void saveWarp(@NotNull Player player, @NotNull String arg) {
		Location location = player.getLocation();
		Chat.sendMessage(player, "warp-set");
		clear(arg);
		warpConf.set(WARP + arg + WORLD, location.getWorld()
		                                         .getName());
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
		for (String key : Objects.requireNonNull(warpConf.getConfigurationSection(WARP))
		                         .getKeys(false)) {
			if (Objects.equals(key, arg)) {
				warpConf.set(WARP + key, null);
			}
		}
		save();
	}

	public void save() {
		try {
			warpConf.save(warpFile);
		}
		catch (IOException ignored) {
			Bukkit.getLogger()
			      .info(IOEXCEPTION);
		}
	}
}