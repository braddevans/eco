package com.willfp.eco.internal.gui.slot

import com.willfp.eco.core.gui.menu.Menu
import com.willfp.eco.core.gui.slot.Slot
import com.willfp.eco.core.gui.slot.SlotBuilder
import com.willfp.eco.core.gui.slot.functional.SlotHandler
import com.willfp.eco.core.gui.slot.functional.SlotProvider
import com.willfp.eco.core.gui.slot.functional.SlotUpdater
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import java.util.function.Predicate

internal object NoOpSlot : SlotHandler {
    override fun handle(event: InventoryClickEvent, slot: Slot, menu: Menu) {

    }

    override fun equals(other: Any?): Boolean {
        return other is NoOpSlot
    }
}

internal class NoOpForPlayer

class EcoSlotBuilder(private val provider: SlotProvider) : SlotBuilder {
    private var captive = false
    private var captiveFromEmpty = false
    private var updater: SlotUpdater = SlotUpdater { player, menu, _ -> provider.provide(player, menu) }

    private var onLeftClick: SlotHandler = NoOpSlot
    private var onRightClick: SlotHandler = NoOpSlot
    private var onShiftLeftClick: SlotHandler = NoOpSlot
    private var onShiftRightClick: SlotHandler = NoOpSlot
    private var onMiddleClick: SlotHandler = NoOpSlot

    private var notCaptiveFor: (Player) -> Boolean = { false }

    override fun onLeftClick(action: SlotHandler): SlotBuilder {
        onLeftClick = action
        return this
    }

    override fun onRightClick(action: SlotHandler): SlotBuilder {
        onRightClick = action
        return this
    }

    override fun onShiftLeftClick(action: SlotHandler): SlotBuilder {
        onShiftLeftClick = action
        return this
    }

    override fun onShiftRightClick(action: SlotHandler): SlotBuilder {
        onShiftRightClick = action
        return this
    }

    override fun onMiddleClick(action: SlotHandler): SlotBuilder {
        onMiddleClick = action
        return this
    }

    override fun notCaptiveFor(predicate: Predicate<Player>): SlotBuilder {
        notCaptiveFor = { predicate.test(it) }
        return this
    }

    override fun setCaptive(fromEmpty: Boolean): SlotBuilder {
        captive = true
        captiveFromEmpty = fromEmpty
        return this
    }

    override fun setUpdater(updater: SlotUpdater): SlotBuilder {
        this.updater = updater
        return this
    }

    override fun build(): Slot {
        return if (captive) {
            EcoCaptiveSlot(
                provider,
                captiveFromEmpty,
                notCaptiveFor
            )
        } else {
            EcoSlot(
                provider,
                onLeftClick,
                onRightClick,
                onShiftLeftClick,
                onShiftRightClick,
                onMiddleClick,
                updater
            )
        }
    }
}
