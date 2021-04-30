package org.davidmc.test1;
import org.davidmc.test1.commands.HelloCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        new HelloCommand(this);
    }
}
