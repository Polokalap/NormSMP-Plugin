package mel.Polokalap.normSMPPlugin.utils;

import mel.Polokalap.normSMPPlugin.NormSMPPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class pointItem {

    public static void pointItem(Player player, String from) {

        ItemStack pointItem = new ItemStack(Material.PAPER);
        ItemMeta pointMeta = pointItem.getItemMeta();

        pointMeta.setDisplayName(NormSMPPlugin.getInstance().getConfig().getString("settings.point.name"));
        pointMeta.setEnchantmentGlintOverride(true);

        List<String> pointLore = new ArrayList<>();
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.point.lore1"));
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.point.lore2"));
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.point.lore3"));
        pointLore.add(null);
        pointLore.add(NormSMPPlugin.getInstance().getConfig().getString("settings.point.lore4")
                .replaceAll("&c", from)
        );

        pointMeta.setLore(pointLore);

        pointItem.setItemMeta(pointMeta);

        player.getInventory().addItem(pointItem);

        player.sendMessage(chat.replace("player.item_given")
                .replaceAll("&c", NormSMPPlugin.getInstance().getConfig().getString("settings.point.name"))
        );

    }

}
