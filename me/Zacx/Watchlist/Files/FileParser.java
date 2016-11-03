package me.Zacx.Watchlist.Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.command.ConsoleCommandSender;

import me.Zacx.Watchlist.Main.Access;
import me.Zacx.Watchlist.Main.Core;

public class FileParser {

	private Core c;
	private ConsoleCommandSender co;
	
	public FileParser() {
		this.c = Access.c;
		this.co = Access.console;
	}
	
	public void importWatched() {

		File folder = c.getDataFolder();
		File uidFile = new File(folder + "/data.txt");

		int i = 0;
		try {

			if (!folder.exists())
				folder.mkdir();
			if (!uidFile.exists())
				uidFile.createNewFile();

			BufferedReader br = new BufferedReader(
					new FileReader(uidFile));
			String line = br.readLine();

			UUID uid;
			
			while (line != null) {
				line = line.trim();

				uid = UUID.fromString(line);
				i++;
				c.watch(uid);

				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			co.sendMessage("[§f§lWatchlist§f] §4Watching §a" + i + " §4Players.");
		}

	}

	public void exportWatched() {

		File folder = c.getDataFolder();
		File uidFile = new File(folder + "/data.txt");

		try {

			if (!folder.exists())
				folder.mkdir();
			if (!uidFile.exists())
				uidFile.createNewFile();
			else {
				uidFile.delete();
				uidFile.createNewFile();
			}

			BufferedWriter out = new BufferedWriter(new FileWriter(uidFile));
			
			for (int i = 0; i < c.getWatched().size(); i++) {
				UUID uid = c.getWatched().get(i);
				out.write(uid.toString());
				out.newLine();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
