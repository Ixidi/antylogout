package pl.fcraft.antylogout.command

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import pl.fcraft.antylogout.AntyLogoutPlugin
import pl.fcraft.antylogout.base.LogoutTask
import pl.fcraft.core.util.base.PlayerCommand
import pl.fcraft.core.v3.extension.message

class FightCommand: PlayerCommand("fcraft.fight") {

    override fun onCommand(player: Player, args: Array<out String>) {
        if (args.isEmpty()) {
            player.message("logout.command.missingArg")
            return
        }
        val target = Bukkit.getPlayer(args[0])
        if (target == null) {
            player.message("logout.command.targetNotFound", mapOf("target" to args[0]))
            return
        }
        val distance = AntyLogoutPlugin.instance.config.fightCommandDistance;
        if (player.location.distance(target.location) > distance) {
            player.message("logout.command.targetToFar", mapOf("configDistance" to distance, "target" to target.name))
            return
        }
        val targetTask = LogoutTask.get(target) ?: LogoutTask(target)
        val playerTask = LogoutTask.get(player) ?: LogoutTask(player)

        playerTask.fight(target)
        targetTask.fight(player)

        playerTask.start()
        targetTask.start()
    }

}