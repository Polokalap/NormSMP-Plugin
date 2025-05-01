package mel.Polokalap.normSMPPlugin.commands;

import mel.Polokalap.normSMPPlugin.NormSMPPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import mel.Polokalap.normSMPPlugin.utils.*;

public class statsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {

        NormSMPPlugin plugin = NormSMPPlugin.getInstance();

        if (sender instanceof Player player) {

            plugin.reloadConfig();

            GUI.openStats(player, plugin);

        } else {

            sender.sendMessage(plugin.getConfig().getString("console.player"));

        }

        return true;
    }

}
