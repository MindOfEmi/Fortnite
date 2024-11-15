package at.typischersepp.fortnite.handlers

import at.typischersepp.fortnite.Fortnite
import at.typischersepp.fortnite.utils.getMetadata
import at.typischersepp.fortnite.utils.reload
import at.typischersepp.fortnite.utils.setMetadata
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.persistence.PersistentDataType

class ShootHandler(private var plugin: Fortnite) : Listener {
    // Initialize Handler
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    fun onLeftClick(event: PlayerInteractEvent) {
        // Check if shoot with gun
        if (!event.action.isLeftClick) return

        // Avoid breaking blocks
        event.isCancelled = true

        // Initialize variable for better understanding
        val player = event.player
        val inventory = player.inventory

        // Check if gun has ammo
        // If it doesn't have ammo, reload
        val ammo = getMetadata(inventory.itemInMainHand, NamespacedKey(plugin, "ammo"), PersistentDataType.INTEGER)
        if (ammo == 0) return reload(player, plugin)

        // Remove shoot from ammo
        setMetadata(inventory.itemInMainHand, NamespacedKey(plugin, "ammo"), PersistentDataType.INTEGER, ammo - 1)
        player.level = ammo - 1

        // Spawn Bullet
        val projectile = player.world.spawnEntity(player.eyeLocation, EntityType.ARROW)

        // Set amount of damage to bullet and launch
        projectile.setMetadata("damage", FixedMetadataValue(plugin, getMetadata(inventory.itemInMainHand, NamespacedKey(plugin, "damage"), PersistentDataType.INTEGER)))
        projectile.velocity = player.location.direction.multiply(1000)
    }
}
