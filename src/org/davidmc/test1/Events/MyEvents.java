package org.davidmc.test1.Events;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MyEvents implements Listener {
    public class pSet {
        public int K;
        public Boolean V;
        public pSet(int k, Boolean v) {
            K = k;
            V = v;
        }
    }
    public class DavidMCHolder implements InventoryHolder {  
        @Override
        public Inventory getInventory() {
            return null;
        }
    }
    private final Inventory inv;
    public MyEvents() {
        inv = Bukkit.createInventory(new DavidMCHolder(), 9, "Menu");
        initalizeItems();
    }

    public void initalizeItems() {
        for (int i = 1; i <= 9; i++) {
            //Bukkit.getLogger().info(String.valueOf(i));
            if (i == 4) {
                inv.addItem(cgit(Material.DIAMOND_SWORD, "Example Sword", 1, "§aFirst line of the lore"));
            } else if (i == 5) {
                inv.addItem(cgit(Material.DIAMOND_CHESTPLATE, "Example Chestplate", 1, "§aFirst line of the lore"));
            } else {
            inv.addItem(cgit(Material.GRAY_STAINED_GLASS_PANE, "", 1, String.valueOf(i)));
            }
        }
    }
    public ItemStack cgit(final Material mat, final String name, final int num, final String... lore) {
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
        //Bukkit.getLogger().info("OIC hit.");
        //Bukkit.getLogger().info((e.getClickedInventory().getType() == InventoryType.PLAYER) ? "statement is true" : "Statement is false.");
        if (e.getClickedInventory().getType() == InventoryType.PLAYER || !(e.getClickedInventory().getHolder() instanceof DavidMCHolder)) return;
        e.setCancelled(true);
        final ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;
        final Player p = (Player) e.getWhoClicked();
        p.sendMessage("You clicked at slot" + e.getRawSlot());
    } 
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        //Bukkit.getLogger().info("On inventory hit.");
        if (e.getInventory().getType() != InventoryType.PLAYER && (e.getInventory().getHolder() instanceof DavidMCHolder)) {
          e.setCancelled(true);
        }
    }
    HashMap<Integer, pSet> EID2 = new HashMap<Integer, pSet>();
    @EventHandler
    public void onSCB(EntityShootBowEvent e) {
        Bukkit.getLogger().info("Bow shot!");
        Player p = (Player) e.getEntity();
        if (e.getBow().getItemMeta().getLore().contains("§a-Explosive")) {
           EID2.put(e.getProjectile().getEntityId(), new pSet((int) (e.getForce()*4), (p.getInventory().getItemInOffHand().getType() == Material.FLINT_AND_STEEL)));
           Bukkit.getLogger().info(String.valueOf(e.getForce()));
        }
    }
    @EventHandler
    public void onSCHit(ProjectileHitEvent e) {
        Bukkit.getLogger().info("Hit!"); 
        if (!(e.getEntityType().equals(EntityType.ARROW))) return;
        if (EID2.containsKey(e.getEntity().getEntityId())) {
        World w = e.getEntity().getWorld();
        try {
        w.createExplosion(e.getHitBlock().getLocation(), EID2.get(e.getEntity().getEntityId()).K, EID2.get(e.getEntity().getEntityId()).V);
        } catch (Exception ex) {
            w.createExplosion(e.getHitEntity().getLocation(), EID2.get(e.getEntity().getEntityId()).K, EID2.get(e.getEntity().getEntityId()).V);
        }
        EID2.remove(e.getEntity().getEntityId());
        
        } 
    } 
    }

