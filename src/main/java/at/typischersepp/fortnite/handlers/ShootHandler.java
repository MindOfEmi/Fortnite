package at.typischersepp.fortnite.handlers;

import at.typischersepp.fortnite.Fortnite;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Random;

public class ShootHandler implements Listener {
    Fortnite plugin;

    public ShootHandler(Fortnite plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent event) {
        if (!event.getAction().isLeftClick() ||
                !event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() ||
                !event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) return;

        event.setCancelled(true);

        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();

        Entity projectile = player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.ARROW);

        Random random = new Random();

        projectile.setMetadata("damage", new FixedMetadataValue(plugin, random.nextInt(100)));
        projectile.setVelocity(player.getLocation().getDirection().multiply(1000));

        Bukkit.broadcast(Component.text(inventory.getItemInMainHand().getItemMeta().getCustomModelData()));
    }
}
