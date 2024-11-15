package at.typischersepp.fortnite.handlers

import at.typischersepp.fortnite.Fortnite
import at.typischersepp.fortnite.utils.reload
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerSwapHandItemsEvent

class ReloadHandler(private var plugin: Fortnite) : Listener {
    // Initialize Handler
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    fun onReload(event: PlayerSwapHandItemsEvent) {
        val player = event.player
        // Avoid switching item to off-hand
        event.isCancelled = true
        reload(player, plugin)
    }
}