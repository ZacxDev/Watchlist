package me.Zacx.Watchlist.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.Zacx.Watchlist.Files.FileParser;

public class Core extends JavaPlugin {

	private List<UUID> watched = new ArrayList<UUID>();
	private List<UUID> admins = new ArrayList<UUID>();
	private FileParser fp;
	
	public Core() {
		new Access(this);
		fp = new FileParser();
	}
	
	public void onEnable() {
		new EventHandle();
		fp.importWatched();
	}
	
	public void onDisable() {
		fp.exportWatched();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player))
			return true;
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("watch")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("list")) {
					p.sendMessage("§b§l-Watched Players- §7§o(" + watched.size() + ")");
					for (int i = 0; i < watched.size(); i++) {
						UUID uid = watched.get(i);
						if (Bukkit.getPlayer(uid) != null) {
							String s = Bukkit.getPlayer(uid).getName();
							p.sendMessage("§4" + s);
						}
					}
				}
				
				Player tar = Bukkit.getPlayer(args[0]);
				if (tar != null && tar.isOnline()) {
					watch(tar.getUniqueId());
					p.sendMessage("§aNow watching §4" + tar.getName() + "§a.");
				}
			}
		} else if (cmd.getName().equalsIgnoreCase("unwatch")) {
			if (args.length == 1) {
				if (Bukkit.getPlayer(args[0]) != null) {
					Player tar = Bukkit.getPlayer(args[0]);
					getWatched().remove(tar.getUniqueId());
					p.sendMessage("§aNo longer watching §e" + tar.getName() + "§a.");
				}
			}
		}
		
		return true;
	} 
	
	public List<UUID> getWatched() {
		return watched;
	}
	
	public void watch(UUID uid) {
		watched.add(uid);
	}
	
	public List<UUID> getAdmins() {
		return admins;
	}
	
}
