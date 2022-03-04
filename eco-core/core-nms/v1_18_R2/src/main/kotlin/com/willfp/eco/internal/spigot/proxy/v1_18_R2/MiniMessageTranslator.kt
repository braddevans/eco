package com.willfp.eco.internal.spigot.proxy.v1_18_R2

import com.willfp.eco.internal.spigot.proxy.MiniMessageTranslatorProxy
import com.willfp.eco.util.toLegacy
import net.kyori.adventure.text.minimessage.MiniMessage

class MiniMessageTranslator : MiniMessageTranslatorProxy {
    override fun format(message: String): String {
        return MiniMessage.miniMessage().deserialize(message).toLegacy()
    }
}
