package org.davidmc.test1.commands;
import org.davidmc.test1.Main;
import org.davidmc.test1.Events.MyEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class HelloCommand implements CommandExecutor {
    private Main plugin;
    public HelloCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("hello").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may execute this command!");
            return true;
        }
        Player p = (Player) sender;
        p.sendMessage("Hi!");
        MyEvents events = new MyEvents();
        events.openInventory(p);
        return true;
        /**
         * Decomment this section if you want to add permissions! Will require an additional plugin such as vault.
         * if (p.hasPermission("hello.use")) {
            p.sendMessage("Hi!");
            return true;
        } else {
            p.sendMessage("You do not have permission to execute this command!");
            return true;
        }**/
        
    }
}
