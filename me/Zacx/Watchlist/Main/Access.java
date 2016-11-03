package me.Zacx.Watchlist.Main;

import org.bukkit.command.ConsoleCommandSender;

public class Access {

	public static Core c;
	public static ConsoleCommandSender console;
	
	public Access(Core c) {
		this.c = c;
		this.console = c.getServer().getConsoleSender();
	}
	
}