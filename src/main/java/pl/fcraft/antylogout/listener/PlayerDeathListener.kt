package pl.fcraft.antylogout.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import pl.fcraft.antylogout.base.LogoutData

class PlayerDeathListener : Listener {

    @EventHandler
    fun onEntityDeath(event: PlayerDeathEvent) {
        val data = LogoutData.get(event.entity)
        if (data != null) {
            data.bossBar.removeAll()
            data.bossBar.isVisible = false
            LogoutData.remove(event.entity.uniqueId)
        }
    }

}