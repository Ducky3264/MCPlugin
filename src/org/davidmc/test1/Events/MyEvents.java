package org.davidmc.test1.Events;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.inventory.Inventory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public class MyEvents implements Listener {
    private final Inventory inv;
    public MyEvents() {
        inv = Bukkit.createInventory(null, 9, "Menu");
        initalizeItems();
    }
    public void initalizeItems() {
        for (int i = 1; i <= 9; i++) {
            Bukkit.getLogger().info(String.valueOf(i));
            if (i == 4) {
                inv.addItem(cgit(Material.DIAMOND_SWORD, "Example Sword", 1, "§aFirst line of the lore"));
            } else if (i == 5) {
                inv.addItem(cgit(Material.DIAMOND_CHESTPLATE, "Example Chestplate", 1, "§aFirst line of the lore"));
            } else {
            inv.addItem(cgit(Material.GRAY_STAINED_GLASS_PANE, "", 1, String.valueOf(i)));
            }
        }
    }
    protected ItemStack cgit(final Material mat, final String name, final int num, final String... lore) {
        final ItemStack item = new ItemStack(mat, num);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }
    /**
     * This is a test event handler. Decommenting this will result in a message being sent every time the player moves for debugging purposes.
     * public void onMove(PlayerMoveEvent event) {
        Bukkit.getLogger().info("Player moved.");
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.GREEN + "You are moving...");
    }**/
    @EventHandler
    public void OIC(final InventoryClickEvent e) {
        Bukkit.getLogger().info("OIC hit.");
        Bukkit.getLogger().info((e.getClickedInventory().getType() == InventoryType.PLAYER) ? "statement is true" : "Statement is false.");
        if (e.getClickedInventory().getType() == InventoryType.PLAYER) return;
        e.setCancelled(true);
        final ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;
        final Player p = (Player) e.getWhoClicked();
        p.sendMessage("You clicked at slot" + e.getRawSlot());
    } 
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        Bukkit.getLogger().info("On inventory hit.");
        if (e.getInventory().getType() == InventoryType.PLAYER) {
          e.setCancelled(true);
        }
    }
}
