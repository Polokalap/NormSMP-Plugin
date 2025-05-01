package mel.Polokalap.normSMPPlugin.listeners;

import mel.Polokalap.normSMPPlugin.NormSMPPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        NormSMPPlugin plugin = NormSMPPlugin.getInstance();

        Player player = event.getPlayer();

        if (plugin.getConfig().get("data." + player.getUniqueId() + ".points") == null) {

            plugin.getConfig().set("data." + player.getUniqueId() + ".points", plugin.getConfig().getInt("settings.starter_point"));
            plugin.getConfig().set("data." + player.getUniqueId() + ".strength", 0);
            plugin.getConfig().set("data." + player.getUniqueId() + ".hero", 0);
            plugin.getConfig().set("data." + player.getUniqueId() + ".protection", 0);
            plugin.getConfig().set("data." + player.getUniqueId() + ".speed", 0);
            plugin.saveConfig();

        }

    }

}
