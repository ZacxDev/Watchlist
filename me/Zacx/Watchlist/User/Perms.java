package me.Zacx.Watchlist.User;

import org.bukkit.permissions.Permission;

public enum Perms {

	ADMIN("watchlist.admin");
	
	
	public Permission perm;
	private Perms(String s) {
		perm = new Permission(s);
	}
	
}
