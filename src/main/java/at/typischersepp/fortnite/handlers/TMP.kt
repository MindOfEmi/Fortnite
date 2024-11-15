package at.typischersepp.fortnite.handlers

import at.typischersepp.fortnite.Fortnite
import at.typischersepp.fortnite.utils.setMetadata
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class TMP(private var plugin: Fortnite) : Listener {
    // Initialize Handler
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        // Initialize variables for better understanding
        val player = event.player
        val gun = ItemStack(Material.STICK)

        // Set metadata to gun
        setMetadata(gun, NamespacedKey(plugin, "ammo"), PersistentDataType.INTEGER, 20)
        setMetadata(gun, NamespacedKey(plugin, "ammoCapacity"), PersistentDataType.INTEGER, 20)
        setMetadata(gun, NamespacedKey(plugin, "damage"), PersistentDataType.INTEGER, 20)

        // Give player gun
        player.inventory.setItemInMainHand(gun)
    }
}
