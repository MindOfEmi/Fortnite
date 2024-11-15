package at.typischersepp.fortnite.handlers

import at.typischersepp.fortnite.Fortnite
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class TMP(var plugin: Fortnite) : Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player

        val gun = ItemStack(Material.STICK)
        val key = NamespacedKey(plugin, "ammo")
        val itemMeta = gun.itemMeta
        itemMeta.persistentDataContainer.set(key, PersistentDataType.INTEGER, 20)
        itemMeta.persistentDataContainer.set(NamespacedKey(plugin, "ammoCapacity"), PersistentDataType.INTEGER, 20)

        gun.setItemMeta(itemMeta)

        player.inventory.setItemInMainHand(gun)
    }
}
