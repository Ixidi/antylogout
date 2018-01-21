package pl.fcraft.core.util.base;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public abstract class PermissionCommand implements CommandExecutor {

    private final String permission;

    public PermissionCommand(String permission) {
        this.permission = permission;
    }

    protected abstract void onCommand(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean hasPermission = sender.hasPermission(permission);
        if ("console".equals(permission) && !(sender instanceof ConsoleCommandSender)) {
            hasPermission = false;
        }
        if (hasPermission) {
            onCommand(sender, args);
        } else {
            sender.sendMessage("§6Serwer»§c Nie masz uprawnień do tej komendy!");
        }
        return true;
    }

}