package at.typischersepp.fortnite.handlers

import at.typischersepp.fortnite.Fortnite
import org.bukkit.Bukkit
import org.bukkit.entity.Arrow
import org.bukkit.entity.Damageable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class HitHandler(plugin: Fortnite) : Listener {
    // Initialize Handler
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    fun onHit(event: EntityDamageByEntityEvent) {
        // Cancel if damage is not from Bullet
        if (event.damager !is Arrow) return
        event.isCancelled = true

        // Damage Entity
        val entity = event.entity
        (entity as Damageable).damage(event.damager.getMetadata("damage")[0].asInt().toDouble())
    }
}
