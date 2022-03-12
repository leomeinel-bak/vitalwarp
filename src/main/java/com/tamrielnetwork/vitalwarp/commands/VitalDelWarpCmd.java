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

package com.tamrielnetwork.vitalwarp.commands;

import com.tamrielnetwork.vitalwarp.VitalWarp;
import com.tamrielnetwork.vitalwarp.utils.Chat;
import com.tamrielnetwork.vitalwarp.utils.commands.Cmd;
import com.tamrielnetwork.vitalwarp.utils.commands.CmdSpec;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VitalDelWarpCmd
		implements TabExecutor {

	private final VitalWarp main = JavaPlugin.getPlugin(VitalWarp.class);

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
	                         @NotNull String[] args) {
		if (Cmd.isArgsLengthNotEqualTo(sender, args, 1)) {
			return false;
		}
		delWarp(sender, args[0]);
		return true;
	}

	private void delWarp(@NotNull CommandSender sender, String arg) {
		if (CmdSpec.isInvalidCmd(sender, "vitalwarp.delwarp")) {
			return;
		}
		main.getWarpStorage()
		    .clear(arg.toLowerCase());
		Chat.sendMessage(sender, "warp-removed");
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
	                                            @NotNull String alias, @NotNull String[] args) {
		if (main.getWarpStorage()
		        .listWarp()
		        .isEmpty()) {
			return null;
		}
		return new ArrayList<>(main.getWarpStorage()
		                           .listWarp());
	}
}
