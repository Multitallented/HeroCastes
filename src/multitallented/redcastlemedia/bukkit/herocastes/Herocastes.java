package multitallented.redcastlemedia.bukkit.herocastes;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Herocastes extends JavaPlugin {
    private CasteManager cm;
    public static Economy econ;
    public static Permission perms;
    @Override
    public void onDisable() {
        System.out.println(this + " is now disabled!");
    }

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        
        cm = new CasteManager();
        
        //register events
        
        setupEconomy();
        setupPermissions();
        
        System.out.println(this + " is now enabled!");
    }
    
    @Override
    public boolean onCommand(CommandSender cs, Command command, String label, String args[]) {
        if (!(cs instanceof Player)) {
            cs.sendMessage(ChatColor.RED + "[HeroCastes] doesn't recognize non-player commands");
            return true;
        }
        Player player = (Player) cs;
        if (args.length > 1 && args[0].equalsIgnoreCase("setcaste")) {
            //Check if the player has permission for that caste
            if (!perms.has(player, "herocaste.all") && !perms.has(player, "herocaste." + args[1])) {
                player.sendMessage(ChatColor.GRAY + "[HeroCastes] You don't have permission for caste " + args[1]);
                return true;
            }
            //Check if valid caste
            boolean isValid = false;
            for (CasteTypes t : CasteTypes.values()) {
                try {
                    if (t.toString().equalsIgnoreCase(args[1])) {
                        isValid = true;
                    }
                } catch (Exception e) {
                }
            }
            if (!isValid) {
                player.sendMessage(ChatColor.GRAY + "[HeroCastes] " + args[1] + " isn't a valid caste.");
                return true;
            }
            //Check if the player already is that caste
            Person p = cm.getPerson(player.getName());
            if (p != null && p.getType().equals(args[1])) {
                player.sendMessage(ChatColor.GRAY + "[HeroCastes] You already are in caste " + args[1]);
                return true;
            }
            
            cm.setCaste(player.getName(), args[1]);
            player.sendMessage(ChatColor.GOLD + "[HeroCastes] You are now in the " + args[1] + " caste.");
        }
        return true;
    }
    
    public boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            econ = rsp.getProvider();
            if (econ != null)
                System.out.println("[HeroStronghold] Hooked into " + econ.getName());
        }
        return econ != null;
    }
    private Boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            perms = permissionProvider.getProvider();
            if (perms != null)
                System.out.println("[HeroStronghold] Hooked into " + perms.getName());
        }
        return (perms != null);
    }
}
