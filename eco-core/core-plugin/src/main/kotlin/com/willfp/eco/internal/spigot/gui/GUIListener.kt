package com.willfp.eco.internal.spigot.gui

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.internal.gui.menu.EcoMenu
import com.willfp.eco.internal.gui.menu.MenuHandler
import com.willfp.eco.internal.gui.menu.asRenderedInventory
import com.willfp.eco.internal.gui.menu.getMenu
import com.willfp.eco.internal.gui.slot.EcoSlot
import com.willfp.eco.util.MenuUtils
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

class GUIListener(private val plugin: EcoPlugin) : Listener {
    @EventHandler(priority = EventPriority.HIGH)
    fun handleSlotClick(event: InventoryClickEvent) {
        val rendered = event.clickedInventory?.asRenderedInventory() ?: return

        val menu = rendered.menu

        val (row, column) = MenuUtils.convertSlotToRowColumn(event.slot)

        val slot = menu.getSlot(row, column) as? EcoSlot ?: return

        slot.handleInventoryClick(event, menu)

        plugin.scheduler.run { rendered.render() }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun handleShiftClick(event: InventoryClickEvent) {
        if (!event.isShiftClick) {
            return
        }

        val player = event.whoClicked as? Player ?: return

        val inv = player.openInventory.topInventory

        if (inv == event.clickedInventory) {
            return
        }

        val menu = inv.getMenu() ?: return
        val rendered = inv.asRenderedInventory() ?: return

        val (row, column) = MenuUtils.convertSlotToRowColumn(inv.firstEmpty())

        val slot = menu.getSlot(row, column)

        if (!slot.isCaptive) {
            event.isCancelled = true
        }

        plugin.scheduler.run { rendered.render() }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun handleClose(event: InventoryCloseEvent) {
        val menu = event.inventory.getMenu() as? EcoMenu ?: return

        menu.handleClose(event)

        plugin.scheduler.run { MenuHandler.unregisterInventory(event.inventory) }
    }
}
