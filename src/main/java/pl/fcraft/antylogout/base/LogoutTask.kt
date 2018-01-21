package pl.fcraft.antylogout.base

import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import pl.fcraft.antylogout.AntyLogoutPlugin
import pl.fcraft.core.v3.extension.actionBar
import java.util.*
import kotlin.collections.HashMap

data class LogoutTask(val player: Player): BukkitRunnable() {

    companion object {
        private val map = HashMap<UUID, LogoutTask>()

        fun get(uuid: UUID): LogoutTask? = map[uuid]

        fun get(player: Player): LogoutTask? = map[player.uniqueId]

        fun add(task: LogoutTask) {
            map[task.player.uniqueId] = task
        }

        fun remove(uuid: UUID) {
            if (map.containsKey(uuid)) map.remove(uuid)
        }
    }


    private var running = false
    var time: kotlin.Int = 0
    lateinit var lastOpponent: Player

    override fun run() {
        time--
        if (time <= 0) {
            remove(player.uniqueId)
            this.cancel()
            player.actionBar("&aWalka dobiegła końca, możesz się wylogować!")
            return
        }
        player.actionBar("&cWalczysz z &7${lastOpponent.name} &cczas do zakonczenia to &7$time sekund.")
    }

    fun fight(opponent: Player) {
        time = AntyLogoutPlugin.instance.config.logoutTime
        lastOpponent = opponent
    }

    fun start() {
        if (!running) {
            this.runTaskTimer(AntyLogoutPlugin.instance, 0, 20)
            running = true
        }
    }

}