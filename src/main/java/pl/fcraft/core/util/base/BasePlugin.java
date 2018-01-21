package pl.fcraft.core.util.base;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BasePlugin extends JavaPlugin implements Listener, Reloadable {

    protected abstract void innerReload();

    private final List<Reloadable> toReload = new ArrayList<>();

    public void register(Reloadable reloadable) {
        toReload.add(reloadable);
        reloadable.reload();
    }

    @Override
    public void reload() {
        innerReload();
        for (Reloadable reloadable : toReload) {
            reloadable.reload();
        }
    }

    protected void listener() {
        listener(this);
    }

    protected void listener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    protected void command(String name, CommandExecutor executor) {
        getCommand(name).setExecutor(executor);
    }

}