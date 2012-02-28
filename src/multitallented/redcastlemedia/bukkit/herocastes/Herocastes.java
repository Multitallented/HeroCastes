package multitallented.redcastlemedia.bukkit.herocastes;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
        
        //register events here
        
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
        if (econ==null) {
            player.sendMessage(ChatColor.RED + "[HeroCastes] An economy plugin is require but not found.");
            return true;
        }
        
        if (args.length > 2 && args[0].equalsIgnoreCase("create")) {
            //hcaste create target wage [bonus] [limit]
            
            //Check player has enough money
            double wage = 0;
            try {
                wage = Integer.parseInt(args[2]);
                if (econ.getBalance(player.getName()) < wage) {
                    player.sendMessage(ChatColor.RED + "[HeroCastes] You don't have enough money to pay that.");
                    return true;
                }
            } catch (Exception e) {

            }
            
            Player p = this.getServer().getPlayer(args[1]);
            Person pe = cm.getPerson(args[1]);
            Material m = Material.getMaterial(args[1]);
            String target = null;
            CasteTypes type = null;
            //Check if target is a valid item, player, or HS region
            if (p != null) {
                target = p.getName();
                type = CasteTypes.SOLDIER;
            } else if (pe != null) {
                target = pe.getName();
                type = CasteTypes.SOLDIER;
            } else if (m != null) {
                target = m.name();
                type = CasteTypes.MERCHANT;
            } else if (args[1].equalsIgnoreCase("hourly") || args[1].equalsIgnoreCase("daily") || args[1].equalsIgnoreCase("weekly")) {
                target = args[1];
                type = CasteTypes.CUSTOM;
            } else {
                player.sendMessage(ChatColor.RED + "[HeroCastes] Target must be a valid player, item, or HeroStronghold region.");
                return true;
            }
            
            //Check if player has permission to create this type
            if (type == CasteTypes.MERCHANT && perms != null && !perms.has(player, "herocastes.merchant.employ")) {
                player.sendMessage(ChatColor.RED + "[HeroCastes] You dont have permission to employ a merchant");
                return true;
            } else if (type == CasteTypes.BUILDER && perms != null && !perms.has(player, "herocastes.builder.employ")) {
                player.sendMessage(ChatColor.RED + "[HeroCastes] You dont have permission to employ a builder");
                return true;
            } else if (type == CasteTypes.SOLDIER && perms != null && !perms.has(player, "herocastes.soldier.employ")) {
                player.sendMessage(ChatColor.RED + "[HeroCastes] You dont have permission to employ a soldier");
                return true;
            } else if (type == CasteTypes.CUSTOM && perms != null && !perms.has(player, "herocastes.custom.employ")) {
                player.sendMessage(ChatColor.RED + "[HeroCastes] You dont have permission to employ a player");
                return true;
            }
            
            
            
        } else if (args.length > 2 && args[0].equalsIgnoreCase("search")) {
            //hcaste search [type] [minwage]
            
        }
        
        //No longer using /hcaste setcaste.
        //Still need to check permission when accepting job postings though.
        /*if (args.length > 1 && args[0].equalsIgnoreCase("setcaste")) {
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
        }*/
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
