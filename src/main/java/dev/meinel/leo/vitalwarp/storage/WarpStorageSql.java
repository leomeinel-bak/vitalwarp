/*
 * File: WarpStorageSql.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalwarp.storage;

import dev.meinel.leo.vitalwarp.storage.mysql.SqlManager;
import dev.meinel.leo.vitalwarp.utils.Chat;
import dev.meinel.leo.vitalwarp.utils.sql.Sql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpStorageSql extends WarpStorage {

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
        try (
                PreparedStatement selectStatement = SqlManager
                        .getConnection()
                        .prepareStatement("SELECT * FROM " + Sql.getPrefix() + "Warp")) {
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
        } catch (SQLException ignored) {
            Bukkit.getLogger().warning(SQLEXCEPTION);
            return null;
        }
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public Set<String> listWarp() {
        Set<String> warps = new HashSet<>();
        try (
                PreparedStatement selectStatement = SqlManager
                        .getConnection()
                        .prepareStatement("SELECT * FROM " + Sql.getPrefix() + "Warp")) {
            try (ResultSet rs = selectStatement.executeQuery()) {
                while (rs.next()) {
                    warps.add(rs.getString(1));
                }
            }
        } catch (SQLException ignored) {
            Bukkit.getLogger().warning(SQLEXCEPTION);
            return Collections.emptySet();
        }
        return warps;
    }

    @Override
    public void saveWarp(@NotNull Player player, @NotNull String arg) {
        Location location = player.getLocation();
        Chat.sendMessage(player, "warp-set");
        clear(arg);
        try (
                PreparedStatement insertStatement = SqlManager
                        .getConnection()
                        .prepareStatement(
                                "INSERT INTO " +
                                        Sql.getPrefix() +
                                        "Warp (`Warp`, `World`, `X`, `Y`, `Z`, `Yaw`, `Pitch`) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            insertStatement.setString(1, arg);
            insertStatement.setString(2, location.getWorld().getName());
            insertStatement.setInt(3, (int) location.getX());
            insertStatement.setInt(4, (int) location.getY());
            insertStatement.setInt(5, (int) location.getZ());
            insertStatement.setInt(6, (int) location.getYaw());
            insertStatement.setInt(7, (int) location.getPitch());
            insertStatement.executeUpdate();
        } catch (SQLException ignored) {
            Bukkit.getLogger().warning(SQLEXCEPTION);
        }
    }

    @Override
    public void clear(@NotNull String arg) {
        try (
                PreparedStatement deleteStatement = SqlManager
                        .getConnection()
                        .prepareStatement(
                                "DELETE FROM " + Sql.getPrefix() + "Warp WHERE `Warp`=?")) {
            deleteStatement.setString(1, arg);
            deleteStatement.executeUpdate();
        } catch (SQLException ignored) {
            Bukkit.getLogger().warning(SQLEXCEPTION);
        }
    }
}
