package pl.fcraft.antylogout.listener

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import pl.fcraft.antylogout.base.LogoutData
import pl.fcraft.antylogout.base.LogoutTask
import pl.fcraft.core.v3.extension.message

class PlayerQuitListener: Listener {

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val player = event.player
        val data = LogoutData.get(player)
        if (data != null && data.time > 0) {
            //ban
            val opponentData = LogoutData.get(data.opponent)
            if (opponentData != null && opponentData.opponent == player) {
                opponentData.bossBar.removeAll()
                opponentData.bossBar.isVisible = false
                LogoutData.remove(opponentData.player.uniqueId)
            }
            LogoutData.remove(player.uniqueId)
            Bukkit.getOnlinePlayers().forEach { p -> p.message("hardcore.logout.broadcast", mapOf("nick" to player.name)) }
        }
    }

}