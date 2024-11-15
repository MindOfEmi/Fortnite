package at.typischersepp.fortnite.utils

import at.typischersepp.fortnite.Fortnite
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType

fun reload(player: Player, plugin: Fortnite) {
    // Check if player has gun selected
    if (!player.inventory.itemInMainHand.hasItemMeta()) return

    // Set ammo to ammo Capacity
    val ammoCapacity = getMetadata(player.inventory.itemInMainHand, NamespacedKey(plugin, "ammoCapacity"), PersistentDataType.INTEGER)
    setMetadata(player.inventory.itemInMainHand, NamespacedKey(plugin, "ammo"), PersistentDataType.INTEGER, ammoCapacity)

    // Update ammo indicator
    player.level = ammoCapacity
}
