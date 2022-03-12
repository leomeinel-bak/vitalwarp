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

import com.tamrielnetwork.vitalwarp.storage.mysql.SqlManager;
import com.tamrielnetwork.vitalwarp.utils.Chat;
import com.tamrielnetwork.vitalwarp.utils.sql.Sql;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WarpStorageSql
		extends WarpStorage {

	private static final String SQLEXCEPTION = "VitalWarp encountered an SQLException while executing task";

	public WarpStorageSql() {
		new SqlManager();
	}

	@Override
	public Location loadWarp(@NotNull String arg) {
		World world = null;
		int x = 0;
		int y = 0;
		int z = 0;
		int yaw = 0;
		int pitch = 0;
		try (PreparedStatement selectStatement = SqlManager.getConnection()
		                                                   .prepareStatement("SELECT * FROM ?" + "Warp")) {
			selectStatement.setString(1, Sql.getPrefix());
			try (ResultSet rs = selectStatement.executeQuery()) {
				while (rs.next()) {
					if (!Objects.equals(rs.getString(1), arg) || rs.getString(2) == null) {
						continue;
					}
					world = Bukkit.getWorld(Objects.requireNonNull(rs.getString(2)));
					x = rs.getInt(3);
					y = rs.getInt(4);
					z = rs.getInt(5);
					yaw = rs.getInt(6);
					pitch = rs.getInt(7);
				}
			}
		}
		catch (SQLException ignored) {
			Bukkit.getLogger()
			      .warning(SQLEXCEPTION);
			return null;
		}
		return new Location(world, x, y, z, yaw, pitch);
	}

	@Override
	public Set<String> listWarp() {
		Set<String> warps = new HashSet<>();
		try (PreparedStatement selectStatement = SqlManager.getConnection()
		                                                   .prepareStatement("SELECT * FROM ?" + "Warp")) {
			selectStatement.setString(1, Sql.getPrefix());
			try (ResultSet rs = selectStatement.executeQuery()) {
				while (rs.next()) {
					warps.add(rs.getString(1));
				}
			}
		}
		catch (SQLException ignored) {
			Bukkit.getLogger()
			      .warning(SQLEXCEPTION);
			return Collections.emptySet();
		}
		return warps;
	}

	@Override
	public void saveWarp(@NotNull Player player, @NotNull String arg) {
		Location location = player.getLocation();
		Chat.sendMessage(player, "warp-set");
		clear(arg);
		try (PreparedStatement insertStatement = SqlManager.getConnection()
		                                                   .prepareStatement("INSERT INTO ?"
		                                                                     + "Warp (`Warp`, `World`, `X`, `Y`, `Z`, `Yaw`, `Pitch`) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
			insertStatement.setString(1, Sql.getPrefix());
			insertStatement.setString(2, arg);
			insertStatement.setString(3, location.getWorld()
			                                     .getName());
			insertStatement.setInt(4, (int) location.getX());
			insertStatement.setInt(5, (int) location.getY());
			insertStatement.setInt(6, (int) location.getZ());
			insertStatement.setInt(7, (int) location.getYaw());
			insertStatement.setInt(8, (int) location.getPitch());
			insertStatement.executeUpdate();
		}
		catch (SQLException ignored) {
			Bukkit.getLogger()
			      .warning(SQLEXCEPTION);
		}
	}

	@Override
	public void clear(@NotNull String arg) {
		try (PreparedStatement deleteStatement = SqlManager.getConnection()
		                                                   .prepareStatement("DELETE FROM ?" + "Warp WHERE `Warp`=?")) {
			deleteStatement.setString(1, Sql.getPrefix());
			deleteStatement.setString(2, "'" + arg + "'");
			deleteStatement.executeUpdate();
		}
		catch (SQLException ignored) {
			Bukkit.getLogger()
			      .warning(SQLEXCEPTION);
		}
	}
}
