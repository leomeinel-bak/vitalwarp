/*
 * File: Cmd.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalwarp.utils.commands;

import dev.meinel.leo.vitalwarp.utils.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Cmd {

    private Cmd() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isArgsLengthNotEqualTo(
            @NotNull CommandSender sender,
            @NotNull String[] args,
            int length) {
        if (args.length != length) {
            Chat.sendMessage(sender, "cmd");
            return true;
        }
        return false;
    }

    public static boolean isNotPermitted(
            @NotNull CommandSender sender,
            @NotNull String perm) {
        if (!sender.hasPermission(perm)) {
            Chat.sendMessage(sender, "no-perms");
            return true;
        }
        return false;
    }

    public static boolean isInvalidSender(@NotNull CommandSender sender) {
        if (!(sender instanceof Player)) {
            Chat.sendMessage(sender, "player-only");
            return true;
        }
        return false;
    }

    public static boolean isInvalidPlayer(Player player) {
        return !player.isOnline();
    }
}
