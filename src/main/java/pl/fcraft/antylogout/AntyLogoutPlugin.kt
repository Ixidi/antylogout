package pl.fcraft.antylogout

import pl.fcraft.antylogout.command.FightCommand
import pl.fcraft.antylogout.data.LogoutConfiguration
import pl.fcraft.antylogout.listener.EntityDamageListener
import pl.fcraft.core.util.base.BasePlugin
import java.io.File

class AntyLogoutPlugin: BasePlugin(){

    companion object {
        lateinit var instance: AntyLogoutPlugin
    }

    lateinit var config: LogoutConfiguration

    override fun innerReload() {
        //TODO
    }

    override fun onEnable() {
        instance = this
        config = LogoutConfiguration(File(this.dataFolder, "config.yml"))
        super.register(config)
        this.getCommand("fight").executor = FightCommand()
        super.listener(EntityDamageListener())
    }

}