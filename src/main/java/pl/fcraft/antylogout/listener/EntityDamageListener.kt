package pl.fcraft.antylogout.listener

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import pl.fcraft.antylogout.base.LogoutData
import pl.fcraft.antylogout.base.LogoutTask

class EntityDamageListener: Listener {

    @EventHandler
    fun onEntityDamage(event: EntityDamageByEntityEvent) {
        if (event.damager !is Player || event.entity !is Player)
            return

        val damager = event.damager as Player
        val victim = event.entity as Player

        val damagerData = LogoutData.get(damager) ?: LogoutData(damager)
        val victimData = LogoutData.get(victim) ?: LogoutData(victim)

        damagerData.fight(victim)
        victimData.fight(damager)

    }

}