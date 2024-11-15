package at.typischersepp.fortnite.handlers;

import at.typischersepp.fortnite.Fortnite;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class TMP implements Listener {
    Fortnite plugin;

    public TMP(Fortnite plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ItemStack gun = new ItemStack(Material.STICK);
        NamespacedKey key = new NamespacedKey(plugin, "ammo");
        ItemMeta itemMeta = gun.getItemMeta();
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 10);

        gun.setItemMeta(itemMeta);

        player.getInventory().setItemInMainHand(gun);
    }
}
