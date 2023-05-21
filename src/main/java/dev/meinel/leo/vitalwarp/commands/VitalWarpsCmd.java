/*
 * File: VitalWarpsCmd.java
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
import java.util.List;
import java.util.Set;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class VitalWarpsCmd implements CommandExecutor {

    private final VitalWarp main = JavaPlugin.getPlugin(VitalWarp.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
            @NotNull String label, @NotNull String[] args) {
        if (!Cmd.isArgsLengthEqualTo(sender, args, 0)) {
            return false;
        }
        doWarps(sender);
        return true;
    }

    private void doWarps(@NotNull CommandSender sender) {
        if (CmdSpec.isInvalidCmd(sender, "vitalwarp.list")) {
            return;
        }
        StringBuilder warpsBuilder = new StringBuilder();
        Set<String> warpsSet = main.getWarpStorage().listWarp();
        if (warpsSet.isEmpty()) {
            Chat.sendMessage(sender, "no-warps");
            return;
        }
        List<String> warpsList = main.getWarpStorage().listWarp().stream().toList();
        for (String warp : warpsList) {
            if (warp.equals(warpsList.get(0))) {
                warpsBuilder.append("&b").append(warp);
                continue;
            }
            warpsBuilder.append("&f, &b").append(warp);
        }
        String warps = warpsBuilder.toString();
        sender.sendMessage(Chat.replaceColors(warps));
    }
}
