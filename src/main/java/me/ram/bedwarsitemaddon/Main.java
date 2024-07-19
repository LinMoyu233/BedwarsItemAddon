package me.ram.bedwarsitemaddon;

import lombok.Getter;
import me.ram.bedwarsitemaddon.command.CommandTabCompleter;
import me.ram.bedwarsitemaddon.command.Commands;
import me.ram.bedwarsitemaddon.config.Config;
import me.ram.bedwarsitemaddon.config.LocaleConfig;
import me.ram.bedwarsitemaddon.items.*;
import me.ram.bedwarsitemaddon.listener.EventListener;
import me.ram.bedwarsitemaddon.manage.NoFallManage;
import me.ram.bedwarsitemaddon.network.UpdateCheck;
import org.bstats.metrics.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Ram
 * @version 1.7.0
 */
public class Main extends JavaPlugin {

    @Getter
    private static Main instance;
    @Getter
    private NoFallManage noFallManage;
    @Getter
    private LocaleConfig localeConfig;

    public static String getVersion() {
        return "1.7.0";
    }

    @Override
    public FileConfiguration getConfig() {
        FileConfiguration config = Config.getConfig();
        return config == null ? super.getConfig() : config;
    }

    public void onEnable() {
        if (!getDescription().getName().equals("BedwarsItemAddon") || !getDescription().getVersion().equals(getVersion()) || !getDescription().getAuthors().contains("Ram")) {
            try {
                new Exception("Please don't edit plugin.yml!").printStackTrace();
            } catch (Exception ignored) {
            }
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        instance = this;
        noFallManage = new NoFallManage();
        localeConfig = new LocaleConfig();
        getLocaleConfig().loadLocaleConfig();
        Bukkit.getConsoleSender().sendMessage("§f========================================");
        Bukkit.getConsoleSender().sendMessage("§7");
        Bukkit.getConsoleSender().sendMessage("            §bBedwarsItemAddon");
        Bukkit.getConsoleSender().sendMessage("§7");
        Bukkit.getConsoleSender().sendMessage(" §f" + getLocaleConfig().getLanguage("version") + ": §a" + getVersion());
        Bukkit.getConsoleSender().sendMessage("§7");
        Bukkit.getConsoleSender().sendMessage(" §f" + getLocaleConfig().getLanguage("author") + ": §aRam");
        Bukkit.getConsoleSender().sendMessage("§7");
        Bukkit.getConsoleSender().sendMessage(" §f" + getLocaleConfig().getLanguage("modified") + ": §aYukiEnd (Powered by ChatGPT)");
        Bukkit.getConsoleSender().sendMessage("§7");
        Bukkit.getConsoleSender().sendMessage("§f========================================");
        Config.loadConfig();
        Bukkit.getPluginCommand("bedwarsitemaddon").setExecutor(new Commands());
        Bukkit.getPluginCommand("bedwarsitemaddon").setTabCompleter(new CommandTabCompleter());
        if (Bukkit.getPluginManager().getPlugin("BedwarsRel") != null) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (Bukkit.getPluginManager().isPluginEnabled("BedwarsRel")) {
                        cancel();
                        registerEvents();
                    }
                }
            }.runTaskTimer(this, 0L, 0L);
        } else {
            Bukkit.getPluginManager().disablePlugin(this);
        }
        try {
            new Metrics(this).addCustomChart(new Metrics.SimplePie("language", () -> localeConfig.getPluginLocale().getName()));
        } catch (Exception ignored) {
        }
    }

    //注释，防止反作弊绕过，后续会在部分代码中添加临时玩家允许飞行
//    @Override
//    public void onLoad() {
//        try {
//            // 允许飞行，防止使用道具时踢出服务器
//            Path path = Paths.get(getDataFolder().getParentFile().getAbsolutePath()).getParent().resolve("server.properties");
//            boolean reboot = false;
//            List<String> lines = Files.readAllLines(path);
//            if (lines.contains("allow-flight=false")) {
//                lines.remove("allow-flight=false");
//                lines.add("allow-flight=true");
//                reboot = true;
//            }
//            Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);
//            if (reboot) {
//                Bukkit.shutdown();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        if (Config.update_check_enabled) {
            Bukkit.getPluginManager().registerEvents(new UpdateCheck(), this);
        }
        if (Config.items_fireball_enabled) {
            Bukkit.getPluginManager().registerEvents(new FireBall(), this);
        }
        if (Config.items_tnt_enabled) {
            Bukkit.getPluginManager().registerEvents(new LightTNT(), this);
        }
        if (Config.items_bridge_egg_enabled) {
            Bukkit.getPluginManager().registerEvents(new BridgeEgg(), this);
        }
        if (Config.items_parachute_enabled) {
            Bukkit.getPluginManager().registerEvents(new Parachute(), this);
        }
        if (Config.items_tnt_launch_enabled) {
            Bukkit.getPluginManager().registerEvents(new TNTLaunch(), this);
        }
        if (Config.items_magic_milk_enabled) {
            Bukkit.getPluginManager().registerEvents(new MagicMilk(), this);
        }
        if (Config.items_trampoline_enabled) {
            Bukkit.getPluginManager().registerEvents(new Trampoline(), this);
        }
        if (Config.items_compact_tower_enabled) {
            Bukkit.getPluginManager().registerEvents(new CompactTower(), this);
        }
        if (Config.items_walk_platform_enabled) {
            Bukkit.getPluginManager().registerEvents(new WalkPlatform(), this);
        }
        if (Config.items_team_iron_golem_enabled) {
            Bukkit.getPluginManager().registerEvents(new TeamIronGolem(), this);
        }
        if (Config.items_team_silver_fish_enabled) {
            Bukkit.getPluginManager().registerEvents(new TeamSilverFish(), this);
        }
        if (Config.items_explosion_proof_enabled) {
            Bukkit.getPluginManager().registerEvents(new ExplosionProof(), this);
        }
        if (Config.items_ender_pearl_chair_enabled) {
            Bukkit.getPluginManager().registerEvents(new EnderPearlChair(), this);
        }
    }
}
