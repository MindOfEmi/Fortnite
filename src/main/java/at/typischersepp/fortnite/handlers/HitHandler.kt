package at.typischersepp.fortnite.handlers;

import at.typischersepp.fortnite.Fortnite;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class HitHandler implements Listener {
    public HitHandler(Fortnite plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Arrow)) return;
        event.setCancelled(true);

        Entity entity = event.getEntity();
        ((Damageable) entity).damage(event.getDamager().getMetadata("damage").get(0).asInt());
    }
}
