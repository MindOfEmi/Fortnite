package at.typischersepp.fortnite.handlers

import at.typischersepp.fortnite.Fortnite
import org.bukkit.Bukkit
import org.bukkit.entity.Arrow
import org.bukkit.entity.Damageable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class HitHandler(plugin: Fortnite) : Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    fun onHit(event: EntityDamageByEntityEvent) {
        if (event.damager !is Arrow) return
        event.isCancelled = true

        val entity = event.entity
        (entity as Damageable).damage(event.damager.getMetadata("damage")[0].asInt().toDouble())
    }
}
