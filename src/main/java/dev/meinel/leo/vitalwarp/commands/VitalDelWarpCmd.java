/*
 * File: VitalDelWarpCmd.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2023 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalwarp.commands;

import dev.meinel.leo.vitalwarp.VitalWarp;
import dev.meinel.leo.vitalwarp.utils.Chat;
import dev.meinel.leo.vitalwarp.utils.commands.Cmd;
import dev.meinel.leo.vitalwarp.utils.commands.CmdSpec;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VitalDelWarpCmd implements TabExecutor {

    private final VitalWarp main = JavaPlugin.getPlugin(VitalWarp.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
            @NotNull String label, @NotNull String[] args) {
        if (!Cmd.isArgsLengthEqualTo(sender, args, 1)) {
            return false;
        }
        delWarp(sender, args[0]);
        return true;
    }

    private void delWarp(@NotNull CommandSender sender, String arg) {
        if (CmdSpec.isInvalidCmd(sender, "vitalwarp.delwarp")) {
            return;
        }
        main.getWarpStorage().clear(arg.toLowerCase());
        Chat.sendMessage(sender, "warp-removed");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,
            @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (main.getWarpStorage().listWarp().isEmpty()) {
            return null;
        }
        return new ArrayList<>(main.getWarpStorage().listWarp());
    }
}
