package at.typischersepp.fortnite.handlers;

import at.typischersepp.fortnite.Fortnite;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

public class ShootHandler implements Listener {
    Fortnite plugin;

    public ShootHandler(Fortnite plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent event) {
        if (!event.getAction().isLeftClick()) return;

        event.setCancelled(true);

        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();

        ItemStack gun = inventory.getItemInMainHand();
        NamespacedKey key = new NamespacedKey(plugin, "ammo");
        ItemMeta itemMeta = gun.getItemMeta();
        Integer ammo = itemMeta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
        if (ammo == null || ammo == 0) return;

        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, ammo - 1);
        gun.setItemMeta(itemMeta);

        player.setLevel(ammo - 1);

        Entity projectile = player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.ARROW);

        Random random = new Random();

        projectile.setMetadata("damage", new FixedMetadataValue(plugin, random.nextInt(100)));
        projectile.setVelocity(player.getLocation().getDirection().multiply(1000));
    }
}
