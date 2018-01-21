package pl.fcraft.antylogout.data

import org.bukkit.configuration.file.YamlConfiguration
import pl.fcraft.core.util.base.Reloadable
import java.io.File

class LogoutConfiguration(val file: File): Reloadable {

    var logoutTime = 60
    var fightCommandDistance = 6

    override fun reload() {
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw RuntimeException("Could not create ${file.name} file!")
            }
            val yaml = YamlConfiguration()
            yaml.set("logoutTime", logoutTime)
            yaml.set("fightCommandDistance", fightCommandDistance)
            yaml.save(file)
        } else {
            val yaml = YamlConfiguration.loadConfiguration(file)
            logoutTime = yaml.getInt("logoutTime")
            fightCommandDistance = yaml.getInt("fightCommandDistance")
        }
    }

}