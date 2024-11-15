package at.typischersepp.fortnite.handlers

import at.typischersepp.fortnite.Fortnite
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.persistence.PersistentDataType
import java.util.*

class ShootHandler(private var plugin: Fortnite) : Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    fun onLeftClick(event: PlayerInteractEvent) {
        if (!event.action.isLeftClick) return

        event.isCancelled = true

        val player = event.player
        val inventory = player.inventory

        val gun = inventory.itemInMainHand
        val key = NamespacedKey(plugin, "ammo")
        val itemMeta = gun.itemMeta
        val ammo = itemMeta.persistentDataContainer.get(key, PersistentDataType.INTEGER)
        if (ammo == null || ammo == 0) return

        itemMeta.persistentDataContainer.set(key, PersistentDataType.INTEGER, ammo - 1)
        gun.setItemMeta(itemMeta)

        player.level = ammo - 1

        val projectile = player.world.spawnEntity(player.eyeLocation, EntityType.ARROW)

        val random = Random()

        projectile.setMetadata("damage", FixedMetadataValue(plugin, random.nextInt(100)))
        projectile.velocity = player.location.direction.multiply(1000)
    }
}
