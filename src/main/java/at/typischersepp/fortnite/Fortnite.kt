package at.typischersepp.fortnite;

import at.typischersepp.fortnite.handlers.HitHandler;
import at.typischersepp.fortnite.handlers.ShootHandler;
import at.typischersepp.fortnite.handlers.TMP;
import org.bukkit.plugin.java.JavaPlugin;

public final class Fortnite extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Hello World!");

        new ShootHandler(this);
        new HitHandler(this);
        new TMP(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}