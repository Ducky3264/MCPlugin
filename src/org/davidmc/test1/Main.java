package org.davidmc.test1;
import org.davidmc.test1.commands.*;
import org.davidmc.test1.Events.MyEvents;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    MyEvents events = new MyEvents();
    @Override
    public void onEnable() {
        new HelloCommand(this);
        getServer().getPluginManager().registerEvents(events, this);
        
        
    }
}
