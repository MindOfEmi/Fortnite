package at.typischersepp.fortnite.handlers

import at.typischersepp.fortnite.Fortnite
import at.typischersepp.fortnite.utils.Metadata
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.persistence.PersistentDataType

class ReloadHandler(private var plugin: Fortnite) : Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    fun onReload(event: PlayerSwapHandItemsEvent) {
        val player = event.player
        val inventory = player.inventory

        event.isCancelled = true
        if (!inventory.itemInMainHand.hasItemMeta()) return

        val ammoCapacity = Metadata().getMetadata(inventory.itemInMainHand, NamespacedKey(plugin, "ammoCapacity"), PersistentDataType.INTEGER)
        Metadata().setMetadata(inventory.itemInMainHand, NamespacedKey(plugin, "ammo"), PersistentDataType.INTEGER, ammoCapacity)

        player.level = ammoCapacity

        Bukkit.broadcast(Component.text(Metadata().getMetadata(inventory.itemInMainHand, NamespacedKey(plugin, "ammo"), PersistentDataType.INTEGER).toString()))
    }
}