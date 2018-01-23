package pl.fcraft.antylogout.command

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import pl.fcraft.antylogout.AntyLogoutPlugin
import pl.fcraft.antylogout.base.LogoutData
import pl.fcraft.core.util.base.PlayerCommand
import pl.fcraft.core.v3.extension.message

class FightCommand: PlayerCommand("fcraft.fight") {

    override fun onCommand(player: Player, args: Array<out String>) {
        if (args.isEmpty()) {
            player.message("hardcore.logout.command.missingArg")
            return
        }

        val target = Bukkit.getPlayer(args[0])
        if (target == null) {
            player.message("hardcore.logout.command.targetNotFound", mapOf("target" to args[0]))
            return
        }

        if (target == player) {
            player.message("hardcore.logout.command.cantFightYourself")
            return
        }

        val distance = AntyLogoutPlugin.instance.fightCommandDistance
        if (player.location.distance(target.location) > distance) {
            player.message("hardcore.logout.command.targetToFar", mapOf("configDistance" to distance, "target" to target.name))
            return
        }

        val playerData = LogoutData.get(player) ?: LogoutData(player)
        val targetData = LogoutData.get(target) ?: LogoutData(target)
        targetData.fight(player)
        playerData.fight(target)
    }

}