package pl.fcraft.antylogout

import org.bukkit.boss.BossBar

fun bossBar(bar: BossBar, key: String, args: Map<String,Any?> = mapOf()) {
    bar.title = "$key => $args"
}