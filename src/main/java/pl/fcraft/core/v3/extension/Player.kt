package pl.fcraft.core.v3.extension

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.boss.BossBar
import org.bukkit.entity.Player

fun Player.message(key: String, args: Map<String,Any?> = mapOf()) {
    spigot().sendMessage(ChatMessageType.SYSTEM, /*Message.xml(key, args)*/TextComponent("$key => $args"))
}
