package pl.fcraft.antylogout.base

import pl.fcraft.antylogout.bossBar
import java.util.*

class LogoutTask: Runnable {


    override fun run() {
        val cleaner = arrayListOf<UUID>()
        LogoutData.map.forEach { (uuid, data) ->
            data.time--
            when {
                data.time == 0 ->  bossBar(data.bossBar, "hardcore.logout.canLogoutBar")
                data.time == -1 -> {
                    data.bossBar.removeAll()
                    data.bossBar.isVisible = false
                    cleaner.add(uuid)
                }
                else -> bossBar(data.bossBar, "hardcore.logout.fightBar", mapOf("opponent" to data.opponent.name, "time" to data.time))
            }
            if (!data.bossBar.isVisible)
                data.bossBar.isVisible = true
        }
        if (cleaner.size > 0)
            cleaner.forEach { uuid -> LogoutData.remove(uuid) }
    }

}