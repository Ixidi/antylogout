package pl.fcraft.antylogout.base

import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar
import org.bukkit.entity.Player
import pl.fcraft.antylogout.AntyLogoutPlugin
import java.util.*

data class LogoutData(val player: Player) {

    companion object {
        val map = HashMap<UUID, LogoutData>()

        fun add(data: LogoutData) {
            map[data.player.uniqueId] = data
        }

        fun get(player: Player): LogoutData? {
            return map[player.uniqueId]
        }

        fun remove(uuid: UUID) {
            if (map.containsKey(uuid))
                map.remove(uuid)
        }
    }

    var time = 0
    var bossBar: BossBar
    lateinit var opponent: Player

    init {
        add(this)
        bossBar = Bukkit.createBossBar("", BarColor.RED, BarStyle.SOLID)
        bossBar.isVisible = false
    }

    fun fight(opponent: Player) {
        if (!bossBar.players.contains(player))
            bossBar.addPlayer(player)
        this.opponent = opponent
        this.time = AntyLogoutPlugin.instance.logoutTime + 1
    }

}