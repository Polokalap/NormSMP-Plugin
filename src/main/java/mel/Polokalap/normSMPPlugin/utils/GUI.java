package mel.Polokalap.normSMPPlugin.utils;

import mel.Polokalap.normSMPPlugin.NormSMPPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class GUI {

    public static void openStats(Player player, NormSMPPlugin plugin) {

        Inventory stats_menu = Bukkit.createInventory(null, 27, plugin.getConfig().getString("settings.stats.menu.name"));

        ItemStack points = new ItemStack(Material.NETHER_STAR);
        ItemMeta pointsMeta = points.getItemMeta();

        pointsMeta.setDisplayName(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.point.name"));

        List<String> pointLore = new ArrayList<>();
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.point.lore1").replaceAll("&c", plugin.getConfig().getString("data." + player.getUniqueId() + ".points")));
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.point.lore2").replaceAll("&c", plugin.getConfig().getString("data." + player.getUniqueId() + ".points")));
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.point.lore3").replaceAll("&c", plugin.getConfig().getString("data." + player.getUniqueId() + ".points")));

        pointsMeta.setLore(pointLore);

        points.setItemMeta(pointsMeta);

        stats_menu.setItem(4, points);

        ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(" ");
        empty.setItemMeta(emptyMeta);

        ItemStack strength = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta strengthMeta = strength.getItemMeta();
        strengthMeta.setDisplayName(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.strength.name"));
        strength.setItemMeta(strengthMeta);

        ItemStack sugar = new ItemStack(Material.SUGAR);
        ItemMeta sugarMeta = sugar.getItemMeta();
        sugarMeta.setDisplayName(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.sugar.name"));
        sugar.setItemMeta(sugarMeta);

        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        ItemMeta ironMeta = iron.getItemMeta();
        ironMeta.setDisplayName(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.iron.name"));
        iron.setItemMeta(ironMeta);

        ItemStack emerald = new ItemStack(Material.EMERALD);
        ItemMeta emeraldMeta = emerald.getItemMeta();
        emeraldMeta.setDisplayName(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.emerald.name"));
        emerald.setItemMeta(emeraldMeta);

        ItemStack withdraw = new ItemStack(Material.STRUCTURE_VOID);
        ItemMeta withdrawMeta = withdraw.getItemMeta();
        withdrawMeta.setDisplayName(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.withdraw.name"));
        withdraw.setItemMeta(withdrawMeta);

        stats_menu.setItem(10, strength);
        stats_menu.setItem(12, emerald);
        stats_menu.setItem(14, iron);
        stats_menu.setItem(16, sugar);
        stats_menu.setItem(22, withdraw);

        for (int i = 0; i < 27; i++) {

            if (stats_menu.getItem(i) == null) {

                stats_menu.setItem(i, empty);

            }

        }

        player.openInventory(stats_menu);

    }

    public static void strength_menu(Player player, NormSMPPlugin plugin) {

        Inventory menu = Bukkit.createInventory(null, 27, plugin.getConfig().getString("settings.stats.menu.name") + " - " + plugin.getConfig().getString("settings.stats.menu.strength.name"));

        ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(" ");
        empty.setItemMeta(emptyMeta);

        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(plugin.getConfig().getString("settings.stats.menu.back"));
        back.setItemMeta(backMeta);

        ItemStack points = new ItemStack(Material.NETHER_STAR);
        ItemMeta pointsMeta = points.getItemMeta();

        pointsMeta.setDisplayName(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.name")
                .replaceAll("&c", NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.strength.name"))
        );

        if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".strength") < plugin.getConfig().getInt("settings.max_levels") && plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") > 0) {

            ItemStack add = new ItemStack(Material.EMERALD_BLOCK);
            ItemMeta addMeta = add.getItemMeta();
            NamespacedKey key = new NamespacedKey(plugin, "strength_add");
            addMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
            addMeta.setDisplayName(plugin.getConfig().getString("settings.stats.menu.add"));
            add.setItemMeta(addMeta);

            menu.setItem(11, add);

        }

        if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".strength") > 0) {

            ItemStack remove = new ItemStack(Material.REDSTONE_BLOCK);
            ItemMeta removeMeta = remove.getItemMeta();
            NamespacedKey key = new NamespacedKey(plugin, "strength_remove");
            removeMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
            removeMeta.setDisplayName(plugin.getConfig().getString("settings.stats.menu.remove"));
            remove.setItemMeta(removeMeta);

            menu.setItem(15, remove);

        }

        List<String> pointLore = new ArrayList<>();
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.lore1").replaceAll("&c", String.valueOf(plugin.getConfig().getInt("data." + player.getUniqueId() + ".strength"))));
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.lore2").replaceAll("&c", String.valueOf(plugin.getConfig().getInt("data." + player.getUniqueId() + ".strength"))));
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.lore3").replaceAll("&c", String.valueOf(plugin.getConfig().getInt("data." + player.getUniqueId() + ".strength"))));

        pointsMeta.setLore(pointLore);

        points.setItemMeta(pointsMeta);

        menu.setItem(13, points);
        menu.setItem(22, back);

        for (int i = 0; i < 27; i++) {

            if (menu.getItem(i) == null) {

                menu.setItem(i, empty);

            }

        }

        player.openInventory(menu);

    }

    public static void hero_menu(Player player, NormSMPPlugin plugin) {

        Inventory menu = Bukkit.createInventory(null, 27, plugin.getConfig().getString("settings.stats.menu.name") + " - " + plugin.getConfig().getString("settings.stats.menu.emerald.name"));

        ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(" ");
        empty.setItemMeta(emptyMeta);

        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(plugin.getConfig().getString("settings.stats.menu.back"));
        back.setItemMeta(backMeta);

        ItemStack points = new ItemStack(Material.NETHER_STAR);
        ItemMeta pointsMeta = points.getItemMeta();

        pointsMeta.setDisplayName(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.name")
                .replaceAll("&c", plugin.getConfig().getString("settings.stats.menu.emerald.name"))
        );

        if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".hero") < plugin.getConfig().getInt("settings.max_levels") && plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") > 0) {

            ItemStack add = new ItemStack(Material.EMERALD_BLOCK);
            ItemMeta addMeta = add.getItemMeta();
            NamespacedKey key = new NamespacedKey(plugin, "hero_add");
            addMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
            addMeta.setDisplayName(plugin.getConfig().getString("settings.stats.menu.add"));
            add.setItemMeta(addMeta);

            menu.setItem(11, add);

        }

        if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".hero") > 0) {

            ItemStack remove = new ItemStack(Material.REDSTONE_BLOCK);
            ItemMeta removeMeta = remove.getItemMeta();
            NamespacedKey key = new NamespacedKey(plugin, "hero_remove");
            removeMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
            removeMeta.setDisplayName(plugin.getConfig().getString("settings.stats.menu.remove"));
            remove.setItemMeta(removeMeta);

            menu.setItem(15, remove);

        }

        List<String> pointLore = new ArrayList<>();
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.lore1").replaceAll("&c", String.valueOf(plugin.getConfig().getInt("data." + player.getUniqueId() + ".hero"))));
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.lore2").replaceAll("&c", String.valueOf(plugin.getConfig().getInt("data." + player.getUniqueId() + ".hero"))));
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.lore3").replaceAll("&c", String.valueOf(plugin.getConfig().getInt("data." + player.getUniqueId() + ".hero"))));

        pointsMeta.setLore(pointLore);

        points.setItemMeta(pointsMeta);

        menu.setItem(13, points);
        menu.setItem(22, back);

        for (int i = 0; i < 27; i++) {

            if (menu.getItem(i) == null) {

                menu.setItem(i, empty);

            }

        }

        player.openInventory(menu);

    }

    public static void protection_menu(Player player, NormSMPPlugin plugin) {

        Inventory menu = Bukkit.createInventory(null, 27, plugin.getConfig().getString("settings.stats.menu.name") + " - " + plugin.getConfig().getString("settings.stats.menu.iron.name"));

        ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(" ");
        empty.setItemMeta(emptyMeta);

        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(plugin.getConfig().getString("settings.stats.menu.back"));
        back.setItemMeta(backMeta);

        ItemStack points = new ItemStack(Material.NETHER_STAR);
        ItemMeta pointsMeta = points.getItemMeta();

        pointsMeta.setDisplayName(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.name")
                .replaceAll("&c", plugin.getConfig().getString("settings.stats.menu.iron.name"))
        );

        if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".protection") < plugin.getConfig().getInt("settings.max_levels") && plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") > 0) {

            ItemStack add = new ItemStack(Material.EMERALD_BLOCK);
            ItemMeta addMeta = add.getItemMeta();
            NamespacedKey key = new NamespacedKey(plugin, "protection_add");
            addMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
            addMeta.setDisplayName(plugin.getConfig().getString("settings.stats.menu.add"));
            add.setItemMeta(addMeta);

            menu.setItem(11, add);

        }

        if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".protection") > 0) {

            ItemStack remove = new ItemStack(Material.REDSTONE_BLOCK);
            ItemMeta removeMeta = remove.getItemMeta();
            NamespacedKey key = new NamespacedKey(plugin, "protection_remove");
            removeMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
            removeMeta.setDisplayName(plugin.getConfig().getString("settings.stats.menu.remove"));
            remove.setItemMeta(removeMeta);

            menu.setItem(15, remove);

        }

        List<String> pointLore = new ArrayList<>();
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.lore1").replaceAll("&c", String.valueOf(plugin.getConfig().getInt("data." + player.getUniqueId() + ".protection"))));
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.lore2").replaceAll("&c", String.valueOf(plugin.getConfig().getInt("data." + player.getUniqueId() + ".protection"))));
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.lore3").replaceAll("&c", String.valueOf(plugin.getConfig().getInt("data." + player.getUniqueId() + ".protection"))));

        pointsMeta.setLore(pointLore);

        points.setItemMeta(pointsMeta);

        menu.setItem(13, points);
        menu.setItem(22, back);

        for (int i = 0; i < 27; i++) {

            if (menu.getItem(i) == null) {

                menu.setItem(i, empty);

            }

        }

        player.openInventory(menu);

    }

    public static void speed_menu(Player player, NormSMPPlugin plugin) {

        Inventory menu = Bukkit.createInventory(null, 27, plugin.getConfig().getString("settings.stats.menu.name") + " - " + plugin.getConfig().getString("settings.stats.menu.sugar.name"));

        ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(" ");
        empty.setItemMeta(emptyMeta);

        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(plugin.getConfig().getString("settings.stats.menu.back"));
        back.setItemMeta(backMeta);

        ItemStack points = new ItemStack(Material.NETHER_STAR);
        ItemMeta pointsMeta = points.getItemMeta();

        pointsMeta.setDisplayName(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.name")
                .replaceAll("&c", plugin.getConfig().getString("settings.stats.menu.sugar.name"))
        );


        if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".speed") < plugin.getConfig().getInt("settings.max_levels") && plugin.getConfig().getInt("data." + player.getUniqueId() + ".points") > 0) {

            ItemStack add = new ItemStack(Material.EMERALD_BLOCK);
            ItemMeta addMeta = add.getItemMeta();
            NamespacedKey key = new NamespacedKey(plugin, "speed_add");
            addMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
            addMeta.setDisplayName(plugin.getConfig().getString("settings.stats.menu.add"));
            add.setItemMeta(addMeta);

            menu.setItem(11, add);

        }

        if (plugin.getConfig().getInt("data." + player.getUniqueId() + ".speed") > 0) {

            ItemStack remove = new ItemStack(Material.REDSTONE_BLOCK);
            ItemMeta removeMeta = remove.getItemMeta();
            NamespacedKey key = new NamespacedKey(plugin, "speed_remove");
            removeMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
            removeMeta.setDisplayName(plugin.getConfig().getString("settings.stats.menu.remove"));
            remove.setItemMeta(removeMeta);

            menu.setItem(15, remove);

        }

        List<String> pointLore = new ArrayList<>();
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.lore1").replaceAll("&c", String.valueOf(plugin.getConfig().getInt("data." + player.getUniqueId() + ".speed"))));
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.lore2").replaceAll("&c", String.valueOf(plugin.getConfig().getInt("data." + player.getUniqueId() + ".speed"))));
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.stats.menu.level.lore3").replaceAll("&c", String.valueOf(plugin.getConfig().getInt("data." + player.getUniqueId() + ".speed"))));

        pointsMeta.setLore(pointLore);

        points.setItemMeta(pointsMeta);

        menu.setItem(13, points);
        menu.setItem(22, back);

        for (int i = 0; i < 27; i++) {

            if (menu.getItem(i) == null) {

                menu.setItem(i, empty);

            }

        }

        player.openInventory(menu);

    }

}
