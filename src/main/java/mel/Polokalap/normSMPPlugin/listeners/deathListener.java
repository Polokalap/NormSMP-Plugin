package mel.Polokalap.normSMPPlugin.listeners;

import mel.Polokalap.normSMPPlugin.NormSMPPlugin;
import mel.Polokalap.normSMPPlugin.utils.chat;
import mel.Polokalap.normSMPPlugin.utils.pointItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class deathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        NormSMPPlugin plugin = NormSMPPlugin.getInstance();

        Player player = event.getEntity();

        if (player.getKiller() instanceof Player killer) {

            int strength = plugin.getConfig().getInt("data." + player.getUniqueId() + ".strength", 0);
            int hero = plugin.getConfig().getInt("data." + player.getUniqueId() + ".hero", 0);
            int protection = plugin.getConfig().getInt("data." + player.getUniqueId() + ".protection", 0);
            int speed = plugin.getConfig().getInt("data." + player.getUniqueId() + ".speed", 0);

            int total = strength + hero + protection + speed;

            if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") + total > 0) {

                plugin.getConfig().set("data." + player.getUniqueId() + ".strength", 0);
                plugin.getConfig().set("data." + player.getUniqueId() + ".hero", 0);
                plugin.getConfig().set("data." + player.getUniqueId() + ".protection", 0);
                plugin.getConfig().set("data." + player.getUniqueId() + ".speed", 0);

                player.setMaxHealth(20.0d);

                int currentPoints = plugin.getConfig().getInt("data." + player.getUniqueId() + ".points");
                plugin.getConfig().set("data." + player.getUniqueId() + ".points", currentPoints + total);

                plugin.saveConfig();

                pointItem.pointItem(killer, player.getName());

                plugin.getConfig().set("data." + player.getUniqueId() + ".points",
                        plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") - 1);

                player.sendMessage(chat.replace("player.minus")
                        .replaceAll("&c", String.valueOf(plugin.getConfig().getInt("data." + player.getUniqueId() + ".points")))
                );

                plugin.saveConfig();
            }


        }

    }

}
