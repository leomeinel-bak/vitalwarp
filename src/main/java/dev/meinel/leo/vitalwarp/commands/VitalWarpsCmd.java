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
 * along with this program. If not, see https://github.com/LeoMeinel/VitalWarp/blob/main/LICENSE
 */

package dev.meinel.leo.vitalwarp.commands;

import dev.meinel.leo.vitalwarp.VitalWarp;
import dev.meinel.leo.vitalwarp.utils.Chat;
import dev.meinel.leo.vitalwarp.utils.commands.Cmd;
import dev.meinel.leo.vitalwarp.utils.commands.CmdSpec;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class VitalWarpsCmd
		implements CommandExecutor {

	private final VitalWarp main = JavaPlugin.getPlugin(VitalWarp.class);

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
	                         @NotNull String[] args) {
		if (Cmd.isArgsLengthNotEqualTo(sender, args, 0)) {
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
		Set<String> warpsSet = main.getWarpStorage()
		                           .listWarp();
		if (warpsSet.isEmpty()) {
			Chat.sendMessage(sender, "no-warps");
			return;
		}
		List<String> warpsList = main.getWarpStorage()
		                             .listWarp()
		                             .stream()
		                             .toList();
		for (String warp : warpsList) {
			if (warp.equals(warpsList.get(0))) {
				warpsBuilder.append("&b")
				            .append(warp);
				continue;
			}
			warpsBuilder.append("&f, &b")
			            .append(warp);
		}
		String warps = warpsBuilder.toString();
		sender.sendMessage(Chat.replaceColors(warps));
	}
}
