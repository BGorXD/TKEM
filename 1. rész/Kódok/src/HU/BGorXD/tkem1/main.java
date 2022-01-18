package hu.BGorXD.tkem1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener, CommandExecutor {
	
	HashMap<Player,Entity> target = new HashMap<Player, Entity>();
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onUse(PlayerInteractEvent e) {
		Player player = e.getPlayer();
				if(player.getItemInHand().getType() != Material.AIR) {
					if(player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§c |- §6Chunk elszívó mikkentyûizé §c -|")) {
						Chunk loc = player.getLocation().getChunk();
						e.setCancelled(true);
						
						int chunkx = loc.getX();
						int chunkz = loc.getZ();
						
						int x = 16*chunkx;
						int max = x + 16;
						while(x < max) {
							int y = 0;
							while(y < 255) {
								int z = 16*chunkz;
								int zmax = z + 17;
								while(z < zmax) {
									Block b = new Location(player.getWorld(), x, y, z).getBlock();
									b.setType(Material.AIR);
									z++;
								}
								y++;
							}
							x++;
					}
				
			}
		}
				
				if(player.getItemInHand().getType() != Material.AIR) {
					if(player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§c |- §eÕrláng por §c -|")) {
						Location loc = player.getLocation();
						int x = -1;
						int z = -1;
						for(int i = 0;i<9; i++) {
							if(i%3 == 0) {
								x++;
								z=-1;
							}else {
							z++;
							}
							loc.setX(player.getLocation().getBlockX()+x);
							loc.setZ(player.getLocation().getBlockZ()+z);
							loc.getBlock().setType(Material.FIRE);
							}
						
					}
				}
				
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("chunkelszivo")) {
			Player p = (Player) sender;
			ItemStack is = new ItemStack(Material.MOB_SPAWNER,1);
			ItemMeta meta = is.getItemMeta();
			meta.setDisplayName("§c |- §6Chunk elszívó mikkentyûizé §c -|");
			is.setItemMeta(meta);
			p.getInventory().addItem(is);
		}
		if(label.equalsIgnoreCase("orlangpor")) {
			Player p = (Player) sender;
			ItemStack is1 = new ItemStack(Material.BLAZE_POWDER,1);
			ItemMeta meta1 = is1.getItemMeta();
			meta1.setDisplayName("§c |- §eÕrláng por §c -|");
			is1.setItemMeta(meta1);
			p.getInventory().addItem(is1);
		}
		return false;
	}
}
