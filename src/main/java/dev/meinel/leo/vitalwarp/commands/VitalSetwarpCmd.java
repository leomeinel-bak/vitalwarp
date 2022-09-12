/*
 * File: VitalSetwarpCmd.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalwarp.commands;

import dev.meinel.leo.vitalwarp.VitalWarp;
import dev.meinel.leo.vitalwarp.utils.commands.Cmd;
import dev.meinel.leo.vitalwarp.utils.commands.CmdSpec;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class VitalSetwarpCmd implements CommandExecutor {

  private final VitalWarp main = JavaPlugin.getPlugin(VitalWarp.class);

  @Override
  public boolean onCommand(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      @NotNull String[] args) {
    if (Cmd.isArgsLengthNotEqualTo(sender, args, 1)) {
      return false;
    }
    setWarp(sender, args[0]);
    return true;
  }

  private void setWarp(@NotNull CommandSender sender, String arg) {
    if (CmdSpec.isInvalidCmd(sender, "vitalwarp.setwarp")) {
      return;
    }
    Player senderPlayer = (Player) sender;
    main.getWarpStorage().saveWarp(senderPlayer, arg.toLowerCase());
  }
}
