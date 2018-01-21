package pl.fcraft.core.util.base;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerCommand extends PermissionCommand {

    public PlayerCommand(String permission) {
        super(permission);
    }

    protected abstract void onCommand(Player player, String[] args);

    @Override
    protected void onCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            onCommand((Player) sender, args);
        } else {
            sender.sendMessage("Ta komenda nie może być wykonana z konsoli!");
        }
    }

}