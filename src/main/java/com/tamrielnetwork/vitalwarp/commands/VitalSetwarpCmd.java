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
import com.tamrielnetwork.vitalwarp.utils.commands.Cmd;
import com.tamrielnetwork.vitalwarp.utils.commands.CmdSpec;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class VitalSetwarpCmd
		implements CommandExecutor {

	private final VitalWarp main = JavaPlugin.getPlugin(VitalWarp.class);

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
	                         @NotNull String[] args) {
		if (Cmd.isArgsLengthNotEqualTo(sender, args, 1)) {
			return false;
		}
		setWarp(sender, args[0]);
		return true;
	}

	private void setWarp(@NotNull CommandSender sender, String arg) {
		if (CmdSpec.isInvalidCmd(sender, "vitalwarp.setwarp", arg)) {
			return;
		}
		Player senderPlayer = (Player) sender;
		main.getWarpStorage()
		    .saveWarp(senderPlayer, arg.toLowerCase());
	}
}
