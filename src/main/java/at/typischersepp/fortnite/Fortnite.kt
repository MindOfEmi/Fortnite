package at.typischersepp.fortnite

import at.typischersepp.fortnite.handlers.HitHandler
import at.typischersepp.fortnite.handlers.ReloadHandler
import at.typischersepp.fortnite.handlers.ShootHandler
import at.typischersepp.fortnite.handlers.TMP
import org.bukkit.plugin.java.JavaPlugin

class Fortnite : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        logger.info("Hello World!")

        ShootHandler(this)
        HitHandler(this)
        ReloadHandler(this)
        TMP(this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
