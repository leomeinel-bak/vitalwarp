/*
 * File: WarpStorage.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalwarp.storage;

import dev.meinel.leo.vitalwarp.VitalWarp;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public abstract class WarpStorage {

  protected final VitalWarp main = JavaPlugin.getPlugin(VitalWarp.class);

  public abstract Location loadWarp(@NotNull String arg);

  public abstract Set<String> listWarp();

  public abstract void saveWarp(@NotNull Player player, @NotNull String arg);

  public abstract void clear(@NotNull String arg);
}
