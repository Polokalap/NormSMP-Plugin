package mel.Polokalap.normSMPPlugin.commands;

import mel.Polokalap.normSMPPlugin.NormSMPPlugin;
import mel.Polokalap.normSMPPlugin.utils.chat;
import mel.Polokalap.normSMPPlugin.utils.pointItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class normSmpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {

        if (sender.hasPermission("normsmp.admin")) {

            if (args.length != 0) {

                switch (args[0]) {

                    case "teszt":
                        sender.sendMessage(chat.replace("player.teszt"));
                        break;

                    case "getitem":

                        if (args.length < 2) {

                            sender.sendMessage(chat.replace("player.error"));

                        } else {

                            if (sender instanceof Player player) {

                                switch (args[1]) {

                                    case "point":
                                        pointItem.pointItem(player, NormSMPPlugin.getInstance().getConfig().getString("settings.console.name"));
                                        break;

                                    default:
                                        sender.sendMessage(chat.replace("player.error"));
                                        break;

                                }

                            } else {

                                sender.sendMessage(chat.replace("console.player"));

                            }

                        }
                        break;

                    case "points":
                        sender.sendMessage(chat.replace("player.default_point_changed"));
                        break;

                    case "reload":
                        NormSMPPlugin.getInstance().reloadConfig();
                        break;

                    default:
                        sender.sendMessage(chat.replace("player.missingperm"));
                        break;

                }

            } else {

                sender.sendMessage(chat.replace("player.error"));

            }

        } else {

            sender.sendMessage(chat.replace("player.missingperm"));

        }

        return true;
    }

}
