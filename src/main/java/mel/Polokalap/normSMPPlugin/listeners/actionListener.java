package mel.Polokalap.normSMPPlugin.listeners;

import mel.Polokalap.normSMPPlugin.NormSMPPlugin;
import mel.Polokalap.normSMPPlugin.utils.GUI;
import mel.Polokalap.normSMPPlugin.utils.chat;
import mel.Polokalap.normSMPPlugin.utils.pointItem;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class actionListener implements Listener {

    @EventHandler
    public void onAction(InventoryClickEvent event) {

        NormSMPPlugin plugin = NormSMPPlugin.getInstance();

        if (!(event.getWhoClicked() instanceof Player)) return;

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta() || !clicked.getItemMeta().hasDisplayName()) return;

        Player player = (Player) event.getWhoClicked();

        String menuName = plugin.getConfig().getString("settings.stats.menu.name");
        if (menuName != null && event.getView().getTitle().startsWith(menuName)) {
            event.setCancelled(true);

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("settings.stats.menu.withdraw.name"))) {

                if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") >= 1) {

                    pointItem.pointItem(player, player.getDisplayName());
                    plugin.getConfig().set("data." + player.getUniqueId() + ".points", plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") - 1);
                    plugin.saveConfig();

                    GUI.openStats(player, plugin);

                } else {

                    player.sendMessage(chat.replace("player.not_enough_points"));

                }

            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("settings.stats.menu.strength.name"))) {

                GUI.strength_menu(player, plugin);

            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("settings.stats.menu.emerald.name"))) {

                GUI.hero_menu(player, plugin);

            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("settings.stats.menu.iron.name"))) {

                GUI.protection_menu(player, plugin);

            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("settings.stats.menu.sugar.name"))) {

                GUI.speed_menu(player, plugin);

            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("settings.stats.menu.back"))) {

                GUI.openStats(player, plugin);

            }

            String displayName = clicked.getItemMeta().getDisplayName();
            ItemMeta clickedMeta = clicked.getItemMeta();
            PersistentDataContainer container = clickedMeta.getPersistentDataContainer();
            NamespacedKey strengthAddKey = new NamespacedKey(plugin, "strength_add");

            if (displayName.equals(plugin.getConfig().getString("settings.stats.menu.add")) && container.has(strengthAddKey, PersistentDataType.STRING)) {

                String uuid = player.getUniqueId().toString();

                int strength = plugin.getConfig().getInt("data." + uuid + ".strength");
                int points = plugin.getConfig().getInt("data." + uuid + ".points");

                plugin.getConfig().set("data." + uuid + ".strength", strength + 1);
                plugin.getConfig().set("data." + uuid + ".points", points - 1);

                plugin.saveConfig();

                player.sendMessage(chat.replace("player.minus")
                        .replaceAll("&c", Objects.requireNonNull(NormSMPPlugin.getInstance().getConfig().getString("data." + player.getUniqueId() + ".points")))
                );

                GUI.strength_menu(player, plugin);

            }

            NamespacedKey strengthRemoveKey = new NamespacedKey(plugin, "strength_remove");

            if (displayName.equals(plugin.getConfig().getString("settings.stats.menu.remove")) && container.has(strengthRemoveKey, PersistentDataType.STRING)) {

                String uuid = player.getUniqueId().toString();

                if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") == 8) {

                    int strength = plugin.getConfig().getInt("data." + uuid + ".strength");
                    plugin.getConfig().set("data." + uuid + ".strength", strength - 1);
                    pointItem.pointItem(player, player.getName());

                } else {

                    int strength = plugin.getConfig().getInt("data." + uuid + ".strength");
                    int points = plugin.getConfig().getInt("data." + uuid + ".points");
                    plugin.getConfig().set("data." + uuid + ".strength", strength - 1);
                    plugin.getConfig().set("data." + uuid + ".points", points + 1);

                }

                plugin.saveConfig();

                player.sendMessage(chat.replace("player.plus")
                        .replaceAll("&c", NormSMPPlugin.getInstance().getConfig().getString("data." + player.getUniqueId() + ".points"))
                );

                GUI.strength_menu(player, plugin);

            }

            NamespacedKey heroAddKey = new NamespacedKey(plugin, "hero_add");

            if (displayName.equals(plugin.getConfig().getString("settings.stats.menu.add")) && container.has(heroAddKey, PersistentDataType.STRING)) {

                String uuid = player.getUniqueId().toString();

                int hero = plugin.getConfig().getInt("data." + uuid + ".hero");
                int points = plugin.getConfig().getInt("data." + uuid + ".points");

                plugin.getConfig().set("data." + uuid + ".hero", hero + 1);
                plugin.getConfig().set("data." + uuid + ".points", points - 1);

                plugin.saveConfig();

                player.sendMessage(chat.replace("player.minus")
                        .replaceAll("&c", Objects.requireNonNull(NormSMPPlugin.getInstance().getConfig().getString("data." + player.getUniqueId() + ".points")))
                );

                GUI.hero_menu(player, plugin);

            }

            NamespacedKey heroRemoveKey = new NamespacedKey(plugin, "hero_remove");

            if (displayName.equals(plugin.getConfig().getString("settings.stats.menu.remove")) && container.has(heroRemoveKey, PersistentDataType.STRING)) {

                String uuid = player.getUniqueId().toString();

                if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") == 8) {

                    int hero = plugin.getConfig().getInt("data." + uuid + ".hero");
                    plugin.getConfig().set("data." + uuid + ".hero", hero - 1);
                    pointItem.pointItem(player, player.getName());

                } else {

                    int hero = plugin.getConfig().getInt("data." + uuid + ".hero");
                    int points = plugin.getConfig().getInt("data." + uuid + ".points");
                    plugin.getConfig().set("data." + uuid + ".hero", hero - 1);
                    plugin.getConfig().set("data." + uuid + ".points", points + 1);

                }

                plugin.saveConfig();

                player.sendMessage(chat.replace("player.plus")
                        .replaceAll("&c", NormSMPPlugin.getInstance().getConfig().getString("data." + player.getUniqueId() + ".points"))
                );

                GUI.hero_menu(player, plugin);

            }

            NamespacedKey protectionAddKey = new NamespacedKey(plugin, "protection_add");

            if (displayName.equals(plugin.getConfig().getString("settings.stats.menu.add")) && container.has(protectionAddKey, PersistentDataType.STRING)) {

                String uuid = player.getUniqueId().toString();

                int protection = plugin.getConfig().getInt("data." + uuid + ".protection");
                int points = plugin.getConfig().getInt("data." + uuid + ".points");

                plugin.getConfig().set("data." + uuid + ".protection", protection + 1);
                plugin.getConfig().set("data." + uuid + ".points", points - 1);

                plugin.saveConfig();

                player.sendMessage(chat.replace("player.minus")
                        .replaceAll("&c", Objects.requireNonNull(NormSMPPlugin.getInstance().getConfig().getString("data." + player.getUniqueId() + ".points")))
                );

                GUI.protection_menu(player, plugin);

            }

            NamespacedKey protectionRemoveKey = new NamespacedKey(plugin, "protection_remove");

            if (displayName.equals(plugin.getConfig().getString("settings.stats.menu.remove")) && container.has(protectionRemoveKey, PersistentDataType.STRING)) {

                String uuid = player.getUniqueId().toString();

                if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") == 8) {

                    int protection = plugin.getConfig().getInt("data." + uuid + ".protection");
                    plugin.getConfig().set("data." + uuid + ".protection", protection - 1);
                    pointItem.pointItem(player, player.getName());

                } else {

                    int protection = plugin.getConfig().getInt("data." + uuid + ".protection");
                    int points = plugin.getConfig().getInt("data." + uuid + ".points");
                    plugin.getConfig().set("data." + uuid + ".protection", protection - 1);
                    plugin.getConfig().set("data." + uuid + ".points", points + 1);

                }

                plugin.saveConfig();

                player.sendMessage(chat.replace("player.plus")
                        .replaceAll("&c", NormSMPPlugin.getInstance().getConfig().getString("data." + player.getUniqueId() + ".points"))
                );

                GUI.protection_menu(player, plugin);

            }

            NamespacedKey speedAddKey = new NamespacedKey(plugin, "speed_add");

            if (displayName.equals(plugin.getConfig().getString("settings.stats.menu.add")) && container.has(speedAddKey, PersistentDataType.STRING)) {

                String uuid = player.getUniqueId().toString();

                int speed = plugin.getConfig().getInt("data." + uuid + ".speed");
                int points = plugin.getConfig().getInt("data." + uuid + ".points");

                plugin.getConfig().set("data." + uuid + ".speed", speed + 1);
                plugin.getConfig().set("data." + uuid + ".points", points - 1);

                plugin.saveConfig();

                player.sendMessage(chat.replace("player.minus")
                        .replaceAll("&c", Objects.requireNonNull(NormSMPPlugin.getInstance().getConfig().getString("data." + player.getUniqueId() + ".points")))
                );

                GUI.speed_menu(player, plugin);

            }

            NamespacedKey speedRemoveKey = new NamespacedKey(plugin, "speed_remove");

            if (displayName.equals(plugin.getConfig().getString("settings.stats.menu.remove")) && container.has(speedRemoveKey, PersistentDataType.STRING)) {

                String uuid = player.getUniqueId().toString();

                if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") == 8) {

                    int speed = plugin.getConfig().getInt("data." + uuid + ".speed");
                    plugin.getConfig().set("data." + uuid + ".speed", speed - 1);
                    pointItem.pointItem(player, player.getName());

                } else {

                    int speed = plugin.getConfig().getInt("data." + uuid + ".speed");
                    int points = plugin.getConfig().getInt("data." + uuid + ".points");
                    plugin.getConfig().set("data." + uuid + ".speed", speed - 1);
                    plugin.getConfig().set("data." + uuid + ".points", points + 1);

                }

                plugin.saveConfig();

                player.sendMessage(chat.replace("player.plus")
                        .replaceAll("&c", NormSMPPlugin.getInstance().getConfig().getString("data." + player.getUniqueId() + ".points"))
                );

                GUI.speed_menu(player, plugin);

            }

        }

    }

    @EventHandler
    public void onItemAction(PlayerInteractEvent event) {

        Action action = event.getAction();
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        NormSMPPlugin plugin = NormSMPPlugin.getInstance();

        if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
                && item != null
                && item.hasItemMeta()
                && item.getItemMeta().hasDisplayName()
                && item.getItemMeta().getDisplayName().equals(
                plugin.getConfig().getString("settings.point.name"))) {

            if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") != plugin.getConfig().getInt("settings.max_points")) {

                plugin.getConfig().set("data." + player.getUniqueId() + ".points", plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") + 1);
                plugin.saveConfig();

                player.sendMessage(chat.replace("player.plus")
                        .replaceAll("&c", plugin.getConfig().getString("data." + player.getUniqueId() + ".points"))
                );

                item.setAmount(item.getAmount() - 1);

            } else {

                player.sendMessage(chat.replace("player.max_points"));

            }

        }

    }

}
