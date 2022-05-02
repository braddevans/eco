package com.willfp.eco.internal.spigot.data.storage

import com.willfp.eco.core.Eco
import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.internal.spigot.data.EcoProfile

class ProfileSaver(plugin: EcoPlugin) {
    init {
        plugin.scheduler.runTimer({
            for ((uuid, set) in EcoProfile.CHANGE_MAP) {
                Eco.getHandler().profileHandler.saveKeysFor(uuid, set)
            }
            EcoProfile.CHANGE_MAP.clear()
        }, 1, 1)
    }
}