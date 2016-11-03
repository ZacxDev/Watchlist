package me.Zacx.Watchlist.Main;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Zacx.Watchlist.User.Perms;

public class EventHandle implements Listener {

	private Core c;
	
	public EventHandle() {
		this.c = Access.c;
		c.getServer().getPluginManager().registerEvents(this, c);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if (p.isOp() || p.hasPermission(Perms.ADMIN.perm)) {
			c.getAdmins().add(p.getUniqueId());
		} else if (c.getWatched().contains(p.getUniqueId()) && c.getAdmins().isEmpty()) {
			p.kickPlayer("[Watchlist] has kicked you because no admins are online!");
		}
	}
	
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		if (c.getAdmins().contains(p.getUniqueId())) {
			c.getAdmins().remove(p.getUniqueId());
			
			if (c.getAdmins().isEmpty())
				for (int i = 0; i < c.getWatched().size(); i++) {
					UUID uid = c.getWatched().get(i);
					Player tar = Bukkit.getPlayer(uid);
					if (tar.isOnline())
						tar.kickPlayer("[Watchlist] has kicked you because no admins are online!");
				}
		}
		
	}
	
}