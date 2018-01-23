package pl.fcraft.antylogout

import pl.fcraft.antylogout.base.LogoutTask
import pl.fcraft.antylogout.command.FightCommand
import pl.fcraft.antylogout.listener.EntityDamageListener
import pl.fcraft.antylogout.listener.PlayerDeathListener
import pl.fcraft.antylogout.listener.PlayerQuitListener
import pl.fcraft.core.util.base.BasePlugin

class AntyLogoutPlugin: BasePlugin(){

    var logoutTime: Int = 60
    var fightCommandDistance: Int = 5

    companion object {
        lateinit var instance: AntyLogoutPlugin
    }

    override fun innerReload() {
        reloadConfig()
        logoutTime = config.getInt("logoutTime")
        fightCommandDistance = config.getInt("fightCommandDistance")
    }

    override fun onEnable() {
        instance = this

        if (!this.dataFolder.exists())
            this.dataFolder.mkdirs()

        config.addDefault("logoutTime", 60)
        config.addDefault("fightCommandDistance", 5)
        config.options().copyDefaults(true)
        saveConfig()
        innerReload()

        command("fight", FightCommand())

        listener(EntityDamageListener())
        listener(PlayerQuitListener())
        listener(PlayerDeathListener())

        server.scheduler.runTaskTimer(this, LogoutTask(), 0, 20)
    }

}