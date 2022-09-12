/*
 * File: SqlManager.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalwarp.storage.mysql;

import dev.meinel.leo.vitalwarp.VitalWarp;
import dev.meinel.leo.vitalwarp.utils.sql.Sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class SqlManager {

  private static final String SQLEXCEPTION = "VitalWarp encountered an SQLException while executing task";
  private static Connection connection;
  private final VitalWarp main = JavaPlugin.getPlugin(VitalWarp.class);
  private final int port;
  private final String host;
  private final String database;
  private final String username;
  private final String password;

  public SqlManager() {
    this.host = main.getConfig().getString("mysql.host");
    this.port = main.getConfig().getInt("mysql.port");
    this.database = main.getConfig().getString("mysql.database");
    this.username = main.getConfig().getString("mysql.username");
    this.password = main.getConfig().getString("mysql.password");
    enableConnection();
    try (
        PreparedStatement statementSpawnTable = SqlManager
            .getConnection()
            .prepareStatement(
                "CREATE TABLE IF NOT EXISTS " +
                    Sql.getPrefix() +
                    "Warp (`Warp` TEXT, `World` TEXT, `X` INT, `Y` INT, `Z` INT, `Yaw` INT, `Pitch` INT)")) {
      statementSpawnTable.executeUpdate();
    } catch (SQLException ignored) {
      Bukkit.getLogger().warning(SQLEXCEPTION);
    }
  }

  public static Connection getConnection() {
    return connection;
  }

  private static void setConnection(@NotNull Connection connection) {
    SqlManager.connection = connection;
  }

  private void enableConnection() {
    try {
      if (getConnection() != null && !getConnection().isClosed()) {
        return;
      }
      setConnection(
          DriverManager.getConnection(
              "jdbc:mysql://" + host + ":" + port + "/" + database,
              username,
              password));
      main.getLogger().info("Connected successfully with the database!");
    } catch (SQLException ignored) {
      Bukkit.getLogger().warning(SQLEXCEPTION);
    }
  }
}
