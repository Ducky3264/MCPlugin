package org.davidmc.test1.commands;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.davidmc.test1.Main;
import org.davidmc.test1.Events.MyEvents;
public class BowCommand implements CommandExecutor {
    private Main plugin;
    public BowCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("ebow").setExecutor(this);

    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may execute this command!");
            return true;
        }
        Player p = (Player) sender;
        MyEvents me = new MyEvents();
        p.sendMessage("Here's your bow!");
        ItemStack is = me.cgit(Material.BOW, "Explosive bow", 1, "§a-Explosive");
        p.getInventory().addItem(is);
        return true;
        
    }
}
