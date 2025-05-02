package mel.Polokalap.normSMPPlugin;

import mel.Polokalap.normSMPPlugin.commands.*;
import mel.Polokalap.normSMPPlugin.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public final class NormSMPPlugin extends JavaPlugin {

    public static mel.Polokalap.normSMPPlugin.NormSMPPlugin instance;

    @Override
    public void onEnable() {

        new BukkitRunnable() {
            @Override
            public void run() {

                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {

                    if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".strength") > 0) onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 50, getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".strength") - 1, true, false));

                    if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".hero") > 0) {

                        if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".hero") == 1)  onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 50, 2, true, false));
                        if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".hero") == 2)  onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 50, 5, true, false));
                        if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".hero") == 3)  onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 50, 9, true, false));
                        if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".hero") > 3)  onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 50, getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".hero") + 14, true, false));

                    }

                    if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".protection") > 0) {

                        if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".protection") == 1) {

                            onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 50, 0, true, false));
                            onlinePlayers.setMaxHealth(20.0d);

                        }

                        if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".protection") >= 2) {

                            onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 50, 0, true, false));
                            onlinePlayers.setMaxHealth(30.0d);

                        }
                        if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".protection") == 3) {

                            onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 50, 1, true, false));

                        }

                        if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".protection") > 3)  onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 50, getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".protection") - 1, true, false));

                    }

                    if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".protection") == 0) {

                        onlinePlayers.setMaxHealth(20.0d);

                    }

                    if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".speed") > 0) {

                        if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".speed") == 1)  onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50, 0, true, false));
                        if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".speed") == 2)  onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50, 1, true, false));
                        if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".speed") == 3)  onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 50, 1, true, false));
                        if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".speed") > 3)  onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50, getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".speed") - 1, true, false));
                        if (getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".speed") > 3)  onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 50, getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".speed") / 2, true, false));

                    }

                    if (getConfig().getBoolean("settings.action_bar.enable")) {

                        onlinePlayers.sendActionBar(getConfig().getString("settings.action_bar.content")
                                .replaceAll("&c1", String.valueOf(getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".points")))
                                .replaceAll("&c2", String.valueOf(getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".strength")))
                                .replaceAll("&c3", String.valueOf(getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".hero")))
                                .replaceAll("&c4", String.valueOf(getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".protection")))
                                .replaceAll("&c5", String.valueOf(getConfig().getInt("data." + onlinePlayers.getUniqueId() + ".speed")))
                        );

                    }

                }

            }
        }.runTaskTimer(this, 0L, 40L);

        getConfig().options().copyDefaults(true);
        saveConfig();

        register_commands_and_listeners();

        getLogger().info(getConfig().getString("console.startup"));

        instance = this;

    }

    private void register_commands_and_listeners() {

        // Command: getCommand("command").setExecutor(new commandClass());
        getCommand("stats").setExecutor(new statsCommand());
        getCommand("normsmp").setExecutor(new normSmpCommand());

        // Listener: getServer().getPluginManager().registerEvents(new eventClass(), this);
        getServer().getPluginManager().registerEvents(new actionListener(), this);
        getServer().getPluginManager().registerEvents(new joinListener(), this);
        getServer().getPluginManager().registerEvents(new deathListener(), this);

        getLogger().info(getConfig().getString("console.register"));

    }

    @Override
    public void onDisable() {

        getLogger().info(getConfig().getString("console.disable"));

    }

    public static mel.Polokalap.normSMPPlugin.NormSMPPlugin getInstance() {

        return instance;

    }

}
