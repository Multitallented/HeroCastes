package multitallented.redcastlemedia.bukkit.herocastes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Herocastes extends JavaPlugin {
    @Override
    public void onDisable() {
        System.out.println(this + " is now disabled!");
    }

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        
        
        System.out.println(this + " is now enabled!");
    }
    
    @Override
    public boolean onCommand(CommandSender cs, Command command, String label, String args[]) {
        if (!(cs instanceof Player)) {
            cs.sendMessage(ChatColor.RED + "[HeroCastes] doesn't recognize non-player commands");
            return true;
        }
        Player player = (Player) cs;
        if (args.length > 1) {
            
        }
        return true;
    }
}
